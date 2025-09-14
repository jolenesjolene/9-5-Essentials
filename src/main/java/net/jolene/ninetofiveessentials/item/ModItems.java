package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.item.custom.CigaretteItem;
import net.jolene.ninetofiveessentials.item.custom.LitCigaretteItem;
import net.jolene.ninetofiveessentials.sound.ModSounds;
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
    public static final Item CRUMPLED_CIGARETTE = registerItem("crumpled_cigarette", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"crumpled_cigarette")))));
    public static final Item TAR_GLOB = registerItem("tar_glob", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tar_glob")))));
    public static final Item TAR_BRICK = registerItem("tar_brick", new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"tar_brick")))));
    public static final Item BAGUETTE = registerItem("baguette", new Item(new Item.Settings().food(ModFoodComponents.BAGUETTE)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"baguette")))));
    public static final Item LONGER_BAGUETTE = registerItem("longer_baguette", new Item(new Item.Settings().food(ModFoodComponents.LONGER_BAGUETTE)
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"longer_baguette")))));
    public static final Item DARK_IS_THE_NIGHT_MUSIC_DISC = registerItem("dark_is_the_night_music_disc",
            new Item(new Item.Settings().jukeboxPlayable(ModSounds.DARK_IS_THE_NIGHT_KEY)
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID,"dark_is_the_night_music_disc"))).maxCount(1)));

    // DON'T DELETE
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NineToFiveEssentials.MOD_ID, name), item);
    }

    public static void registerModItems() {
        NineToFiveEssentials.LOGGER.info("Registering Items for " + NineToFiveEssentials.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(CIGARETTE);
            entries.add(CRUMPLED_CIGARETTE);
            entries.add(BAGUETTE);
            entries.add(LONGER_BAGUETTE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(TAR_GLOB);
            entries.add(TAR_BRICK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> entries.add(DARK_IS_THE_NIGHT_MUSIC_DISC));
    }
}
