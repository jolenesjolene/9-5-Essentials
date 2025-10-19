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
                            entries.add(ModItems.TAR_GLOB);
                            entries.add(ModItems.TAR_BRICK);
                            entries.add(ModBlocks.TAR_BRICKS);
                            entries.add(ModBlocks.TAR_BRICK_STAIRS);
                            entries.add(ModBlocks.TAR_BRICK_SLAB);
                            entries.add(ModItems.FUNKY_CIGARETTE);
                            entries.add(ModItems.HEMP_SEEDS);
                            entries.add(ModItems.HEMP_LEAVES);
                            entries.add(ModItems.DRIED_HEMP);
                            ItemStack coffeePotion = PotionContentsComponent.createStack(Items.POTION, ModPotions.COFFEE);
                            entries.add(coffeePotion);
                            ItemStack splashCoffeePotion = PotionContentsComponent.createStack(Items.SPLASH_POTION, ModPotions.COFFEE);
                            entries.add(splashCoffeePotion);
                            ItemStack lingeringCoffeePotion = PotionContentsComponent.createStack(Items.LINGERING_POTION, ModPotions.COFFEE);
                            entries.add(lingeringCoffeePotion);
                            ItemStack coffeeArrow = PotionContentsComponent.createStack(Items.TIPPED_ARROW, ModPotions.COFFEE);
                            entries.add(coffeeArrow);
                            entries.add(ModItems.COFFEE_BEANS);
                            entries.add(ModItems.COFFEE_CHERRIES);
                            entries.add(ModItems.BERET);
                            entries.add(ModItems.BAGUETTE);
                            entries.add(ModItems.DARK_IS_THE_NIGHT_MUSIC_DISC);
                            entries.add(ModItems.FUNKY_CIGARETTE_MUSIC_DISC);
                            entries.add(ModBlocks.BRITNEY);

                        }).build());
    public static final ItemGroup NINE_TO_FIVE_WIP_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(NineToFiveEssentials.MOD_ID, "nine_to_five_essentials_wip"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.SLOT_MACHINE))
                    .displayName(Text.translatable("itemgroup.ninetofiveessentials.ninetofiveessentials_wip"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.VODKA);
                        entries.add(ModItems.BEER);
                        entries.add(ModBlocks.SLOT_MACHINE);
                        entries.add(ModBlocks.DICE);
                        entries.add(ModBlocks.MINTING_TABLE);
                        entries.add(ModBlocks.COIN);
                        entries.add(ModItems.MINTED_COIN);
                        entries.add(ModBlocks.BLOCK_OF_COINS);
                        entries.add(ModItems.PLAIN_POKER_CHIP);
                        entries.add(ModItems.WHITE_POKER_CHIP);
                        entries.add(ModItems.RED_POKER_CHIP);
                        entries.add(ModItems.ORANGE_POKER_CHIP);
                        entries.add(ModItems.YELLOW_POKER_CHIP);
                        entries.add(ModItems.GREEN_POKER_CHIP);
                        entries.add(ModItems.BLACK_POKER_CHIP);
                        entries.add(ModItems.PURPLE_POKER_CHIP);
                        entries.add(ModItems.MAROON_POKER_CHIP);
                        entries.add(ModBlocks.BOWLING_PIN);
                        entries.add(ModBlocks.DART_BOARD);
                    }).build());



    public static void registerItemGroups() {
        NineToFiveEssentials.LOGGER.info("Registering Item Group for " + NineToFiveEssentials.MOD_ID);
    }
}
