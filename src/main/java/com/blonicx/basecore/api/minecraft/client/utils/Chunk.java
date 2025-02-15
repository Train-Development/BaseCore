package com.blonicx.basecore.api.minecraft.client.utils;

import net.minecraft.client.MinecraftClient;

public class Chunk {

    // Reloads all Client chunks in the world //
    public static void reloadChunks() {
        MinecraftClient.getInstance().worldRenderer.reload();
    }
}