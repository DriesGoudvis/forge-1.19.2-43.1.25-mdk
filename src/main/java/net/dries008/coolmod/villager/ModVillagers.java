package net.dries008.coolmod.villager;

import com.google.common.collect.ImmutableSet;
import net.dries008.coolmod.CoolMod;
import net.dries008.coolmod.block.ModBlocks;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.spongepowered.tools.obfuscation.ObfuscationData;

import javax.annotation.concurrent.Immutable;
import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, CoolMod.MOD_ID);

    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, CoolMod.MOD_ID);

    public static final RegistryObject<PoiType> SUPERACTIVETRASH_POI = POI_TYPES.register("superactivetrash_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SUPERACTIVETRASH.get().getStateDefinition().getPossibleStates()),
                    1,1));


    public static final RegistryObject<VillagerProfession> TRASHMASTER = VILLAGER_PROFESSIONS.register("trashmaster",
            () -> new VillagerProfession("trashmaster", x -> x.get() == SUPERACTIVETRASH_POI.get(),
                    x -> x.get() == SUPERACTIVETRASH_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIs(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, SUPERACTIVETRASH_POI.get());
        }catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
