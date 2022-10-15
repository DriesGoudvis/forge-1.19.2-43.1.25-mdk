package net.dries008.coolmod.recipe;

import net.dries008.coolmod.CoolMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, CoolMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PrecisionOperationTableRecipe>> PRECISION_OPERATION_SERIALIZER =
            SERIALIZERS.register("precision_operation", () -> PrecisionOperationTableRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
