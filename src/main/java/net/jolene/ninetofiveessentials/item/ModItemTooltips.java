package net.jolene.ninetofiveessentials.item;

import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.minecraft.text.Text;

public class ModItemTooltips {
    public static void register() {
        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
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
        });
    }

    public static void registerModItemTooltips() {
        NineToFiveEssentials.LOGGER.info("Registering Tooltips for " + NineToFiveEssentials.MOD_ID);
        register();
    }
}

