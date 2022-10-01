package net.dries008.coolmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_COOLMOD = "key.category.coolmod.coolmod";
    public static final String KEY_JUMP_VERY_HIGH = "key.coolmod.jump_very_high";

    public static final KeyMapping JUMPING_HIGH_KEY =new KeyMapping(KEY_JUMP_VERY_HIGH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_COOLMOD);
}
