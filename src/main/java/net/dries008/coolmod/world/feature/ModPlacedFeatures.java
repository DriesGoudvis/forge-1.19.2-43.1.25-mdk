package net.dries008.coolmod.world.feature;

import net.dries008.coolmod.CoolMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, CoolMod.MOD_ID);


    public static final RegistryObject<PlacedFeature> SUPERACTIVETRASHPLACED = PLACED_FEATURES.register("superactivetrashplaced",
            () -> new PlacedFeature(ModConfiguredFeatures.SUPERACTIVETRASH.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(90)))));

    public static final RegistryObject<PlacedFeature> ENDSUPERACTIVETRASHPLACED = PLACED_FEATURES.register("endsuperactivetrashplaced",
            () -> new PlacedFeature(ModConfiguredFeatures.ENDSUPERACTIVETRASH.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(90)))));

    public static final RegistryObject<PlacedFeature> NETHERSUPERACTIVETRASHPLACED = PLACED_FEATURES.register("nethersuperactivetrashplaced",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHERSUPERACTIVETRASH.getHolder().get(),
                    commonOrePlacement(3,
                            HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(90)))));



    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
