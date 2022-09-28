package net.dries008.coolmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, CoolMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLDSUPERACTIVETRASH = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.SUPERACTIVETRASH.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.SUPERACTIVETRASH.get().defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> ENDTRASHORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), ModBlocks.SUPERACTIVETRASH.get().defaultBlockState())
    ));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHERTRASHORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.SUPERACTIVETRASH.get().defaultBlockState())
    ));


    public static final RegistryObject<ConfiguredFeature<?,?>> SUPERACTIVETRASH = CONFIGURED_FEATURE.register("superactivetrash",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLDSUPERACTIVETRASH.get(), 7)));
    public static final RegistryObject<ConfiguredFeature<?,?>> NETHERSUPERACTIVETRASH = CONFIGURED_FEATURE.register("endtrashores",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(ENDTRASHORES.get(), 7)));
    public static final RegistryObject<ConfiguredFeature<?,?>> ENDSUPERACTIVETRASH = CONFIGURED_FEATURE.register("nethertrashores",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHERTRASHORES.get(), 7)));

    public static void register(IEventBus eventBus){
        CONFIGURED_FEATURE.register(eventBus);
    }
}
