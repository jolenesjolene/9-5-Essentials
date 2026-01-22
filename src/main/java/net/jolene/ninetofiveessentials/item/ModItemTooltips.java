package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.minecraft.item.Items;
import net.minecraft.text.Text;

public class ModItemTooltips {
    public static void register() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isOf(ModItems.CIGARETTE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.cigarette"));
            }
            if (itemStack.isOf(ModItems.LIT_CIGARETTE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.lit_cigarette"));
            }
            if (itemStack.isOf(ModItems.FUNKY_CIGARETTE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.funky_cigarette"));
            }
            if (itemStack.isOf(ModItems.LIT_FUNKY_CIGARETTE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.lit_funky_cigarette"));
            }
            if (itemStack.isOf(ModItems.COFFEE_GUM)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.coffee_gum"));
            }
            if (itemStack.isOf(ModItems.FUNKY_BROWNIE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.funky_brownie"));
            }
            if (itemStack.isOf(ModBlocks.FIVE_HUNDRED_CIGARETTES.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.five_hundred_cigarettes"));
            }
            if (itemStack.isOf(ModItems.BERET)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.beret"));
            }
            if (itemStack.isOf(ModItems.COIN)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.coin"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModBlocks.DICE.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModBlocks.SLOT_MACHINE.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.slot_machine"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.WHITE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.white_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.RED_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.red_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.ORANGE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.orange_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.YELLOW_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.yellow_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.GREEN_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.green_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.BLACK_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.black_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.PURPLE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.purple_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }
            if (itemStack.isOf(ModItems.MAROON_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.maroon_poker_chip"));
                list.add(Text.translatable("tooltip.ninetofiveessentials.gambling"));
            }

        });
    }

    public static void registerModItemTooltips() {
        NineToFiveEssentials.LOGGER.info("Registering Tooltips for " + NineToFiveEssentials.MOD_ID);
        register();
    }
}

