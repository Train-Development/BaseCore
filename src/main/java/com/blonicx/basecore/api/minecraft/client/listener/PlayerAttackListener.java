package com.blonicx.basecore.api.minecraft.client.listener;

import com.blonicx.basecore.api.minecraft.client.events.entity.PlayerClickEvent;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public class PlayerAttackListener {
    public static void register() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity instanceof PlayerEntity target) {
                return PlayerClickEvent.PLAYER_CLICK.invoker().onPlayerClick(player, target, hand, world);
            }
            return ActionResult.PASS;
        });
    }
}
