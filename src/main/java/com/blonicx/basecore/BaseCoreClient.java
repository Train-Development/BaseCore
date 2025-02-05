package com.blonicx.basecore;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;
import com.blonicx.basecore.api.utils.text.placeholder.PlaceholderRegistry;

public class BaseCoreClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientReceiveMessageEvents.CHAT.register((message, signed_message, sender, params, timestamp) -> {
            PlaceholderRegistry.processString(String.valueOf(message));
        });
    }
}
