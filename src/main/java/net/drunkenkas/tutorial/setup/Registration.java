package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.TutorialMod;
import net.drunkenkas.tutorial.entity.Util;
import net.drunkenkas.tutorial.world.OreGenerationListener;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, TutorialMod.MOD_ID);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        FLUIDS.register(modEventBus);

        ModBlocks.register();
        ModItems.register();
        ModEffects.load();
        Util.registerEvents();

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, OreGenerationListener::generateOres);
    }
}
