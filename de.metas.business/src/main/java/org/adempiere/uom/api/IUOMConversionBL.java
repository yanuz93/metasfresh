package org.adempiere.uom.api;

/*
 * #%L
 * de.metas.adempiere.adempiere.base
 * %%
 * Copyright (C) 2015 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.adempiere.uom.UomId;
import org.compiere.model.I_C_UOM;
import org.compiere.model.I_C_UOM_Conversion;

import de.metas.product.ProductId;
import de.metas.quantity.Quantity;
import de.metas.util.ISingletonService;
import lombok.NonNull;

public interface IUOMConversionBL extends ISingletonService
{
	/**
	 * Convert quantity from <code>uomFrom</code> to <code>uomTo</code>
	 *
	 * @return converted quantity; never return NULL.
	 */
	BigDecimal convertQty(
			final ProductId productId,
			final BigDecimal qty,
			@NonNull final I_C_UOM uomFrom,
			@NonNull final I_C_UOM uomTo);

	/**
	 * Convert quantity from <code>uomFrom</code> to <code>uomTo</code>
	 *
	 * @param conversionCtx conversion context
	 * @param qty
	 * @param uomFrom
	 * @param uomTo
	 * @return converted quantity; never return NULL.
	 */
	BigDecimal convertQty(UOMConversionContext conversionCtx, BigDecimal qty, I_C_UOM uomFrom, I_C_UOM uomTo);

	/**
	 * Creates a new {@link Quantity} object by converting the given {@code quantity} to the given {@code uomTo}.
	 *
	 * The new {@link Quantity} object will have {@link #getQty()} and {@link #getUOM()} as their source Qty/UOM.
	 *
	 * @return new Quantity converted to given <code>uom</code>.
	 */
	Quantity convertQuantityTo(Quantity quantity, UOMConversionContext conversionCtx, I_C_UOM uomTo);

	Quantity convertQuantityTo(Quantity quantity, UOMConversionContext conversionCtx, UomId uomToId);

	/**
	 * Convert quantity from <code>uomFrom</code> to product's stocking UOM.
	 *
	 * @param conversionCtx
	 * @param qty
	 * @param uomFrom
	 * @return converted quantity; never return NULL.
	 */
	BigDecimal convertQtyToProductUOM(UOMConversionContext conversionCtx, BigDecimal qty, I_C_UOM uomFrom);

	/**
	 * Convert price from <code>uomFrom</code> to <code>uomTo</code>
	 *
	 * @param product
	 * @param price
	 * @param uomFrom may not be <code>null</code>.
	 * @param uomTo may not be <code>null</code>.
	 * @param pricePrecision precision to be used for resulting price
	 * @return converted price using <code>pricePrecision</code>; never return NULL.
	 */
	BigDecimal convertPrice(int productId, BigDecimal price, I_C_UOM uomFrom, I_C_UOM uomTo, int pricePrecision);

	/**
	 * Rounds given qty to UOM standard precision.
	 *
	 * If qty's actual precision is bigger than UOM standard precision then the qty WON'T be rounded.
	 *
	 * @param qty
	 * @param uom
	 * @return qty rounded to UOM precision
	 */
	BigDecimal roundToUOMPrecisionIfPossible(BigDecimal qty, I_C_UOM uom);

	/**
	 * Gets UOM standard precision.
	 *
	 * @param ctx
	 * @param uomId
	 * @return
	 */
	int getPrecision(int uomId);

	/**
	 * Round quantity based on uom's standard precision if stdPrecision = true, uom's costing precision otherwise.
	 *
	 * @param uom
	 * @param qty quantity
	 * @param useStdPrecision true if standard precision
	 * @return rounded quantity
	 */
	BigDecimal roundQty(I_C_UOM uom, BigDecimal qty, boolean useStdPrecision);

	// BigDecimal convert(Properties ctx, I_C_UOM uomFrom, I_C_UOM uomTo, BigDecimal qty);

	/**
	 * Get Converted Qty from Server (no cache)
	 *
	 * @param qty The quantity to be converted
	 * @param uomFrom The C_UOM of the qty
	 * @param uomTo The targeted UOM
	 * @param useStdPrecision if true, standard precision, if false costing precision
	 * @return amount
	 * @deprecated should not be used
	 */
	@Deprecated
	BigDecimal convert(Properties ctx, I_C_UOM uomFrom, I_C_UOM uomTo, BigDecimal qty, boolean useStdPrecision);

	/**
	 * * Converts the given qty from the given product's stocking UOM to the given destination UOM.
	 * <p>
	 * As a rule of thumb, if you want to get QtyEntered from QtOrdered/Moved/Invoiced/Requiered, this is your method.
	 *
	 * @param ctx context
	 * @param product product from whose stocking UOM we want to convert
	 * @param uomDest the UOM to which we want to convert
	 * @param qtyToConvert the Qty in the product's stocking UOM
	 * @return the converted qty or <code>null</code> if the product's stocking UOM is different from the given <code>uomDest</code> and if there is no conversion rate to use.
	 */
	BigDecimal convertFromProductUOM(Properties ctx, ProductId productId, I_C_UOM uomDest, BigDecimal qtyToConvert);
	

	/**
	 * Derive Standard Conversions
	 *
	 * @param ctx context
	 * @param uomFrom from UOM
	 * @param uomTo to UOM
	 * @return Conversion or null
	 */
	BigDecimal deriveRate(Properties ctx, I_C_UOM uomFrom, I_C_UOM uomTo);

	/**
	 * Convert qty to target UOM and round.
	 *
	 * @param ctx context
	 * @param uomFrom from UOM
	 * @param uomTo to UOM
	 * @param qty qty
	 * @return converted qty (std precision)
	 */
	BigDecimal convert(Properties ctx, I_C_UOM uomFrom, I_C_UOM uomTo, BigDecimal qty);

	/**
	 * Converts the given qty from the given source UOM to the given product's stocking UOM.
	 *
	 * @param ctx context
	 * @param product
	 * @param uomSource the UOM of the given qty
	 * @param qtyToConvert
	 *
	 * @return the converted qty or <code>null</code> if the product's stocking UOM is different from the given <code>C_UOM_Source_ID</code> and if there is no conversion rate to use.
	 */
	BigDecimal convertToProductUOM(Properties ctx, ProductId productId, I_C_UOM uomSource, BigDecimal qtyToConvert);

	Quantity convertToProductUOM(Quantity quantity, ProductId productId);

	/**
	 * Get Product Conversions (cached). More detailed: gets those <code>C_UOM_Conversion</code>s that
	 * <ul>
	 * <li>reference the given <code>product</code>
	 * <li>have <code>C_UOM_ID</code> (i.e. the "from" UOM) being equal to the product's <code>C_UOM</code>
	 * </ul>
	 *
	 * @param ctx context
	 * @param product
	 * @return array of conversions
	 */
	List<I_C_UOM_Conversion> getProductConversions(ProductId productId);

	/**
	 * Get Multiplier Rate to target UOM
	 *
	 * @param ctx context
	 * @param uomFrom
	 * @param uomTo
	 * @return multiplier
	 */
	BigDecimal getRate(Properties ctx, I_C_UOM uomFrom, I_C_UOM uomTo);
}