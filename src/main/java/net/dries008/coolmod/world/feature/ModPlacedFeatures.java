package net.dries008.coolmod.world.feature;

import net.dries008.coolmod.CoolMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CoolMod.MOD_ID);

    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
