package com.blonicx.basecore.api.minecraft.events.entity.friendly.sheep;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class SheepDamageEvent {
    public static final Event<Listener> SHEEP_DAMAGE = EventFactory.createArrayBacked(
            Listener.class,
            (listeners) -> (entity, world, damage) -> {
                for (Listener listener : listeners) {
                    ActionResult result = listener.onSheepDamaged(entity, world, damage);
                    if (result == ActionResult.SUCCESS) return ActionResult.SUCCESS;
                }
                return ActionResult.PASS;
            }
    );

    public interface Listener {
        ActionResult onSheepDamaged(SheepEntity entity, World world, float damage);
    }
}