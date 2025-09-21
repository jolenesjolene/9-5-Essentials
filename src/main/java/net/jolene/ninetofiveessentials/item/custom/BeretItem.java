package net.jolene.ninetofiveessentials.item.custom;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BeretItem extends Item {
    public BeretItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        EquipmentSlot slot = EquipmentSlot.HEAD;

        if (user.getEquippedStack(slot).isEmpty()) {
            if (!world.isClient()) {
                user.equipStack(slot, stack.copy());

                if (!user.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }

            return ItemUsage.consumeHeldItem(world, user, hand);
        }

        return ActionResult.FAIL;
    }
}


