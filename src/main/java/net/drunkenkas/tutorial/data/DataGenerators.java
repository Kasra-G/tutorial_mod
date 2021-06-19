package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.data.client.ModBlockStateProvider;
import net.drunkenkas.tutorial.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

/**
 * This class uses the GatherDataEvent to generate all the JSON files required.
 * These Data Generators ensure that no JSON files will need to be handwritten
 */
@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    /**
     * Private constructor so that no classes can generate an instance of this class.
     */
    private DataGenerators() {}

    /**
     * Event handler that generates all the JSON files needed for the mod to function.
     *
     * @param event  the GatherDataEvent to run on
     */
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //generate BlockStates and ItemModels
        generator.addProvider(new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));

        //generate Tags
        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(blockTags);
        generator.addProvider(new ModItemTagsProvider(generator, blockTags, existingFileHelper));

        //generate LootTables and LootModifiers
        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModLootModiferProvider(generator));

        //generate recipes
        generator.addProvider(new ModRecipeProvider(generator));
    }
}
