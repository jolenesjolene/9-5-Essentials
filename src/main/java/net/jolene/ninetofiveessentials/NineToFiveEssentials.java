package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ModInitializer;

import net.jolene.ninetofiveessentials.item.ModItemGroups;
import net.jolene.ninetofiveessentials.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NineToFiveEssentials implements ModInitializer {
	public static final String MOD_ID = "ninetofiveessentials";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		LOGGER.info("Very Dirty!");
	}
}