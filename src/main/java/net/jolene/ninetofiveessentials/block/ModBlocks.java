package net.jolene.ninetofiveessentials.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.custom.*;
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

    public static final Block BRITNEY = registerBlock("britney",
            AbstractBlock.Settings.copy(Blocks.BLACK_WOOL));

    public static final Block FIVE_HUNDRED_CIGARETTES = registerBlock("five_hundred_cigarettes",
            AbstractBlock.Settings.copy(Blocks.BLACK_WOOL));

    public static final Block BLOCK_OF_COINS = registerBlock("block_of_coins",
            AbstractBlock.Settings.copy(Blocks.GOLD_BLOCK));

    public static final Block TAR_BRICKS = registerBlock("tar_bricks",
            AbstractBlock.Settings.copy(Blocks.BRICKS));

    public static final Block TAR_BRICK_SLAB = registerBlock("tar_brick_slab",
            properties -> new SlabBlock(properties.strength(2f).requiresTool()));
    public static final Block TAR_BRICK_STAIRS = registerBlock("tar_brick_stairs",
            properties -> new SlabBlock(properties.strength(2f).requiresTool()));


    public static final Block NICOTIANA_PLANT = registerBlockWithoutBlockItem("nicotiana_plant",
            properties -> new NicotianaPlantBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block MINTING_TABLE = registerBlock("minting_table",
            AbstractBlock.Settings.copy(Blocks.SMITHING_TABLE));

    public static final Block COIN = registerBlockWithItem("coin",
            settings -> new CoinLayerBlock(
                    settings
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.LANTERN)
                    .nonOpaque()
                    .noCollision()  // Snow layers have no collision on top layers, you can tweak this
            )
    );

    public static final Block DICE = registerBlockWithItem("dice",
            settings -> new DiceBlock(
                    settings
                            .strength(0.0f)  // Instantly breakable
                            .sounds(BlockSoundGroup.BONE)
                            .nonOpaque()
            ));
    public static final Block BOWLING_PIN = registerBlockWithItem("bowling_pin",
            settings -> new BowlingPinBlock(
                    settings
                            .strength(0.0f)  // Instantly breakable
                            .sounds(BlockSoundGroup.BAMBOO)
                            .nonOpaque()

            ));
    public static final Block DART_BOARD = registerBlockWithItem("dart_board",
            settings -> new DartBoardBlock(
                    settings
                            .strength(0.0f)  // Instantly breakable
                            .sounds(BlockSoundGroup.BAMBOO)
                            .nonOpaque()

            ));
    public static final Block SLOT_MACHINE = registerBlockWithItem("slot_machine",
            settings -> new SlotMachineBlock(
                    settings
                            .strength(3.0f)
                            .sounds(BlockSoundGroup.IRON)
                            .nonOpaque()
                            .requiresTool()
            ));

    private static Block registerBlockWithItem(String name, Function<AbstractBlock.Settings, Block> blockFactory) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name));
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().registryKey(blockKey);

        Block block = blockFactory.apply(settings);
        Registry.register(Registries.BLOCK, blockKey, block);

        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID, name));
        BlockItem item = new BlockItem(block, new Item.Settings().registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);

        return block;
    }

    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(NineToFiveEssentials.MOD_ID, name), toRegister);
    }
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
            entries.add(ModBlocks.SLOT_MACHINE);
            entries.add(ModBlocks.DICE);
            entries.add(ModBlocks.BOWLING_PIN);
            entries.add(ModBlocks.DART_BOARD);
            entries.add(ModBlocks.MINTING_TABLE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.TAR_BRICKS);
            entries.add(ModBlocks.TAR_BRICK_STAIRS);
            entries.add(ModBlocks.TAR_BRICK_SLAB);
            entries.add(ModBlocks.COIN);
            entries.add(ModBlocks.BLOCK_OF_COINS);
        });
    }
}
