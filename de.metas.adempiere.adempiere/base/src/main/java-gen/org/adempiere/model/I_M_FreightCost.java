/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.adempiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.compiere.model.I_M_Product;
import org.compiere.model.MTable;
import org.compiere.util.KeyNamePair;

/** Generated Interface for M_FreightCost
 *  @author Adempiere (generated) 
 *  @version Release 3.5.4a
 */
public interface I_M_FreightCost 
{

    /** TableName=M_FreightCost */
    public static final String Table_Name = "M_FreightCost";

    /** AD_Table_ID=540004 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Mandant.
	  * Mandant fuer diese Installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organisation.
	  * Organisatorische Einheit des Mandanten
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organisation.
	  * Organisatorische Einheit des Mandanten
	  */
	public int getAD_Org_ID();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Erstellt.
	  * Datum, an dem dieser Eintrag erstellt wurde
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Erstellt durch.
	  * Nutzer, der diesen Eintrag erstellt hat
	  */
	public int getCreatedBy();

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Beschreibung.
	  * Optionale kurze Beschreibung fuer den Eintrag
	  */
	public void setDescription (String Description);

	/** Get Beschreibung.
	  * Optionale kurze Beschreibung fuer den Eintrag
	  */
	public String getDescription();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Aktiv.
	  * Der Eintrag ist im System aktiv
	  */
	public void setIsActive (boolean IsActive);

	/** Get Aktiv.
	  * Der Eintrag ist im System aktiv
	  */
	public boolean isActive();

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Standard.
	  * Standardeintrag
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Standard.
	  * Standardeintrag
	  */
	public boolean isDefault();

    /** Column name M_FreightCost_ID */
    public static final String COLUMNNAME_M_FreightCost_ID = "M_FreightCost_ID";

	/** Set Frachtkostenpauschale	  */
	public void setM_FreightCost_ID (int M_FreightCost_ID);

	/** Get Frachtkostenpauschale	  */
	public int getM_FreightCost_ID();

    /** Column name M_FreightCost_includedTab */
    public static final String COLUMNNAME_M_FreightCost_includedTab = "M_FreightCost_includedTab";

	/** Set M_FreightCost_includedTab	  */
	public void setM_FreightCost_includedTab (String M_FreightCost_includedTab);

	/** Get M_FreightCost_includedTab	  */
	public String getM_FreightCost_includedTab();

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Produkt.
	  * Produkt, Leistung, Artikel
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Produkt.
	  * Produkt, Leistung, Artikel
	  */
	public int getM_Product_ID();

	public I_M_Product getM_Product() throws RuntimeException;

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumerische Bezeichnung fuer diesen Eintrag
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumerische Bezeichnung fuer diesen Eintrag
	  */
	public String getName();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Aktualisiert.
	  * Datum, an dem dieser Eintrag aktualisiert wurde
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Aktualisiert durch.
	  * Nutzer, der diesen Eintrag aktualisiert hat
	  */
	public int getUpdatedBy();

    /** Column name Value */
    public static final String COLUMNNAME_Value = "Value";

	/** Set Suchschluessel.
	  * Suchschluessel fuer den Eintrag im erforderlichen Format - muss eindeutig sein
	  */
	public void setValue (String Value);

	/** Get Suchschluessel.
	  * Suchschluessel fuer den Eintrag im erforderlichen Format - muss eindeutig sein
	  */
	public String getValue();
}
