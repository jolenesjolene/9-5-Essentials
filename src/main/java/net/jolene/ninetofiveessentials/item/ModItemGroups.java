package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
        public static final ItemGroup NINE_TO_FIVE_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
                Identifier.of(NineToFiveEssentials.MOD_ID, "nine_to_five_essentials"),
                FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.CIGARETTE))
                        .displayName(Text.translatable("itemgroup.ninetofiveessentials.ninetofiveessentials"))
                        .entries((displayContext, entries) -> {

                            entries.add(ModItems.CIGARETTE);
                            entries.add(ModItems.CRUMPLED_CIGARETTE);
                            entries.add(ModBlocks.FIVE_HUNDRED_CIGARETTES);
                            entries.add(ModItems.BAGUETTE);
                            entries.add(ModItems.LONGER_BAGUETTE);
                            entries.add(ModItems.TAR_GLOB);
                            entries.add(ModItems.TAR_BRICK);
                            entries.add(ModBlocks.TAR_BRICKS);
                            entries.add(ModItems.DARK_IS_THE_NIGHT_MUSIC_DISC);


                        }).build());




    public static void registerItemGroups() {
        NineToFiveEssentials.LOGGER.info("Registering Item Group for " + NineToFiveEssentials.MOD_ID);
    }
}
