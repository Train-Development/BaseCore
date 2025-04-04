package com.blonicx.basecore.mixin.gui;

import com.blonicx.basecore.api.minecraft.events.items.ItemTransferEvent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ScreenHandler.class)
public class ScreenHandlerMixin {

	@Inject(method = "onSlotClick", at = @At("HEAD"))
	private void onSlotClick(int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo info) {
		// Fetch the slot's source inventory. This should be the player's inventory. //
		Inventory sourceInventory = player.getInventory();

		// Fetch the slot's destination inventory. This should be a container or the player's inventory. //
		Inventory destinationInventory = slotId >= 0 && slotId < player.currentScreenHandler.slots.size()
				? player.currentScreenHandler.slots.get(slotId).inventory
				: null;

		ItemStack itemStack = slotId >= 0 && slotId < player.getInventory().size()
				? player.getInventory().getStack(slotId)
				: ItemStack.EMPTY;

		// Trigger the custom ItemTransferEvent when an item is moved //
		if (destinationInventory != null) {
			ItemTransferEvent.ITEM_TRANSFER.invoker().onItemTransfer(player, sourceInventory, destinationInventory, itemStack);
		}
	}
}