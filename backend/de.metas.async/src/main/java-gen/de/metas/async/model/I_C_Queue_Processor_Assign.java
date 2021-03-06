package de.metas.async.model;


/** Generated Interface for C_Queue_Processor_Assign
 *  @author Adempiere (generated) 
 */
@SuppressWarnings("javadoc")
public interface I_C_Queue_Processor_Assign 
{

    /** TableName=C_Queue_Processor_Assign */
    public static final String Table_Name = "C_Queue_Processor_Assign";

    /** AD_Table_ID=540487 */
//    public static final int Table_ID = org.compiere.model.MTable.getTable_ID(Table_Name);

//    org.compiere.util.KeyNamePair Model = new org.compiere.util.KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client
     */
//    java.math.BigDecimal accessLevel = java.math.BigDecimal.valueOf(6);

    /** Load Meta Data */

	/**
	 * Get Mandant.
	 * Mandant für diese Installation.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Client_ID();

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/**
	 * Set Organisation.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setAD_Org_ID (int AD_Org_ID);

	/**
	 * Get Organisation.
	 * Organisatorische Einheit des Mandanten
	 *
	 * <br>Type: Search
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getAD_Org_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/**
	 * Set WorkPackage Processor.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setC_Queue_PackageProcessor_ID (int C_Queue_PackageProcessor_ID);

	/**
	 * Get WorkPackage Processor.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getC_Queue_PackageProcessor_ID();

	public de.metas.async.model.I_C_Queue_PackageProcessor getC_Queue_PackageProcessor();

	public void setC_Queue_PackageProcessor(de.metas.async.model.I_C_Queue_PackageProcessor C_Queue_PackageProcessor);

    /** Column definition for C_Queue_PackageProcessor_ID */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, de.metas.async.model.I_C_Queue_PackageProcessor> COLUMN_C_Queue_PackageProcessor_ID = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, de.metas.async.model.I_C_Queue_PackageProcessor>(I_C_Queue_Processor_Assign.class, "C_Queue_PackageProcessor_ID", de.metas.async.model.I_C_Queue_PackageProcessor.class);
    /** Column name C_Queue_PackageProcessor_ID */
    public static final String COLUMNNAME_C_Queue_PackageProcessor_ID = "C_Queue_PackageProcessor_ID";

	/**
	 * Set Assigned Workpackage Processors.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setC_Queue_Processor_Assign_ID (int C_Queue_Processor_Assign_ID);

	/**
	 * Get Assigned Workpackage Processors.
	 *
	 * <br>Type: ID
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getC_Queue_Processor_Assign_ID();

    /** Column definition for C_Queue_Processor_Assign_ID */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object> COLUMN_C_Queue_Processor_Assign_ID = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object>(I_C_Queue_Processor_Assign.class, "C_Queue_Processor_Assign_ID", null);
    /** Column name C_Queue_Processor_Assign_ID */
    public static final String COLUMNNAME_C_Queue_Processor_Assign_ID = "C_Queue_Processor_Assign_ID";

	/**
	 * Set Queue Processor Definition.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setC_Queue_Processor_ID (int C_Queue_Processor_ID);

	/**
	 * Get Queue Processor Definition.
	 *
	 * <br>Type: TableDir
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getC_Queue_Processor_ID();

	public de.metas.async.model.I_C_Queue_Processor getC_Queue_Processor();

	public void setC_Queue_Processor(de.metas.async.model.I_C_Queue_Processor C_Queue_Processor);

    /** Column definition for C_Queue_Processor_ID */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, de.metas.async.model.I_C_Queue_Processor> COLUMN_C_Queue_Processor_ID = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, de.metas.async.model.I_C_Queue_Processor>(I_C_Queue_Processor_Assign.class, "C_Queue_Processor_ID", de.metas.async.model.I_C_Queue_Processor.class);
    /** Column name C_Queue_Processor_ID */
    public static final String COLUMNNAME_C_Queue_Processor_ID = "C_Queue_Processor_ID";

	/**
	 * Get Erstellt.
	 * Datum, an dem dieser Eintrag erstellt wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getCreated();

    /** Column definition for Created */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object> COLUMN_Created = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object>(I_C_Queue_Processor_Assign.class, "Created", null);
    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/**
	 * Get Erstellt durch.
	 * Nutzer, der diesen Eintrag erstellt hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getCreatedBy();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/**
	 * Set Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public void setIsActive (boolean IsActive);

	/**
	 * Get Aktiv.
	 * Der Eintrag ist im System aktiv
	 *
	 * <br>Type: YesNo
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public boolean isActive();

    /** Column definition for IsActive */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object> COLUMN_IsActive = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object>(I_C_Queue_Processor_Assign.class, "IsActive", null);
    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/**
	 * Get Aktualisiert.
	 * Datum, an dem dieser Eintrag aktualisiert wurde
	 *
	 * <br>Type: DateTime
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public java.sql.Timestamp getUpdated();

    /** Column definition for Updated */
    public static final org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object> COLUMN_Updated = new org.adempiere.model.ModelColumn<I_C_Queue_Processor_Assign, Object>(I_C_Queue_Processor_Assign.class, "Updated", null);
    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/**
	 * Get Aktualisiert durch.
	 * Nutzer, der diesen Eintrag aktualisiert hat
	 *
	 * <br>Type: Table
	 * <br>Mandatory: true
	 * <br>Virtual Column: false
	 */
	public int getUpdatedBy();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";
}
