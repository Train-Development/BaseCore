package com.blonicx.basecore.api.utils.social;

import com.blonicx.basecore.BaseCore;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Discord {
    public static void SendWebhook(String message, String webhookUrl) {
        try {
            URL url = new URL(webhookUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            String jsonPayload = "{\"content\": \"" + message + "\"}";

            try (DataOutputStream dos = new DataOutputStream(con.getOutputStream())) {
                dos.write(jsonPayload.getBytes());
                dos.flush();
            }

            int responseCode = con.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                BaseCore.LOGGER.warn("Failed to send webhook message. Response code: " + responseCode +".");
            }

            con.disconnect();
        } catch (Exception e) {
            BaseCore.LOGGER.warn("Error sending webhook message: " + e.getMessage() + ".");
        }
    }
}
