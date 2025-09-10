package net.jolene.ninetofiveessentials.item;

import net.minecraft.component.type.FoodComponent;

public class ModFoodComponents {
    public static final FoodComponent BAGUETTE = new FoodComponent.Builder().nutrition(12).saturationModifier(1F).build();
    public static final FoodComponent LONGER_BAGUETTE = new FoodComponent.Builder().nutrition(20).saturationModifier(1F).build();
}
