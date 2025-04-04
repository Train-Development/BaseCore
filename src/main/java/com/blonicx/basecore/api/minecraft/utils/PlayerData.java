package com.blonicx.basecore.api.minecraft.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PlayerData {
    // Minecraft API Endpoint URLs //
    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final String USERNAME_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";

    // UUID Pattern //
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    // Format short UUID to Full UUID //
    private static String formatUUID(String shortUUID) {
        return shortUUID.replaceFirst(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5"
        );
    }

    // Check if input is in a valid UUID Pattern //
    public static boolean isUUID(String input) {
        return UUID_PATTERN.matcher(input).matches();
    }

    // Get UUID from Username //
    public static String getUUIDFromUsername(String username) throws IOException {
        //Set up the Connection //
        URL url = new URL(UUID_URL + username);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check if connection was successful //
        if (conn.getResponseCode() != 200) {
            return "Invalid username or user does not exist";
        }

        // Get data from Response //
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Return the Players UUID //
        JSONObject json = new JSONObject(response.toString());
        String shortUUID = json.getString("id");

        // Convert short UUID to full format
        return formatUUID(shortUUID);
    }

    // Get Username from UUID //
    public static String getUsernameFromUUID(String uuid) throws IOException {
        //Set up the Connection //
        URL url = new URL(USERNAME_URL + uuid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // Check if connection was successful //
        if (conn.getResponseCode() != 200) {
            return "Invalid UUID or user does not exist";
        }

        // Get data from Response //
        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        // Return the Players Username //
        JSONObject json = new JSONObject(response.toString());
        return json.getString("name");
    }
}
