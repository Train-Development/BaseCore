package com.blonicx.basecore.api.minecraft.events.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class PlayerDamageEvent {
    public static final Event<Listener> PLAYER_DAMAGE = EventFactory.createArrayBacked(
            Listener.class,
            (listeners) -> (player, world, damage) -> {
                for (Listener listener : listeners) {
                    ActionResult result = listener.onPlayerDamaged(player, world, damage);
                    if (result == ActionResult.SUCCESS) return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
    );

    public interface Listener {
        ActionResult onPlayerDamaged(PlayerEntity player, World world, float damage);
    }
}