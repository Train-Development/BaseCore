package com.blonicx.basecore.api.utils.social;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.ITwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.events.channel.ChannelMessageEvent;

public class Twitch {

    public interface MessageListener {
        void onMessageReceived(String message);
    }

    public static void getChatMessages(String OAuthToken, String ChannelName, MessageListener listener) {
        ITwitchClient twitchClient = TwitchClientBuilder.builder()
                .withDefaultAuthToken(new OAuth2Credential("twitch", OAuthToken))
                .withEnableChat(true)
                .withDefaultEventHandler(SimpleEventHandler.class)
                .build();

        twitchClient.getChat().joinChannel(ChannelName);
        twitchClient.getEventManager().onEvent(ChannelMessageEvent.class, event -> {
            listener.onMessageReceived(event.getMessage());
        });
    }
}