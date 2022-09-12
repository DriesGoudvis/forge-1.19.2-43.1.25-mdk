package net.dries008.coolmod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab COOLMOD_TAB = new CreativeModeTab("coolmodtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TRASH.get());
        }
    };
}
