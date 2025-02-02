package com.blonicx.basecore;

import com.blonicx.basecore.api.utils.text.placeholder.PlaceholderRegistry;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseCore implements ModInitializer {
	public static final String MOD_ID = "basecore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("BaseCore initialized.");
		PlaceholderRegistry.loadPlaceholdersFromDirectory("./api/utils/text/placeholders");
	}
}