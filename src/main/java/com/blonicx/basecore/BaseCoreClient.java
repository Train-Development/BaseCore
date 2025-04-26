package com.blonicx.basecore;

import com.blonicx.basecore.api.minecraft.events.entity.friendly.cow.CowDamageEvent;
import com.blonicx.basecore.api.minecraft.events.entity.friendly.pig.PigDamageEvent;
import com.blonicx.basecore.api.minecraft.events.entity.friendly.sheep.SheepDamageEvent;
import com.blonicx.basecore.api.minecraft.events.entity.player.PlayerClickEvent;
import com.blonicx.basecore.api.minecraft.events.entity.player.PlayerDamageEvent;
import com.blonicx.basecore.api.minecraft.listener.LivingEntityDamageListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BaseCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registering for LivingEntityDamageListener //
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            LivingEntityDamageListener.tick((entity, damage) -> {
                World world = entity.getWorld();

                // Check for specific entity types and invoke responsive events //
                if (entity instanceof PlayerEntity player) {
                    PlayerDamageEvent.PLAYER_DAMAGE.invoker().onPlayerDamaged(player, world, damage);
                } else if (entity instanceof CowEntity cow) {
                    CowDamageEvent.COW_DAMAGE.invoker().onCowDamaged(cow, world, damage);
                } else if (entity instanceof PigEntity pig) {
                    PigDamageEvent.PIG_DAMAGE.invoker().onPigDamaged(pig, world, damage);
                } else if (entity instanceof SheepEntity sheep) {
                    SheepDamageEvent.SHEEP_DAMAGE.invoker().onSheepDamaged(sheep, world, damage);
                }
            });
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
