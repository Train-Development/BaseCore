package com.blonicx.basecore.api.minecraft.events.entity.cow;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class CowDamageEvent {
    public static final Event<Listener> COW_DAMAGE = EventFactory.createArrayBacked(
            Listener.class,
            (listeners) -> (entity, world, damage) -> {
                for (Listener listener : listeners) {
                    ActionResult result = listener.onCowDamaged(entity, world, damage);
                    if (result == ActionResult.SUCCESS) return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
    );

    public interface Listener {
        ActionResult onCowDamaged(CowEntity entity, World world, float damage);
    }
}