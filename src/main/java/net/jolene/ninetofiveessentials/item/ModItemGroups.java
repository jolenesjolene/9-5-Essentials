package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.potion.ModPotions;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
                            entries.add(ModItems.CIGARETTE_BUTT);
                            entries.add(ModBlocks.FIVE_HUNDRED_CIGARETTES);
                            ItemStack coffeePotion = PotionContentsComponent.createStack(Items.POTION, ModPotions.COFFEE);
                            entries.add(coffeePotion);
                            ItemStack splashCoffeePotion = PotionContentsComponent.createStack(Items.SPLASH_POTION, ModPotions.COFFEE);
                            entries.add(splashCoffeePotion);
                            ItemStack lingeringCoffeePotion = PotionContentsComponent.createStack(Items.LINGERING_POTION, ModPotions.COFFEE);
                            entries.add(lingeringCoffeePotion);
                            ItemStack coffeeArrow = PotionContentsComponent.createStack(Items.TIPPED_ARROW, ModPotions.COFFEE);
                            entries.add(coffeeArrow);
                            entries.add(ModItems.NICOTIANA_SEEDS);
                            entries.add(ModItems.TOBACCO_LEAVES);
                            entries.add(ModItems.DRIED_TOBACCO);
                            entries.add(ModItems.COFFEE_CHERRIES);
                            entries.add(ModItems.COFFEE_BEANS);
                            entries.add(ModItems.BAGUETTE);
                            entries.add(ModItems.TAR_GLOB);
                            entries.add(ModBlocks.TAR_GLOB_BLOCK);
                            entries.add(ModItems.TAR_BRICK);
                            entries.add(ModBlocks.TAR_BRICKS);
                            entries.add(ModItems.DARK_IS_THE_NIGHT_MUSIC_DISC);
                            entries.add(ModItems.BERET);
                            entries.add(ModItems.COIN);
                            entries.add(ModBlocks.BLOCK_OF_COINS);


                        }).build());




    public static void registerItemGroups() {
        NineToFiveEssentials.LOGGER.info("Registering Item Group for " + NineToFiveEssentials.MOD_ID);
    }
}
