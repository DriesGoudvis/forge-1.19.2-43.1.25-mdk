package net.dries008.coolmod.util;

import net.dries008.coolmod.CoolMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> NEEDS_PROTIER_TOOL = tag("needs_protier_tool");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(CoolMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name){
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }
}
