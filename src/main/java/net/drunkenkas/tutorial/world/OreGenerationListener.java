package net.drunkenkas.tutorial.world;

import net.drunkenkas.tutorial.setup.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;

import javax.annotation.Nonnull;

/**
 * This class contains the methods for generating ores
 */
public class OreGenerationListener {

    /**
     * Register the events of this class.
     */
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGenerationListener::generateOres);
    }

    /**
     * Generates ores.
     *
     * @param event  the BiomeLoadingEvent to use
     */
    public static void generateOres(@Nonnull BiomeLoadingEvent event) {

        //generate silver ore only in the overworld
        if (!(event.getCategory().equals(Biome.Category.NETHER)
                || event.getCategory().equals(Biome.Category.THEEND))) {

            generateOre(event,
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    ModBlocks.SILVER_ORE.get().defaultBlockState(),
                    7, 5, 24, 2);
        }
    }

    /**
     * Helper method for generating ores.
     *
     * @param event  the BiomeLoadingEvent to use
     * @param fillerType  the type of stone to fill with
     * @param state  the block state to generate
     * @param veinSize  the size of the ore veins
     * @param min  the minimum ore spawn height
     * @param max  the maximum ore spawn height
     * @param amount  the amount of times to spawn the ore per chunk
     */
    private static void generateOre(@Nonnull BiomeLoadingEvent event, RuleTest fillerType, BlockState state, int veinSize, int min, int max, int amount) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
                        .configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(min, 0, max)))
                        .squared()
                        .count(amount));
    }
}
