package de.metas.handlingunits;

/*
 * #%L
 * de.metas.handlingunits.base
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

import java.util.List;

import org.adempiere.util.ISingletonService;

import de.metas.handlingunits.model.I_M_HU;
import de.metas.handlingunits.model.I_M_HU_PI_Item_Product;
import de.metas.handlingunits.model.I_M_PickingSlot;
import de.metas.handlingunits.model.I_M_PickingSlot_HU;
import de.metas.handlingunits.model.I_M_PickingSlot_Trx;
import de.metas.handlingunits.model.X_M_HU;
import de.metas.inoutcandidate.model.I_M_ShipmentSchedule;
import de.metas.picking.api.IPickingSlotBL;
import lombok.Builder.Default;
import lombok.NonNull;

/**
 * Note: Please use this interface in this module instead of {@link IPickingSlotBL}.
 */
public interface IHUPickingSlotBL extends IPickingSlotBL, ISingletonService
{
	/**
	 * Creates a new HU and sets it to the given <code>pickingSlot</code>.
	 * <p>
	 * Note: the method does not set the new HU's <code>C_BPartner_ID</code> and <code>C_BPartner_Location_ID</code>. Setting them is the job of the business logic which associates the HU with the
	 * <code>M_ShipmentSchedule</code> for which we are doing all this.
	 *
	 * @param pickingSlot
	 * @param itemProduct the blueprint to use for the new HU.
	 * @return the result with the created picking slot trx (the trx will have ACTION_Set_Current_HU)
	 */
	IQueueActionResult createCurrentHU(I_M_PickingSlot pickingSlot, I_M_HU_PI_Item_Product itemProduct);

	/**
	 * Adds current picking slot HU to HUs queue.
	 *
	 * @param pickingSlot
	 * @return the result: if the picking slot had a current HU assigned, the result contains a trx to document the HU being closed
	 */
	IQueueActionResult closeCurrentHU(I_M_PickingSlot pickingSlot);

	/**
	 * Adds given Handling Units to picking slot queue.
	 *
	 * NOTEs:
	 * <ul>
	 * <li>It will also change HU's status to {@link X_M_HU#HUSTATUS_Picked}.
	 * <li>the method <b>does not</b> set the HU's <code>C_BPartner_ID</code> and <code>C_BPartner_Lcoation_ID</code>. Setting them is the job of the business logic which associates the HU with the
	 * <code>M_ShipmentSchedule</code> for which we are doing all this.
	 * <li>if any of the given HUs are included in some other HU, they will be taken out and parent HUs will be destroyed if they become empty
	 * </ul>
	 *
	 * @param pickingSlot
	 * @param hu
	 * @return the results with the created picking slot trx and the picking-slot-hu-assignment that was created or updated
	 */
	List<IQueueActionResult> addToPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot pickingSlot, List<I_M_HU> hus);

	/**
	 * @see #addToPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot, List)
	 */
	IQueueActionResult addToPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot pickingSlot, I_M_HU hu);

	/**
	 * Removes the given <code>hu</code> from the picking slot queue by deleting its associating {@link I_M_PickingSlot_HU} record.<br>
	 * If the given hu was the slot's current HU, it is unset as current HU as well.<br>
	 * Finally, if the given <code>pickingSlot</code> is dynamic, it also releases the slot from its current BPartner.
	 *
	 * TODO: i think it should check if there queue is *really* empty before releasing form the BPartner.
	 *
	 * @param pickingSlot
	 * @param hu
	 * @return the result with the created picking slot trx
	 */
	IQueueActionResult removeFromPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot pickingSlot, I_M_HU hu);

	/**
	 *
	 * @param hu
	 * @see #removeFromPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot, I_M_HU).
	 */
	void removeFromPickingSlotQueue(I_M_HU hu);

	/**
	 * Removes the given <code>hu</code> all of it's children (recursively) from any picking slot (current picking slot HU or in picking slot queue).
	 *
	 * @param hu
	 * @see #removeFromPickingSlotQueue(de.metas.picking.model.I_M_PickingSlot, I_M_HU)
	 */
	void removeFromPickingSlotQueueRecursivelly(I_M_HU hu);

	/**
	 * @return <code>true</code> if the given <code>itemProduct</code> references no <code>C_BPartner</code> or if the referenced BPartner fits with the given <code>pickingSlot</code>.
	 * @see #isAvailableForBPartnerID(de.metas.picking.model.I_M_PickingSlot, int)
	 */
	boolean isAvailableForProduct(I_M_PickingSlot pickingSlot, I_M_HU_PI_Item_Product itemProduct);

	/**
	 * Allocate dynamic picking slot to selected partner and location if the picking slot was not already allocated for a partner. If the picking slot is not dynamic, the method does nothing.
	 * <p>
	 * Note: Even if the given <code>pickingSlot</code> already has a HU assigned to itself, this method does not set that HU's <code>C_BPartner_ID</code> and <code>C_BPartner_Lcoation_ID</code>.
	 * Setting them is the job of the business logic which associates the HU with the <code>M_ShipmentSchedule</code> for which we are doing all this.
	 *
	 * @param pickingSlot
	 * @param bpartnerId
	 * @param bpartnerLocationId
	 */
	void allocatePickingSlot(I_M_PickingSlot pickingSlot, int bpartnerId, int bpartnerLocationId);

	/**
	 * Release the given dynamic picking slot.<br>
	 * By releasing, we mean "resetting the slot's C_BPartner to <code>null</code>". If the picking slot is not dynamic or not allocated to any partner, the method does nothing.<br>
	 * <b>Important:</b> Picking slot will NOT be released if there still are any {@link I_M_PickingSlot_HU} assignments.
	 *
	 * @param pickingSlot
	 */
	void releasePickingSlot(I_M_PickingSlot pickingSlot);

	/**
	 * Ad-Hoc and simple return type for above methods
	 *
	 * @author ts
	 *
	 */
	interface IQueueActionResult
	{
		I_M_PickingSlot_Trx getM_PickingSlot_Trx();

		I_M_PickingSlot_HU getI_M_PickingSlot_HU();
	}

	/**
	 * Search for available (top level) HUs to be picked.
	 *
	 * @param request
	 * 
	 * @return matching HUs
	 */
	List<I_M_HU> retrieveAvailableHUsToPick(AvailableHUsToPickRequest request);

	@lombok.Builder
	@lombok.Value
	public static final class AvailableHUsToPickRequest
	{
		/**
		 * If true we shall consider the HU attributes while searching for matching HUs.
		 */
		@Default
		final boolean considerAttributes = true;

		/**
		 * ShipmentSchedules for which the HUs shall be picked. May not be {@code null}.
		 */
		@NonNull
		final List<I_M_ShipmentSchedule> shipmentSchedules;

		/**
		 * {@code true} by default, for backwards compatibility.
		 */
		@Default
		final boolean onlyTopLevelHUs = true;
	}
}
