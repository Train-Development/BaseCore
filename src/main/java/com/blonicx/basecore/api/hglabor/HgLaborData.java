package com.blonicx.basecore.api.hglabor;

import com.blonicx.basecore.BaseCore;
import com.blonicx.basecore.api.minecraft.client.utils.PlayerData;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HgLaborData {
    private static final String API_URL = "https://api.hglabor.de/stats/";

    public static JSONObject PlayerStats(String PlayerIdentifier, String GameMode) throws IOException {
        String UUID;

        if (PlayerData.isUUID(PlayerIdentifier)) {
            UUID = PlayerIdentifier;
        }
        else {
            UUID = PlayerData.getUUIDFromUsername(PlayerIdentifier);
        }

        BaseCore.LOGGER.info("UUID: " + UUID);

        URL url = new URL(API_URL + "/" + GameMode + "/" + UUID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (conn.getResponseCode() != 200) {
            return new JSONObject().put("error", "Error connecting to HgLabor API");
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONObject(response.toString());
    }
}
