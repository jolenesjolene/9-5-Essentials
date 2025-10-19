package net.jolene.ninetofiveessentials;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModItemGroups;
import net.jolene.ninetofiveessentials.item.ModItemTooltips;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.jolene.ninetofiveessentials.particle.ModParticles;
import net.jolene.ninetofiveessentials.potion.ModPotions;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.jolene.ninetofiveessentials.util.ModLootTableModifiers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
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
		ModPotions.registerModPotions();
		ModEffects.registerEffects();
		ModSounds.registerModSounds();
		ModParticles.registerModParticles();
		ModLootTableModifiers.registerLootTableModifiers();
		ModItemTooltips.registerModItemTooltips();
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.registerPotionRecipe(Potions.WATER, ModItems.COFFEE_BEANS, ModPotions.COFFEE);
		});
		CompostingChanceRegistry.INSTANCE.add(ModItems.COFFEE_CHERRIES, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.HEMP_LEAVES, 0.5f);
		CompostingChanceRegistry.INSTANCE.add(ModItems.HEMP_SEEDS, 0.25f);
		LOGGER.info("Very Dirty!");
	}
}