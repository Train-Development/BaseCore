package com.blonicx.basecore.api.minecraft.events.entity.player;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class PlayerClickEvent {
    // Register the Event //
    public static final Event<PlayerClickEventListener> PLAYER_CLICK = EventFactory.createArrayBacked(
            PlayerClickEventListener.class,
            (listeners) -> (clicker, target, hand, world) -> {
                for (PlayerClickEventListener listener : listeners) {
                    ActionResult result = listener.onPlayerClick(clicker, target, hand, world);
                    if (result == ActionResult.SUCCESS) {
                        return ActionResult.SUCCESS; // Stop propagation once handled
                    }
                }
                return ActionResult.PASS; // Continue processing if not handled
            }
    );

    private final PlayerEntity clicker;
    private final PlayerEntity target;
    private final Hand hand;
    private final World world;

    public PlayerClickEvent(PlayerEntity clicker, PlayerEntity target, Hand hand, World world) {
        this.clicker = clicker;
        this.target = target;
        this.hand = hand;
        this.world = world;
    }

    public PlayerEntity getClicker() {
        return clicker;
    }

    public PlayerEntity getTarget() {
        return target;
    }

    public Hand getHand() {
        return hand;
    }

    public World getWorld() {
        return world;
    }

    public interface PlayerClickEventListener {
        ActionResult onPlayerClick(PlayerEntity clicker, PlayerEntity target, Hand hand, World world);
    }
}
