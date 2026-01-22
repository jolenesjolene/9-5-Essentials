package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.item.custom.*;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item CIGARETTE = registerItem("cigarette", new CigaretteItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"cigarette"))).maxCount(1)));
    public static final Item LIT_CIGARETTE = registerItem("lit_cigarette", new LitCigaretteItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"lit_cigarette"))).maxCount(1).maxDamage(6)));

    public static final Item FUNKY_CIGARETTE = registerItem("funky_cigarette", new FunkyCigaretteItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"funky_cigarette"))).maxCount(1)));
    public static final Item LIT_FUNKY_CIGARETTE = registerItem("lit_funky_cigarette", new LitFunkyCigaretteItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"lit_funky_cigarette"))).maxCount(1).maxDamage(6)));


    public static final Item CIGARETTE_BUTT = registerItem("cigarette_butt", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"cigarette_butt"))).maxCount(16)));
    public static final Item TAR_GLOB = registerItem("tar_glob", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tar_glob")))));
    public static final Item TAR_BRICK = registerItem("tar_brick", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tar_brick")))));
    public static final Item BAGUETTE = registerItem("baguette", new Item(new Item.Settings().food(ModFoodComponents.BAGUETTE)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"baguette")))));
    public static final Item VODKA = registerItem("vodka", new Item(new Item.Settings().food(ModFoodComponents.VODKA)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"vodka"))).maxCount(1)));
    public static final Item BEER = registerItem("beer", new Item(new Item.Settings().food(ModFoodComponents.BEER)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"beer"))).maxCount(3)));

    public static final Item HEMP_SEEDS = registerItem("hemp_seeds", new BlockItem(ModBlocks.HEMP_PLANT, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"hemp_seeds")))));

    public static final Item HEMP_LEAVES = registerItem("hemp_leaves", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"hemp_leaves")))));

    public static final Item DRIED_HEMP = registerItem("dried_hemp", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"dried_hemp")))));

    public static final Item BROWNIE = registerItem("brownie", new Item(new Item.Settings().food(ModFoodComponents.BROWNIE)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"brownie")))));
    public static final Item FUNKY_BROWNIE = registerItem("funky_brownie", new Item(new Item.Settings().food(ModFoodComponents.FUNKY_BROWNIE,ModFoodComponents.BROWNIE_EFFECT)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"funky_brownie")))));



    public static final Item COFFEE_CHERRIES = registerItem("coffee_cherries", new BlockItem(ModBlocks.COFFEE_BUSH, new Item.Settings().food(ModFoodComponents.COFFEE_CHERRIES)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"coffee_cherries")))));
    public static final Item COFFEE_BEANS = registerItem("coffee_beans", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"coffee_beans")))));
    public static final Item COFFEE_GUM = registerItem("coffee_gum", new CoffeeGumItem(new Item.Settings().food(ModFoodComponents.COFFEE_GUM, ModFoodComponents.GUM_EFFECT)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"coffee_gum")))));

    public static final Item DARK_IS_THE_NIGHT_MUSIC_DISC = registerItem("dark_is_the_night_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.DARK_IS_THE_NIGHT_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"dark_is_the_night_music_disc"))).maxCount(1)));
    public static final Item FUNKY_CIGARETTE_MUSIC_DISC = registerItem("funky_cigarette_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.FUNKY_CIGARETTE_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"funky_cigarette_music_disc"))).maxCount(1)));
    public static final Item BERET = registerItem("beret", new BeretItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"beret"))).maxCount(1)));


    public static final Item ROBOS = registerItem("robos", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"robos"))).maxCount(16)));
    public static final Item CRUSHED_TABLETS = registerItem("crushed_tablets", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"crushed_tablets"))).maxCount(16)));
    public static final Item ROBO_GELS = registerItem("robo_gels", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"gel_tablets"))).maxCount(16)));

    public static final Item ALD = registerItem("ald", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"ald"))).maxCount(1)));
    public static final Item FUNKY_FUNGI = registerItem("funky_fungi", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"funky_fungi"))).maxCount(64)));
    public static final Item FUNKY_FUNGI_STEW = registerItem("funky_fungi_stew", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"funky_fungi_stew"))).maxCount(64)));

    public static final Item TABLET_BOTTLE = registerItem("tablet_bottle", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tablet_bottle"))).maxCount(1)));








    public static final Item MINTED_COIN = registerItem("minted_coin", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"minted_coin")))));

    public static final Item PLAIN_POKER_CHIP = registerItem("plain_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"plain_poker_chip")))));
    public static final Item WHITE_POKER_CHIP = registerItem("white_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"white_poker_chip")))));
    public static final Item RED_POKER_CHIP = registerItem("red_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"red_poker_chip")))));
    public static final Item ORANGE_POKER_CHIP = registerItem("orange_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"orange_poker_chip")))));
    public static final Item YELLOW_POKER_CHIP = registerItem("yellow_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"yellow_poker_chip")))));
    public static final Item GREEN_POKER_CHIP = registerItem("green_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"green_poker_chip")))));
    public static final Item BLACK_POKER_CHIP = registerItem("black_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"black_poker_chip")))));
    public static final Item PURPLE_POKER_CHIP = registerItem("purple_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"purple_poker_chip")))));
    public static final Item MAROON_POKER_CHIP = registerItem("maroon_poker_chip", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"maroon_poker_chip")))));

    // DON'T DELETE
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NineToFiveEssentials.LOGGER.info("Registering Items for " + NineToFiveEssentials.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(CIGARETTE);
            entries.add(CIGARETTE_BUTT);
            entries.add(COFFEE_CHERRIES);
            entries.add(COFFEE_GUM);
            entries.add(BAGUETTE);
            entries.add(VODKA);
            entries.add(BEER);
            entries.add(BROWNIE);
            entries.add(FUNKY_BROWNIE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(BERET);
            entries.add(MINTED_COIN);
            entries.add(WHITE_POKER_CHIP);
            entries.add(RED_POKER_CHIP);
            entries.add(ORANGE_POKER_CHIP);
            entries.add(YELLOW_POKER_CHIP);
            entries.add(GREEN_POKER_CHIP);
            entries.add(BLACK_POKER_CHIP);
            entries.add(PURPLE_POKER_CHIP);
            entries.add(MAROON_POKER_CHIP);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TAR_GLOB);
            entries.add(TAR_BRICK);
            entries.add(COFFEE_BEANS);
            entries.add(HEMP_LEAVES);
            entries.add(DRIED_HEMP);;
            entries.add(PLAIN_POKER_CHIP);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(DARK_IS_THE_NIGHT_MUSIC_DISC);
            entries.add(FUNKY_CIGARETTE_MUSIC_DISC);
        });
    }
}
