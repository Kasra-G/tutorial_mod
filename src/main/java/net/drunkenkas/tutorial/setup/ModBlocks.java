package net.drunkenkas.tutorial.setup;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

/**
 * This class contains all the blocks that this mod adds.
 */
public class ModBlocks {

    /** Silver ore. */
    public static final RegistryObject<Block> SILVER_ORE = register("silver_ore", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.STONE)
                    .strength(3, 3)
                    .harvestLevel(2)
                    .harvestTool(ToolType.PICKAXE)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    /** Silver block. */
    public static final RegistryObject<Block> SILVER_BLOCK = register("silver_block", () ->
            new Block(AbstractBlock.Properties
                    .of(Material.METAL)
                    .strength(5, 6)
                    .harvestLevel(0)
                    .harvestTool(ToolType.PICKAXE)
                    .sound(SoundType.METAL)));

    /**
     * Helper method for registering blocks without a corresponding item.
     *
     * @param name  the name of the block
     * @param block  the block to register
     * @param <T>  the type of block to register
     * @return  the RegistryObject of the block
     */
    private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<T> block) {
        return Registration.BLOCKS.register(name, block);
    }

    /**
     * Helper method for registering blocks and its corresponding item.
     *
     * @param name  the name of the block
     * @param block  the block to register
     * @param <T>  the type of block to register
     * @return  the RegistryObject of the block
     */
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        RegistryObject<T> rtn = registerNoItem(name, block);
        Registration.ITEMS.register(name, () -> new BlockItem(rtn.get(), new Item.Properties().tab(ItemGroup.TAB_BUILDING_BLOCKS)));
        return rtn;
    }

    /**
     * Classloading method.
     */
    public static void register() {}
}
