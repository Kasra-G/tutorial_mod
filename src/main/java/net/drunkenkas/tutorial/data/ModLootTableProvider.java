package net.drunkenkas.tutorial.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.drunkenkas.tutorial.setup.ModBlocks;
import net.drunkenkas.tutorial.setup.ModItems;
import net.drunkenkas.tutorial.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.KilledByPlayer;
import net.minecraft.loot.functions.LootingEnchantBonus;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    @Nonnull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
        map.forEach((a, b) -> LootTableManager.validate(validationtracker, a, b));
    }
    /*
    public static class ModEntityLootTables extends EntityLootTables {
        @Override
        protected void addTables() {
            this.add(EntityType.SILVERFISH, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(ConstantRange.exactly(1))
                            .add(ItemLootEntry.lootTableItem(ModBlocks.SILVER_BLOCK.get().asItem())
                                    .apply(SetCount.setCount(RandomValueRange.between(0.0F, 1.0F)))
                                    .apply(LootingEnchantBonus.lootingMultiplier(RandomValueRange.between(0.0F, 1.0F)))
                            )
                            .when(KilledByPlayer.killedByPlayer())
                    ));
        }
    }
    */

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.SILVER_BLOCK.get());
            dropSelf(ModBlocks.SILVER_ORE.get());
        }

        @Override
        @Nonnull
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
