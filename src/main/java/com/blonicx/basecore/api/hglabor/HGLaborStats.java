package com.blonicx.basecore.api.hglabor;

import com.blonicx.basecore.BaseCore;
import com.blonicx.basecore.api.hglabor.enums.stats.HGLaborGameModes;
import com.blonicx.basecore.api.hglabor.enums.stats.HGLaborValues;
import com.blonicx.basecore.api.minecraft.client.utils.PlayerData;
import org.json.JSONObject;
import org.json.JSONArray;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HGLaborStats {
    private static final String API_URL = "https://api.hglabor.de/stats/";

    public static JSONArray TopPlayers(HGLaborValues Sort, HGLaborGameModes GameMode, int PlayerCount) throws IOException {
        //Set up the Connection //
        URL url = new URL(API_URL + "/" + GameMode + "/top?sort=" + Sort);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check if connection was successful //
        if (conn.getResponseCode() != 200) {
            return new JSONArray().put(new JSONObject().put("error", "Error connecting to HgLabor API"));
        }

        // Get data from Response //
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        JSONArray leaderboard = new JSONArray(response.toString());

        // Extract the top Players //
        JSONArray topPlayers = new JSONArray();
        for (int i = 0; i < Math.min(PlayerCount, leaderboard.length()); i++) {
            topPlayers.put(leaderboard.getJSONObject(i));
        }

        // Close the Connection //
        conn.disconnect();

        // Return the Top Players //
        return topPlayers;
    }


    public static JSONObject PlayerStats(String PlayerIdentifier, HGLaborGameModes GameMode) throws IOException {
        String UUID;

        // Convert PlayerIdentifier to UUID if it is a Username //
        if (PlayerData.isUUID(PlayerIdentifier)) {
            UUID = PlayerIdentifier;
        }
        else {
            UUID = PlayerData.getUUIDFromUsername(PlayerIdentifier);
        }

        // Log the UUID //
        BaseCore.LOGGER.info("UUID: " + UUID);

        //Set up the Connection //
        URL url = new URL(API_URL + "/" + GameMode + "/" + UUID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check if connection was successful //
        if (conn.getResponseCode() != 200) {
            return new JSONObject().put("error", "Error connecting to HgLabor API");
        }

        // Get data from Response //
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Close the Connection //
        conn.disconnect();

        // Return the Players Stats //
        return new JSONObject(response.toString());
    }

    public static String PlayerStat(String PlayerIdentifier, HGLaborGameModes GameMode, HGLaborValues Sort) throws IOException {
        String UUID;

        // Convert PlayerIdentifier to UUID if it is a Username //
        if (PlayerData.isUUID(PlayerIdentifier)) {
            UUID = PlayerIdentifier;
        }
        else {
            UUID = PlayerData.getUUIDFromUsername(PlayerIdentifier);
        }

        // Log the UUID //
        BaseCore.LOGGER.info("UUID: " + UUID);

        //Set up the Connection //
        URL url = new URL(API_URL + "/" + GameMode + "/" + UUID);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check if connection was successful //
        if (conn.getResponseCode() != 200) {
            return "Error connecting to HgLabor API";
        }

        // Get data from Response //
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Extract the Value //
        JSONObject JSONData = new JSONObject(response.toString());
        String Value = JSONData.get(Sort.toString()).toString();

        // Close the Connection //
        conn.disconnect();

        // Return the Value //
        return Value;
    }
}
