package com.blonicx.basecore.api.minecraft.client;

import net.minecraft.client.MinecraftClient;

public class ChunkAPI {

    // Reloads all Client chunks in the world //
    public static void reloadChunks() {
        MinecraftClient.getInstance().worldRenderer.reload();
    }
}
