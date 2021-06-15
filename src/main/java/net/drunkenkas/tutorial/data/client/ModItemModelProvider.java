package net.drunkenkas.tutorial.data.client;

import net.drunkenkas.tutorial.TutorialMod;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("silver_block", modLoc("block/silver_block"));
        withExistingParent("silver_ore", modLoc("block/silver_ore"));

        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));
        buildTexture(itemHandheld, "silver_pickaxe");
        buildTexture(itemHandheld, "silver_axe");
        buildTexture(itemHandheld, "silver_shovel");
        buildTexture(itemHandheld, "silver_hoe");
        buildTexture(itemHandheld, "silver_sword");

        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        buildTexture(itemGenerated, "silver_boots");
        buildTexture(itemGenerated, "silver_leggings");
        buildTexture(itemGenerated, "silver_chestplate");
        buildTexture(itemGenerated, "silver_helmet");

        buildTexture(itemGenerated, "silver_ingot");
        buildTexture(itemGenerated, "silver_nugget");
        buildTexture(itemGenerated, "silver_dust");
    }

    private ItemModelBuilder buildTexture(ModelFile itemGenerated, String name) {
        return getBuilder(name).parent(itemGenerated).texture("layer0", "item/" + name);
    }
}
