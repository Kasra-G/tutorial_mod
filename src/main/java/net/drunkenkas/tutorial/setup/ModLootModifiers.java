package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.modifier.SilverfishDropModifier;
import net.minecraftforge.fml.RegistryObject;

public class ModLootModifiers {
    public static final RegistryObject<SilverfishDropModifier.Serializer> SILVER_FISH_DROPS =
            Registration.GLOBAL_LOOT_MODIFIER.register("silverfish_drop", SilverfishDropModifier.Serializer::new);

    public static void register() {}
}
