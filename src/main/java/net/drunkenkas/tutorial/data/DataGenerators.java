package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.data.client.ModBlockStateProvider;
import net.drunkenkas.tutorial.data.client.ModItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(new ModBlockStateProvider(generator, existingFileHelper));
        generator.addProvider(new ModItemModelProvider(generator, existingFileHelper));

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(generator, existingFileHelper);
        generator.addProvider(blockTags);
        generator.addProvider(new ModItemTagsProvider(generator, blockTags, existingFileHelper));

        generator.addProvider(new ModLootTableProvider(generator));
        generator.addProvider(new ModRecipeProvider(generator));
    }
}
