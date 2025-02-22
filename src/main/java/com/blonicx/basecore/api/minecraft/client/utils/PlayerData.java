package com.blonicx.basecore.api.minecraft.client.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PlayerData {
    private static final String UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
    private static final String USERNAME_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

    private static String formatUUID(String shortUUID) {
        return shortUUID.replaceFirst(
                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                "$1-$2-$3-$4-$5"
        );
    }

    public static boolean isUUID(String input) {
        return UUID_PATTERN.matcher(input).matches();
    }

    public static String getUUIDFromUsername(String username) throws IOException {
        URL url = new URL(UUID_URL + username);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (conn.getResponseCode() != 200) {
            return "Invalid username or user does not exist";
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject json = new JSONObject(response.toString());
        String shortUUID = json.getString("id");

        // Convert short UUID to full format
        return formatUUID(shortUUID);
    }

    public static String getUsernameFromUUID(String uuid) throws IOException {
        URL url = new URL(USERNAME_URL + uuid);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        if (conn.getResponseCode() != 200) {
            return "Invalid UUID or user does not exist";
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        JSONObject json = new JSONObject(response.toString());
        return json.getString("name");
    }
}
