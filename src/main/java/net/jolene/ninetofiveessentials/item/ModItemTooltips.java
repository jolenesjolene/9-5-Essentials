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
            if (itemStack.isOf(Items.COOKIE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.sweet_treat"));
            }
            if (itemStack.isOf(Items.CAKE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.sweet_treat"));
            }
            if (itemStack.isOf(Items.PUMPKIN_PIE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.sweet_treat"));
            }
            if (itemStack.isOf(Items.COOKIE)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.sweet_treat"));
            }
            if (itemStack.isOf(ModBlocks.FIVE_HUNDRED_CIGARETTES.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.five_hundred_cigarettes"));
            }
            if (itemStack.isOf(ModItems.BERET)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.beret"));
            }
            if (itemStack.isOf(ModBlocks.SLOT_MACHINE.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.slot_machine"));
            }
            if (itemStack.isOf(ModBlocks.COIN.asItem())) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.coin"));
            }
            if (itemStack.isOf(ModItems.MINTED_COIN)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.minted_coin"));
            }
            if (itemStack.isOf(ModItems.WHITE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.white_poker_chip"));
            }
            if (itemStack.isOf(ModItems.RED_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.red_poker_chip"));
            }
            if (itemStack.isOf(ModItems.ORANGE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.orange_poker_chip"));
            }
            if (itemStack.isOf(ModItems.YELLOW_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.yellow_poker_chip"));
            }
            if (itemStack.isOf(ModItems.GREEN_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.green_poker_chip"));
            }
            if (itemStack.isOf(ModItems.BLACK_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.black_poker_chip"));
            }
            if (itemStack.isOf(ModItems.PURPLE_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.purple_poker_chip"));
            }
            if (itemStack.isOf(ModItems.MAROON_POKER_CHIP)) {
                list.add(Text.translatable("tooltip.ninetofiveessentials.maroon_poker_chip"));
            }
        });
    }

    public static void registerModItemTooltips() {
        NineToFiveEssentials.LOGGER.info("Registering Tooltips for " + NineToFiveEssentials.MOD_ID);
        register();
    }
}

