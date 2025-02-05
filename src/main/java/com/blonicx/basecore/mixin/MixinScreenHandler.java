package com.blonicx.basecore.mixin;

import com.blonicx.basecore.api.minecraft.client.events.items.ItemTransferEvent;
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
public class MixinScreenHandler {

	@Inject(method = "onSlotClick", at = @At("HEAD"))
	private void onSlotClick(int slotId, int button, SlotActionType actionType, PlayerEntity player, CallbackInfo info) {
		// Fetch the slot's source inventory. This should be the player's inventory.
		Inventory sourceInventory = player.getInventory();

		// Fetch the slot's destination inventory. This should be a container or the player's inventory.
		Inventory destinationInventory = player.currentScreenHandler.slots.get(slotId).inventory;
		ItemStack itemStack = player.getInventory().getStack(slotId);

		// Trigger the custom ItemTransferEvent when an item is moved
		ItemTransferEvent.ITEM_TRANSFER.invoker().onItemTransfer(player, sourceInventory, destinationInventory, itemStack);
	}
}