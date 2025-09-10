package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ModInitializer;

import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModItemGroups;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NineToFiveEssentials implements ModInitializer {
	public static final String MOD_ID = "ninetofiveessentials";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModEffects.registerEffects();
		ModSounds.registerModSounds();
		LOGGER.info("Very Dirty!");
	}
}