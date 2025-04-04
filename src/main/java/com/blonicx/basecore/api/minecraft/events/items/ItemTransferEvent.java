package com.blonicx.basecore.api.minecraft.events.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class ItemTransferEvent {

    // Register the Event //
    public static final Event<ItemTransferEventListener> ITEM_TRANSFER = EventFactory.createArrayBacked(ItemTransferEventListener.class, (listeners) -> (player, sourceInventory, destinationInventory, itemStack) -> {
        for (ItemTransferEventListener listener : listeners) {
            listener.onItemTransfer(player, sourceInventory, destinationInventory, itemStack);
        }
    });

    private final PlayerEntity player;
    private final Inventory sourceInventory;
    private final Inventory destinationInventory;
    private final ItemStack itemStack;

    public ItemTransferEvent(PlayerEntity player, Inventory sourceInventory, Inventory destinationInventory, ItemStack itemStack) {
        this.player = player;
        this.sourceInventory = sourceInventory;
        this.destinationInventory = destinationInventory;
        this.itemStack = itemStack;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public Inventory getSourceInventory() {
        return sourceInventory;
    }

    public Inventory getDestinationInventory() {
        return destinationInventory;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public interface ItemTransferEventListener {
        void onItemTransfer(PlayerEntity player, Inventory sourceInventory, Inventory destinationInventory, ItemStack itemStack);
    }
}