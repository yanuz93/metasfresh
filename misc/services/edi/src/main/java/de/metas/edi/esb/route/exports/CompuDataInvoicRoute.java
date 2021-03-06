package de.metas.edi.esb.route.exports;

import java.text.DecimalFormat;

import javax.xml.namespace.QName;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.spi.DataFormat;
import org.milyn.smooks.camel.dataformat.SmooksDataFormat;
import org.springframework.stereotype.Component;

import de.metas.edi.esb.bean.exports.invoic.CompuDataInvoicBean;
import de.metas.edi.esb.commons.Constants;
import de.metas.edi.esb.commons.Util;
import de.metas.edi.esb.jaxb.metasfresh.EDICctopInvoicVType;
import de.metas.edi.esb.jaxb.metasfresh.EDIInvoiceFeedbackType;
import de.metas.edi.esb.processor.feedback.EDIXmlSuccessFeedbackProcessor;
import de.metas.edi.esb.processor.feedback.helper.EDIXmlFeedbackHelper;
import de.metas.edi.esb.route.AbstractEDIRoute;

@Component
public class CompuDataInvoicRoute extends AbstractEDIRoute
{
	public static final String ROUTE_ID = "MF-Invoic-To-COMPUDATA-Invoic";

	private static final String EDI_INVOICE_FILENAME_PATTERN = "edi.file.invoic.compudata.filename";

	public static final String EP_EDI_COMPUDATA_INVOICE_CONSUMER = "direct:edi.invoice.consumer";

	public static final String EDI_INVOIC_SENDER_GLN = "edi.props.000.sender.gln";
	public static final String EDI_INVOIC_IS_TEST = "edi.compudata.invoic.isTest";

	public final static QName EDIInvoiceFeedback_QNAME = Constants.JAXB_ObjectFactory.createEDIInvoiceFeedback(null).getName();
	public static final String METHOD_setCInvoiceID = "setCInvoiceID";

	/**
	 * The FILE folder where the EDI file will be stored
	 */
	public static final String EP_EDI_FILE_INVOICE = "{{edi.file.invoic.compudata}}";

	@Override
	public void configureEDIRoute(final DataFormat jaxb, final DecimalFormat decimalFormat)
	{
		final SmooksDataFormat sdf = getSDFForConfiguration("edi.smooks.config.xml.invoices");

		// FRESH-360: provide our own converter, so we don't anymore need to rely on the system's default charset when writing the EDI data to file.
		final ReaderTypeConverter readerTypeConverter = new ReaderTypeConverter();
		getContext().getTypeConverterRegistry().addTypeConverters(readerTypeConverter);

		final String invoiceFilenamePattern = Util.resolveProperty(getContext(), CompuDataInvoicRoute.EDI_INVOICE_FILENAME_PATTERN);

		final String senderGln = Util.resolveProperty(getContext(), CompuDataInvoicRoute.EDI_INVOIC_SENDER_GLN);
		final String isTest = Util.resolveProperty(getContext(), CompuDataInvoicRoute.EDI_INVOIC_IS_TEST);

		from(CompuDataInvoicRoute.EP_EDI_COMPUDATA_INVOICE_CONSUMER)
				.routeId(ROUTE_ID)

		.log(LoggingLevel.INFO, "EDI: Setting defaults as exchange properties...")
				.setProperty(CompuDataInvoicRoute.EDI_INVOIC_SENDER_GLN).constant(senderGln)
				.setProperty(CompuDataInvoicRoute.EDI_INVOIC_IS_TEST).constant(isTest)

		.log(LoggingLevel.INFO, "EDI: Setting EDI feedback headers...")
				.process(new Processor()
				{
					@Override
					public void process(final Exchange exchange)
					{
						// i'm sure that there are better ways, but we want the EDIFeedbackRoute to identify that the error is coming from *this* route.
						exchange.getIn().setHeader(EDIXmlFeedbackHelper.HEADER_ROUTE_ID, ROUTE_ID);

						final EDICctopInvoicVType xmlCctopInvoice = exchange.getIn().getBody(EDICctopInvoicVType.class);

						exchange.getIn().setHeader(EDIXmlFeedbackHelper.HEADER_ADClientValueAttr, xmlCctopInvoice.getADClientValueAttr());
						exchange.getIn().setHeader(EDIXmlFeedbackHelper.HEADER_RecordID, xmlCctopInvoice.getCInvoiceID().longValue());
					}
				})

		.log(LoggingLevel.INFO, "EDI: Converting XML Java Object -> EDI Java Object...")
				.bean(CompuDataInvoicBean.class, CompuDataInvoicBean.METHOD_createEDIData)

		.log(LoggingLevel.INFO, "EDI: Marshalling EDI Java Object to EDI Format using SDF...")
				.marshal(sdf)

		.log(LoggingLevel.INFO, "EDI: Setting output filename pattern from properties...")
				.setHeader(Exchange.FILE_NAME).simple(invoiceFilenamePattern)

		.log(LoggingLevel.INFO, "EDI: Sending the EDI file to the FILE component...")
				.to(CompuDataInvoicRoute.EP_EDI_FILE_INVOICE)

		.log(LoggingLevel.INFO, "EDI: Creating metasfresh feedback XML Java Object...")
				.process(new EDIXmlSuccessFeedbackProcessor<EDIInvoiceFeedbackType>(EDIInvoiceFeedbackType.class, CompuDataInvoicRoute.EDIInvoiceFeedback_QNAME, CompuDataInvoicRoute.METHOD_setCInvoiceID))

		.log(LoggingLevel.INFO, "EDI: Marshalling XML Java Object feedback -> XML document...")
				.marshal(jaxb)

		.log(LoggingLevel.INFO, "EDI: Sending success response to metasfresh...")
				.to(Constants.EP_AMQP_TO_MF);
	}
}
