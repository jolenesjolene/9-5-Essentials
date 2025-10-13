package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.item.custom.BeretItem;
import net.jolene.ninetofiveessentials.item.custom.CigaretteItem;
import net.jolene.ninetofiveessentials.item.custom.LitCigaretteItem;
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
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"lit_cigarette"))).maxCount(1).maxDamage(10)));
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

    public static final Item NICOTIANA_SEEDS = registerItem("nicotiana_seeds", new BlockItem(ModBlocks.NICOTIANA_PLANT, new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"nicotiana_seeds")))));

    public static final Item TOBACCO_LEAVES = registerItem("tobacco_leaves", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tobacco_leaves")))));
    public static final Item DRIED_TOBACCO = registerItem("dried_tobacco", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"dried_tobacco")))));

    public static final Item COFFEE_CHERRIES = registerItem("coffee_cherries", new Item(new Item.Settings().food(ModFoodComponents.COFFEE_CHERRIES)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"coffee_cherries")))));
    public static final Item COFFEE_BEANS = registerItem("coffee_beans", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"coffee_beans")))));

    public static final Item DARK_IS_THE_NIGHT_MUSIC_DISC = registerItem("dark_is_the_night_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.DARK_IS_THE_NIGHT_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"dark_is_the_night_music_disc"))).maxCount(1)));
    public static final Item CAN_CAN_MUSIC_DISC = registerItem("can_can_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.CAN_CAN_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"can_can_music_disc"))).maxCount(1)));
    public static final Item BERET = registerItem("beret", new BeretItem(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"beret"))).maxCount(1)));

    public static final Item MINTED_COIN = registerItem("minted_coin", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"minted_coin")))));

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
            entries.add(BAGUETTE);
            entries.add(VODKA);
            entries.add(BEER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(BERET);
            entries.add(MINTED_COIN);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TAR_GLOB);
            entries.add(TAR_BRICK);
            entries.add(COFFEE_BEANS);
            entries.add(TOBACCO_LEAVES);
            entries.add(DRIED_TOBACCO);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(NICOTIANA_SEEDS);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(DARK_IS_THE_NIGHT_MUSIC_DISC);
            entries.add(CAN_CAN_MUSIC_DISC);
        });
    }
}
