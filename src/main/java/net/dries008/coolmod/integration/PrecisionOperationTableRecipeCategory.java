package net.dries008.coolmod.integration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.dries008.coolmod.recipe.PrecisionOperationTableRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class PrecisionOperationTableRecipeCategory implements IRecipeCategory<PrecisionOperationTableRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(CoolMod.MOD_ID, "precision_operation");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(CoolMod.MOD_ID, "textures/gui/precision_operation_table_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public PrecisionOperationTableRecipeCategory(IGuiHelper helper){
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.PRECISION_OPERATION_TABLE.get()));
    }

    @Override
    public RecipeType<PrecisionOperationTableRecipe> getRecipeType() {
        return JEICoolmodPlugin.PRECISION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Precision operation table");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PrecisionOperationTableRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
