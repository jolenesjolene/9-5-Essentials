package net.jolene.ninetofiveessentials.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.custom.NicotianaPlantBlock;
import net.jolene.ninetofiveessentials.block.custom.NicotianaTopBlock;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModBlocks {
    public static final Block FIVE_HUNDRED_CIGARETTES = registerBlock("five_hundred_cigarettes",
            AbstractBlock.Settings.copy(Blocks.BLACK_WOOL));

    public static final Block TAR_GLOB_BLOCK = registerBlock("tar_glob_block",
            AbstractBlock.Settings.copy(Blocks.SLIME_BLOCK));

    public static final Block BLOCK_OF_COINS = registerBlock("block_of_coins",
            AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK));

    public static final Block TAR_BRICKS = registerBlock("tar_bricks",
            AbstractBlock.Settings.copy(Blocks.BRICKS));

    public static final Block NICOTIANA_PLANT = registerBlockWithoutBlockItem("nicotiana_plant",
            properties -> new NicotianaPlantBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block NICOTIANA_TOP = registerBlockWithoutBlockItem("nicotiana_top",
            properties -> new NicotianaTopBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));



    private static Block registerBlock(String name, AbstractBlock.Settings blockSettings) {
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name));
        Block block = new Block(blockSettings.registryKey(key));
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, key, block);
    }
    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(key));
        Registry.register(Registries.ITEM, key, item);
    }
    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        NineToFiveEssentials.LOGGER.info("Registering Blocks for " + NineToFiveEssentials.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.FIVE_HUNDRED_CIGARETTES);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.TAR_GLOB_BLOCK);
            entries.add(ModBlocks.TAR_BRICKS);
            entries.add(ModBlocks.BLOCK_OF_COINS);
        });
    }
}
