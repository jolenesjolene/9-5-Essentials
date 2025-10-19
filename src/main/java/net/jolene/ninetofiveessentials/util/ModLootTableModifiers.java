package net.jolene.ninetofiveessentials.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.LootTables;

public class ModLootTableModifiers {
    public static void registerLootTableModifiers() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            // Only modify built‑in tables (not user data packs, etc.)
            if (source.isBuiltin() && LootTables.SIMPLE_DUNGEON_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(ItemEntry.builder(ModItems.HEMP_SEEDS))
                        .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1.0f, 3.0f)
                        ));

                tableBuilder.pool(poolBuilder);
            }

        });
        LootTableEvents.MODIFY.register((key, tableBuilder, source) -> {
            // Only modify built‑in tables (not user data packs, etc.)
            if (source.isBuiltin() && LootTables.ABANDONED_MINESHAFT_CHEST.equals(key)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.25f))
                        .with(ItemEntry.builder(ModItems.HEMP_SEEDS))
                        .apply(SetCountLootFunction.builder(
                                UniformLootNumberProvider.create(1.0f, 3.0f)
                        ));

                tableBuilder.pool(poolBuilder);
            }
        });
    }
}