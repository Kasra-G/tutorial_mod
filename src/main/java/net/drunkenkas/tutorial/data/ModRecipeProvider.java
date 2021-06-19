package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.setup.ModBlocks;
import net.drunkenkas.tutorial.setup.ModItems;
import net.drunkenkas.tutorial.setup.ModTags;
import net.minecraft.data.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Consumer;

/**
 * This class provides all the recipes.
 */
public class ModRecipeProvider extends RecipeProvider {

    /**
     * Constructs a new ModRecipeProvider.
     *
     * @param dataGenerator  the DataGenerator to use
     */
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    /**
     * Builds all the recipes for this mod.
     * Although named {@code buildShapelessRecipes}, it actually builds all the recipes.
     *
     * @param consumer  the Consumer for the finished recipes
     */
    @Override
    @ParametersAreNonnullByDefault
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

        //build the shapeless recipes
        ShapelessRecipeBuilder.shapeless(ModItems.SILVER_INGOT.get(), 9)
                .requires(ModTags.Items.STORAGE_BLOCKS_SILVER)
                .unlockedBy("has_item", has(ModTags.Items.STORAGE_BLOCKS_SILVER))
                .save(consumer, modId("silver_ingot_crafting_block"));

        ShapelessRecipeBuilder.shapeless(ModItems.SILVER_NUGGET.get(), 9)
                .requires(ModTags.Items.INGOTS_SILVER)
                .unlockedBy("has_item", has(ModTags.Items.NUGGETS_SILVER))
                .save(consumer, modId("silver_nugget_crafting_ingot"));

        buildShapedRecipes(consumer);
        buildSmeltingRecipes(consumer);
    }

    /**
     * Helper method that builds all the smelting recipes.
     *
     * @param consumer  the Consumer for the finished recipes
     */
    private void buildSmeltingRecipes(Consumer<IFinishedRecipe> consumer) {
        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 200)
                .unlockedBy("has_item", has(ModTags.Items.ORES_SILVER))
                .save(consumer, modId("silver_ingot_smelting_ore"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 100)
                .unlockedBy("has_item", has(ModTags.Items.ORES_SILVER))
                .save(consumer, modId("silver_ingot_blasting_ore"));
        CookingRecipeBuilder.smelting(Ingredient.of(ModTags.Items.DUSTS_SILVER), ModItems.SILVER_INGOT.get(), 0.3f, 200)
                .unlockedBy("has_item", has(ModTags.Items.DUSTS_SILVER))
                .save(consumer, modId("silver_ingot_smelting_dust"));
    }

    /**
     * Helper method for building the shaped recipes.
     *
     * @param consumer  the Consumer for the finished recipes
     */
    private void buildShapedRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer, modId("silver_block_crafting_ingot"));

        ShapedRecipeBuilder.shaped(ModItems.SILVER_INGOT.get(), 1)
                .define('#', ModTags.Items.NUGGETS_SILVER)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModTags.Items.NUGGETS_SILVER))
                .save(consumer, modId("silver_ingot_crafting_nugget"));

        buildSilverToolRecipes(consumer);
        buildSilverArmorRecipes(consumer);
    }

    /**
     * Helper method for building the silver armor recipes.
     *
     * @param consumer  the Consumer for the finished recipes
     */
    private void buildSilverArmorRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.SILVER_HELMET.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .pattern("   ")
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_CHESTPLATE.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_LEGGINGS.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_BOOTS.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .pattern("   ")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);
    }

    /**
     * Helper method for building the silver tool recipes.
     *
     * @param consumer  the Consumer for the finished recipes
     */
    private void buildSilverToolRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.SILVER_AXE.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern("## ")
                .pattern("#| ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_PICKAXE.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_SHOVEL.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_HOE.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern("## ")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_SWORD.get(), 1)
                .define('#', ModTags.Items.INGOTS_SILVER)
                .define('|', Tags.Items.RODS_WOODEN)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModTags.Items.INGOTS_SILVER))
                .save(consumer);
    }

    /**
     * Helper method that helps make a unique ResourceLocation for recipes.
     *
     * @param path  the name of the location
     * @return  the created ResourceLocation
     */
    private ResourceLocation modId(String path) {
        return new ResourceLocation(TutorialMod.MOD_ID, path);
    }
}
