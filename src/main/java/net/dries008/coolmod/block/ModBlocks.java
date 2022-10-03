package net.dries008.coolmod.block;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.custom.LaucherBlock;
import net.dries008.coolmod.block.custom.Superactivetrash;
import net.dries008.coolmod.block.custom.radiohealcrop;
import net.dries008.coolmod.fluid.ModFluids;
import net.dries008.coolmod.item.ModCreativeModeTab;
import net.dries008.coolmod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CoolMod.MOD_ID);

    public static final RegistryObject<Block> COMPRESSED_TRASH = registerBlock("compressed_trash",
            () -> new Block(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(30f).requiresCorrectToolForDrops()),
                    ModCreativeModeTab.COOLMOD_TAB);

    public static final RegistryObject<Block> RADIOTRASHBLOCK = registerBlock("radiotrashblock",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops(),
                    UniformInt.of(100,500)), ModCreativeModeTab.COOLMOD_TAB);

    public static final RegistryObject<Block> JUMPBLOCK = registerBlock("jumpblock",
            () -> new LaucherBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.COOLMOD_TAB);

    public static final RegistryObject<Block> SUPERACTIVETRASH = registerBlock("superactivetrash",
            () -> new Superactivetrash(BlockBehaviour.Properties.of(Material.HEAVY_METAL)
                    .strength(30f).requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(Superactivetrash.LIT) ? 15 : 3)), ModCreativeModeTab.COOLMOD_TAB);

    public static final RegistryObject<Block> RADIOHEALCROP = BLOCKS.register("radiohealcrop",
            () -> new radiohealcrop(BlockBehaviour.Properties.copy(Blocks.WHEAT)));

    public static final RegistryObject<LiquidBlock> P_WATER_BLOCK = BLOCKS.register("p_water_block",
            () -> new LiquidBlock(ModFluids.SOURCE_P_WATER, BlockBehaviour.Properties.copy(Blocks.WATER)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
