package com.blonicx.basecore.api.hglabor;

import com.blonicx.basecore.BaseCore;
import com.blonicx.basecore.api.hglabor.enums.ffa.FFAPlayStyles;
import com.blonicx.basecore.api.minecraft.utils.PlayerData;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class HGLaborFFA {
    private static final String API_URL = "https://api.hglabor.de/ffa/";

    // Inventory //
    public static JSONObject PlayerInventory(String PlayerIdentifier, FFAPlayStyles PlayStyle) throws IOException {
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
        URL url = new URL(API_URL + "/inventory/" + UUID + "/" + PlayStyle);
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
}
