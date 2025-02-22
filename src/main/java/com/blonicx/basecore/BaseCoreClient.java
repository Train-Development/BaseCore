package com.blonicx.basecore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import com.blonicx.basecore.api.utils.placeholder.PlaceholderRegistry;

public class BaseCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Registering for ChatMessageEvent //
        ClientReceiveMessageEvents.CHAT.register((message, signed_message, sender, params, timestamp) -> {
            // Replace placeholders in the message //
            PlaceholderRegistry.processString(String.valueOf(message));
        });
    }
}
