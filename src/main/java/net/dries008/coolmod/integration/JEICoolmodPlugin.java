package net.dries008.coolmod.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.recipe.PrecisionOperationTableRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEICoolmodPlugin implements IModPlugin {

    public static RecipeType<PrecisionOperationTableRecipe> PRECISION_TYPE =
            new RecipeType<>(PrecisionOperationTableRecipeCategory.UID, PrecisionOperationTableRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(CoolMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration){
        registration.addRecipeCategories(new
                PrecisionOperationTableRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration){
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<PrecisionOperationTableRecipe> recipeList = rm.getAllRecipesFor(PrecisionOperationTableRecipe.Type.INSTANCE);
        registration.addRecipes(PRECISION_TYPE, recipeList);
    }
}
