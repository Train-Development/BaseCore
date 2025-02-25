package com.blonicx.basecore.api.hglabor;

import com.blonicx.basecore.BaseCore;
import com.blonicx.basecore.api.hglabor.enums.HGLaborGameModes;
import com.blonicx.basecore.api.hglabor.enums.ffa.FFAValues;
import com.blonicx.basecore.api.minecraft.client.utils.PlayerData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HGLaborStats {
    private static final String API_URL = "https://api.hglabor.de/stats/";

    // Helper method to fetch JSON from a URL
    private static String fetchJson(String urlString) throws IOException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlString);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                return "{\"error\": \"API request failed with status: " + responseCode + "\"}";
            }

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                StringBuilder response = new StringBuilder();
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
                return response.toString();
            }
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    public static JSONArray TopPlayers(FFAValues sort, HGLaborGameModes gameMode, int playerCount) throws IOException {
        String jsonResponse = fetchJson(API_URL + gameMode + "/top?sort=" + sort);
        JSONArray leaderboard = new JSONArray(jsonResponse);
        JSONArray topPlayers = new JSONArray();

        for (int i = 0; i < Math.min(playerCount, leaderboard.length()); i++) {
            topPlayers.put(leaderboard.getJSONObject(i));
        }
        return topPlayers;
    }

    public static JSONObject PlayerStats(String playerIdentifier, HGLaborGameModes gameMode) throws IOException {
        String uuid = PlayerData.isUUID(playerIdentifier) ? playerIdentifier : PlayerData.getUUIDFromUsername(playerIdentifier);
        BaseCore.LOGGER.info("UUID: " + uuid);

        String jsonResponse = fetchJson(API_URL + gameMode + "/" + uuid);
        return new JSONObject(jsonResponse);
    }

    public static String PlayerStat(String playerIdentifier, HGLaborGameModes gameMode, FFAValues sort) throws IOException {
        String uuid = PlayerData.isUUID(playerIdentifier) ? playerIdentifier : PlayerData.getUUIDFromUsername(playerIdentifier);
        BaseCore.LOGGER.info("UUID: " + uuid);

        String jsonResponse = fetchJson(API_URL + gameMode + "/" + uuid);
        JSONObject jsonData = new JSONObject(jsonResponse);

        return jsonData.optString(String.valueOf(sort), "Stat not found");
    }
}