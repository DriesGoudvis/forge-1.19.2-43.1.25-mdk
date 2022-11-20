package net.dries008.coolmod.item;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.dries008.coolmod.entity.ModEntityTypes;
import net.dries008.coolmod.fluid.ModFluids;
import net.dries008.coolmod.item.custom.EightBallItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoolMod.MOD_ID);


    public static final RegistryObject<Item> RADIOTRASH = ITEMS.register("radiotrash",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB)));

    public static final RegistryObject<Item> EIGHTBALL = ITEMS.register("eightball",
            () -> new EightBallItem(new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB).stacksTo(1)));

    public static final RegistryObject<Item> RADIOHEALCROPSEEDS = ITEMS.register("radiohealcropseeds",
            () -> new ItemNameBlockItem(ModBlocks.RADIOHEALCROP.get(),new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB)));

    public static final RegistryObject<Item> RADIOHEALCROPEAT = ITEMS.register("radiohealcropeat",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB)
                    .food(new FoodProperties.Builder().nutrition(5).saturationMod(5f).build())));

    public static final RegistryObject<Item> P_WATER_BUCKET = ITEMS.register("p_water_bucket",
        () -> new BucketItem(ModFluids.SOURCE_P_WATER,
                new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB).craftRemainder(Items.BUCKET).stacksTo(1)));

    public static final RegistryObject<Item> SUPERSWORD = ITEMS.register("supersword",
            () -> new SwordItem(Tiers.NETHERITE, 15, -3.0f,
                    new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB).stacksTo(1).durability(10000)));

    public static final RegistryObject<Item> SUPERAXE = ITEMS.register("superaxe",
            () -> new AxeItem(ModToolTiers.PROTIER, 9.0f, 0.0f,
                    new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB).stacksTo(1)));

    public static final RegistryObject<Item> SKULLER_SPAWN_EGG = ITEMS.register("skullerspawnegg",
            () -> new ForgeSpawnEggItem(ModEntityTypes.SKULLER, 0x640000, 0x211010,
                    new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB)));

    public static final RegistryObject<Item> SUPERPICKAXE = ITEMS.register("superpickaxe",
            () -> new PickaxeItem(ModToolTiers.PROTIER, 2, 0.0f,
                    new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB).stacksTo(1)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
