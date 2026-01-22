package net.jolene.ninetofiveessentials.item;

import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodComponent BAGUETTE = new FoodComponent.Builder().nutrition(18).saturationModifier(0.6F).build();
    public static final FoodComponent COFFEE_CHERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F).build();
    public static final FoodComponent COFFEE_GUM = new FoodComponent.Builder().nutrition(0).saturationModifier(0F).alwaysEdible().build();
    public static final FoodComponent VODKA = new FoodComponent.Builder().nutrition(6).saturationModifier(0.1F).alwaysEdible().build();
    public static final FoodComponent BEER = new FoodComponent.Builder().nutrition(6).saturationModifier(0.1F).alwaysEdible().build();
    public static final FoodComponent BROWNIE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();
    public static final FoodComponent FUNKY_BROWNIE = new FoodComponent.Builder().nutrition(5).saturationModifier(0.6F).build();

    public static final ConsumableComponent BROWNIE_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.SERENITY, 1200, 2), 1.0f)).build();
    public static final ConsumableComponent GUM_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(ModEffects.OVERCAFFEINATED, 1200, 0), 1.0f)).build();

}
