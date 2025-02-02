package com.blonicx.basecore.api.utils.text.placeholder.placeholders;

import com.blonicx.basecore.api.utils.text.placeholder.Placeholder;
import net.minecraft.client.MinecraftClient;

public class PlayerUUIDPlaceholder implements Placeholder {

    @Override
    public String getValue() {
        return MinecraftClient.getInstance().player != null ? MinecraftClient.getInstance().player.getUuidAsString() : "Unknown";
    }
}
