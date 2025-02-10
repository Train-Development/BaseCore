package com.blonicx.basecore.api.minecraft.client.events.blocks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public class SignChangeEvent {
    public static final Event<SignChangeEventListener> SIGN_CHANGE = EventFactory.createArrayBacked(SignChangeEvent.SignChangeEventListener.class, (listeners) -> (text) -> {
        for (SignChangeEvent.SignChangeEventListener listener : listeners) {
            listener.onSignChange(text);
        }
    });

    private final String text;

    public SignChangeEvent(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public interface SignChangeEventListener {
        void onSignChange(String text);
    }
}
