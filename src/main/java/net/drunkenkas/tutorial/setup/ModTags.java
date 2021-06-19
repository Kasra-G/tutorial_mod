package net.drunkenkas.tutorial.setup;

import net.drunkenkas.tutorial.TutorialMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

/**
 * This class registers all the tags added by this mod.
 */
public class ModTags {

    /**
     * Subclass for registering tags for blocks.
     */
    public static final class Blocks {
        public static final ITag.INamedTag<Block> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Block> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");

        private static @Nonnull ITag.INamedTag<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static @Nonnull ITag.INamedTag<Block> mod(String path) {
            return BlockTags.bind(new ResourceLocation(TutorialMod.MOD_ID, path).toString());
        }
    }

    /**
     * Subclass for registering tags for items.
     */
    public static final class Items {
        public static final ITag.INamedTag<Item> ORES_SILVER = forge("ores/silver");
        public static final ITag.INamedTag<Item> STORAGE_BLOCKS_SILVER = forge("storage_blocks/silver");

        public static final ITag.INamedTag<Item> INGOTS_SILVER = forge("ingots/silver");
        public static final ITag.INamedTag<Item> NUGGETS_SILVER = forge("nuggets/silver");
        public static final ITag.INamedTag<Item> DUSTS_SILVER = forge("dusts/silver");

        private static @Nonnull ITag.INamedTag<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static @Nonnull ITag.INamedTag<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(TutorialMod.MOD_ID, path).toString());
        }
    }
}
