package com.blonicx.basecore.api.minecraft.client.listener;

import com.blonicx.basecore.api.minecraft.client.events.entity.PlayerClickEvent;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class PlayerClickListener {
    public static void register() {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof PlayerEntity target) {
                if (hand == Hand.OFF_HAND) return ActionResult.PASS; // Ignore off-hand interactions
                return PlayerClickEvent.PLAYER_CLICK.invoker().onPlayerClick(player, target, hand, world);
            }
            return ActionResult.PASS;
        });
    }
}
