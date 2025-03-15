package com.blonicx.basecore;

import com.blonicx.basecore.api.minecraft.client.events.entity.PlayerClickEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import com.blonicx.basecore.api.utils.placeholder.PlaceholderRegistry;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class BaseCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registering for ChatMessageEvent //
        ClientReceiveMessageEvents.CHAT.register((message, signed_message, sender, params, timestamp) -> {
            // Replace placeholders in the message //
            PlaceholderRegistry.processString(String.valueOf(message));
        });

        // Registering the PlayerClickEvent Callbacks //
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof PlayerEntity target) {
                PlayerClickEvent.PLAYER_CLICK.invoker().onPlayerClick(player, target, hand, world);
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });

        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof PlayerEntity target) {
                if (hand == Hand.OFF_HAND) return ActionResult.PASS; // Ignore off-hand interactions
                PlayerClickEvent.PLAYER_CLICK.invoker().onPlayerClick(player, target, hand, world);
                return ActionResult.PASS;
            }

            return ActionResult.PASS;
        });
    }
}
