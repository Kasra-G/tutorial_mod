package net.drunkenkas.tutorial.data;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.modifier.SilverfishDropModifier;
import net.drunkenkas.tutorial.setup.ModItems;
import net.drunkenkas.tutorial.setup.ModLootModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

/**
 * This class contains all the loot modifications done.
 */
public class ModLootModiferProvider extends GlobalLootModifierProvider {

    /**
     * Instantiates this LootModifierProvider.
     *
     * @param gen the generator to use
     */
    public ModLootModiferProvider(DataGenerator gen) {
        super(gen, TutorialMod.MOD_ID);
    }

    /**
     * Adds all the loot modifications.
     *
     * Modified loot: <p>
     *     Silverfish drop silver dust on death.
     * </p>
     */
    @Override
    protected void start() {

        //make silverfish drop silver dust with a 39% chance
        add("silverfish_drop", ModLootModifiers.SILVER_FISH_DROPS.get(), new SilverfishDropModifier(
                new ILootCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("entities/silverfish")).build()
                },
                ModItems.SILVER_DUST.get(), 0.39F)
        );
    }
}
