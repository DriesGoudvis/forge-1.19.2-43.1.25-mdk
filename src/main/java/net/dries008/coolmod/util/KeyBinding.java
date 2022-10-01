package net.dries008.coolmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_COOLMOD = "key.category.coolmod.coolmod";
    public static final String TEST = "key.coolmod.test";
    public static final String DRINK = "key.coolmod.drink";


    public static final KeyMapping TESTER =new KeyMapping(TEST, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_0, KEY_CATEGORY_COOLMOD);

    public static final KeyMapping DRINKING =new KeyMapping(DRINK, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_COOLMOD);
}
