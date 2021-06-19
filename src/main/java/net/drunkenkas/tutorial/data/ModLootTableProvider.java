package net.drunkenkas.tutorial.data;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.drunkenkas.tutorial.setup.ModBlocks;
import net.drunkenkas.tutorial.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.data.loot.BlockLootTables;
import net.minecraft.loot.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * This class provides all the extra LootTables for this mod.
 */
public class ModLootTableProvider extends LootTableProvider {

    /**
     * Instantiates a new ModLootTableProvider.
     *
     * @param dataGenerator  the DataGenerator to use
     */
    public ModLootTableProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    /**
     * Returns the List of LootTables added paired with the appropriate LootParameterSet.
     *
     * @return  the ImmutableList of LootTables paired with the appropriate LootParameterSet.
     */
    @Override
    @Nonnull
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    /**
     * Validates each LootTable and ResourceLocation.
     *
     * @param map  the map of ResourceLocations and LootTables.
     * @param validationtracker  the ValidationTracker to use
     */
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, @Nonnull ValidationTracker validationtracker) {
        map.forEach((resLoc, lootTable) -> LootTableManager.validate(validationtracker, resLoc, lootTable));
    }

    /**
     * This class provides the LootTables added for Blocks.
     */
    public static class ModBlockLootTables extends BlockLootTables {

        /**
         * Generates the LootTables for each Block.
         */
        @Override
        protected void addTables() {
            dropSelf(ModBlocks.SILVER_BLOCK.get());
            dropSelf(ModBlocks.SILVER_ORE.get());
        }

        /**
         * Returns the list of registered ModBlocks.
         *
         * @return the list of registered ModBLocks.
         */
        @Override
        @Nonnull
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
