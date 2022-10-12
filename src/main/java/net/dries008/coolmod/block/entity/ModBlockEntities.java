package net.dries008.coolmod.block.entity;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CoolMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<PrecisionOperationTableEntity>> PRECISION_OPERATION_TABLE =
            BLOCK_ENTITIES.register("precision_operation_table", () ->
                    BlockEntityType.Builder.of(PrecisionOperationTableEntity::new,
                            ModBlocks.PRECISION_OPERATION_TABLE.get()).build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
