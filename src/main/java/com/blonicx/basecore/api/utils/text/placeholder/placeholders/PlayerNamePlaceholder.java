package com.blonicx.basecore.api.utils.text.placeholder.placeholders;

import com.blonicx.basecore.api.utils.text.placeholder.Placeholder;
import net.minecraft.client.MinecraftClient;

public class PlayerNamePlaceholder implements Placeholder {

    @Override
    public String getValue() {
        return MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getName().getString() : "Unknown";
    }
}