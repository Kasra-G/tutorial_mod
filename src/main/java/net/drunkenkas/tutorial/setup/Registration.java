package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.entity.LivingListener;
import net.drunkenkas.tutorial.world.OreGenerationListener;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class registers everything added by the mod.
 */
public class Registration {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> GLOBAL_LOOT_MODIFIER = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, TutorialMod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);

    /**
     * Register all the events, items, blocks, effects, and modifiers.
     */
    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        GLOBAL_LOOT_MODIFIER.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModEffects.load();
        ModLootModifiers.register();

        LivingListener.registerEvents();
        OreGenerationListener.registerEvents();

    }
}
