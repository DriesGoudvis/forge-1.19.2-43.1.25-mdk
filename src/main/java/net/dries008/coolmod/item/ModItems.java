package net.dries008.coolmod.item;

import net.dries008.coolmod.CoolMod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CoolMod.MOD_ID);

    public static final RegistryObject<Item> TRASH = ITEMS.register("trash",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.COOLMOD_TAB)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
