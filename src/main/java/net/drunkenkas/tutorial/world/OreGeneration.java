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
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class OreGeneration {
    public static void generateOres(final BiomeLoadingEvent event) {
        if (!(event.getCategory().equals(Biome.Category.NETHER) ||
                event.getCategory().equals(Biome.Category.THEEND))) {
            generateOre(event,
                    OreFeatureConfig.FillerBlockType.NATURAL_STONE,
                    ModBlocks.SILVER_ORE.get().defaultBlockState(),
                    7, 5, 24, 2);

        }
    }

    private static void generateOre(BiomeLoadingEvent event, RuleTest fillerType, BlockState state, int veinSize, int min, int max, int amount) {
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
                        .configured(new OreFeatureConfig(fillerType, state, veinSize))
                        .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(min, 0, max)))
                        .squared()
                        .count(amount));
    }
}
