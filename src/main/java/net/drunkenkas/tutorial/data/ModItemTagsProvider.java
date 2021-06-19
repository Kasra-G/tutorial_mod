package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.setup.ModItems;
import net.drunkenkas.tutorial.setup.ModTags;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;

/**
 * This class provides all the Tags for the Items in this mod.
 */
public class ModItemTagsProvider extends ItemTagsProvider {

    /**
     * Instantiates a new ModItemTagsProvider.
     *
     * @param dataGenerator  the DataGenerator to use
     * @param blockTagsProvider  the BlockTagsProvider to generate ItemTags from
     * @param existingFileHelper  the ExistingFileHelper to use
     */
    public ModItemTagsProvider(DataGenerator dataGenerator, BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, blockTagsProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    /**
     * Adds the tags to the Items.
     * Also copies Block tags to Item tags
     */
    @Override
    protected void addTags() {

        //copy Block tags to Item tags
        copy(ModTags.Blocks.ORES_SILVER, ModTags.Items.ORES_SILVER);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(ModTags.Blocks.STORAGE_BLOCKS_SILVER, ModTags.Items.STORAGE_BLOCKS_SILVER);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);

        //tag each Item in the mod that requires tags
        tag(ModTags.Items.INGOTS_SILVER).add(ModItems.SILVER_INGOT.get());
        tag(Tags.Items.INGOTS).addTag(ModTags.Items.INGOTS_SILVER);
        tag(ModTags.Items.NUGGETS_SILVER).add(ModItems.SILVER_NUGGET.get());
        tag(Tags.Items.NUGGETS).addTag(ModTags.Items.NUGGETS_SILVER);
        tag(ModTags.Items.DUSTS_SILVER).add(ModItems.SILVER_DUST.get());
        tag(Tags.Items.DUSTS).addTag(ModTags.Items.DUSTS_SILVER);
    }
}
