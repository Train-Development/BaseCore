package com.blonicx.basecore.api.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ModData {
    public static boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    public static String getModVersion(String modId) {
        return FabricLoader.getInstance().getModContainer(modId)
                .map(modContainer -> modContainer.getMetadata().getVersion().getFriendlyString())
                .orElse("unknown");
    }
}
