package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.setup.ModBlocks;
import net.drunkenkas.tutorial.setup.ModItems;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.SILVER_INGOT.get(), 9)
                .requires(ModBlocks.SILVER_BLOCK.get())
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer, modId("silver_ingot_crafting_block"));

        ShapelessRecipeBuilder.shapeless(ModItems.SILVER_NUGGET.get(), 9)
                .requires(ModItems.SILVER_INGOT.get())
                .unlockedBy("has_item", has(ModItems.SILVER_NUGGET.get()))
                .save(consumer, modId("silver_nugget_crafting_ingot"));

        ShapedRecipeBuilder.shaped(ModBlocks.SILVER_BLOCK.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer, modId("silver_block_crafting_ingot"));

        ShapedRecipeBuilder.shaped(ModItems.SILVER_INGOT.get(), 1)
                .define('#', ModItems.SILVER_NUGGET.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", has(ModItems.SILVER_NUGGET.get()))
                .save(consumer, modId("silver_ingot_crafting_nugget"));

        createSilverToolRecipes(consumer);

        CookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 200)
                .unlockedBy("has_item", has(ModBlocks.SILVER_ORE.get()))
                .save(consumer, modId("silver_ingot_smelting_ore"));
        CookingRecipeBuilder.blasting(Ingredient.of(ModBlocks.SILVER_ORE.get()), ModItems.SILVER_INGOT.get(), 0.7f, 100)
                .unlockedBy("has_item", has(ModBlocks.SILVER_ORE.get()))
                .save(consumer, modId("silver_ingot_blasting_ore"));
    }

    private void createSilverToolRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.SILVER_AXE.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .define('|', Items.STICK)
                .pattern("## ")
                .pattern("#| ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_PICKAXE.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .define('|', Items.STICK)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_SHOVEL.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .define('|', Items.STICK)
                .pattern(" # ")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_HOE.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .define('|', Items.STICK)
                .pattern("## ")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.SILVER_SWORD.get(), 1)
                .define('#', ModItems.SILVER_INGOT.get())
                .define('|', Items.STICK)
                .pattern(" # ")
                .pattern(" # ")
                .pattern(" | ")
                .unlockedBy("has_item", has(ModItems.SILVER_INGOT.get()))
                .save(consumer);
    }


    private ResourceLocation modId(String path) {
        return new ResourceLocation(TutorialMod.MOD_ID, path);
    }
}
