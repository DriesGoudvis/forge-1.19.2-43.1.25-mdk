package net.dries008.coolmod.item;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static Tier PROTIER;
    static{
        PROTIER = TierSortingRegistry.registerTier(
                new ForgeTier(5, 3000, 15.0f, 6.0f, 30,
                        ModTags.Blocks.NEEDS_PROTIER_TOOL, ()-> Ingredient.of(Items.IRON_INGOT)),
                new ResourceLocation(CoolMod.MOD_ID, "protier"), List.of(Tiers.NETHERITE), List.of());
    }
}
