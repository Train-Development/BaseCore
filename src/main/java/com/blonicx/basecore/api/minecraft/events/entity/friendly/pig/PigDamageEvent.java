package com.blonicx.basecore.api.minecraft.events.entity.friendly.pig;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class PigDamageEvent {
    public static final Event<Listener> PIG_DAMAGE = EventFactory.createArrayBacked(
            Listener.class,
            (listeners) -> (entity, world, damage) -> {
                for (Listener listener : listeners) {
                    ActionResult result = listener.onPigDamaged(entity, world, damage);
                    if (result == ActionResult.SUCCESS) return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
    );

    public interface Listener {
        ActionResult onPigDamaged(PigEntity entity, World world, float damage);
    }
}