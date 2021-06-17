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

public class ModLootModiferProvider extends GlobalLootModifierProvider {

    public ModLootModiferProvider(DataGenerator gen) {
        super(gen, TutorialMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("silverfish_drop", ModLootModifiers.SILVER_FISH_DROPS.get(), new SilverfishDropModifier(
                new ILootCondition[] {
                        LootTableIdCondition.builder(new ResourceLocation("entities/silverfish")).build()
                },
                ModItems.SILVER_DUST.get(), 0.39F)
        );
    }
}
