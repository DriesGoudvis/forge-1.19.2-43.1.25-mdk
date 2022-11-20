package net.dries008.coolmod.entity;

import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.entity.custom.SkullerEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CoolMod.MOD_ID);


    public static final RegistryObject<EntityType<SkullerEntity>> SKULLER =
            ENTITY_TYPES.register("skuller",
                    () -> EntityType.Builder.of(SkullerEntity::new, MobCategory.MONSTER)
                            .sized(0.4f, 1.5f)
                            .build(new ResourceLocation(CoolMod.MOD_ID, "skuller").toString()));


    public static void register(IEventBus eventBus){
        ENTITY_TYPES.register(eventBus);
    }
}
