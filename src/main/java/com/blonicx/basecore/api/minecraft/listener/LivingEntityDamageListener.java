package com.blonicx.basecore.api.minecraft.listener;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

import java.util.WeakHashMap;
import java.util.function.BiConsumer;

public class LivingEntityDamageListener {
    private static final WeakHashMap<LivingEntity, Float> lastHealthMap = new WeakHashMap<>();

    public static void tick(BiConsumer<LivingEntity, Float> onDamage) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world != null) {
            for (var entity : client.world.getEntities()) {
                if (entity instanceof LivingEntity living) {
                    float currentHealth = living.getHealth();
                    float lastHealth = lastHealthMap.getOrDefault(living, currentHealth);

                    if (currentHealth < lastHealth) {
                        float damage = lastHealth - currentHealth;
                        onDamage.accept(living, damage);
                    }

                    lastHealthMap.put(living, currentHealth);
                }
            }
        }
    }
}
