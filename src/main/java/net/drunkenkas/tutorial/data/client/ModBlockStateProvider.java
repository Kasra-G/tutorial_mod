package net.drunkenkas.tutorial.data.client;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.setup.ModBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * This class provides all the block states for every ModBlock.
 */
public class ModBlockStateProvider extends BlockStateProvider {

    /**
     * Constructs a new ModBlockStateProvider
     *
     * @param gen  the DataGenerator to use.
     * @param exFileHelper  the ExistingFileHelper to use
     */
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, TutorialMod.MOD_ID, exFileHelper);
    }

    /**
     * Provide all the Block models for every ModBlock
     */
    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.SILVER_BLOCK.get());
        simpleBlock(ModBlocks.SILVER_ORE.get());
    }
}
