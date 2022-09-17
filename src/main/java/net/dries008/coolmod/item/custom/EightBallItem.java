package net.dries008.coolmod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EightBallItem extends Item {
    public EightBallItem(Properties properties) {
        super(properties);
    }
    String[] a = {"You are trash!", "Did you even try to be good or were you born like this?","If there was a competition for biggest losers, you would be second.","If I had to jump from your ego to your skills I would die from starvation before arriving."};
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            outputRandomNumber(player);
            player.getCooldowns().addCooldown(this, 40);
        }


        return super.use(level, player, hand);
    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal(getRandomString()));
    }

    private String getRandomString(){

        int b = RandomSource.createNewThreadLocalInstance().nextInt(a.length);
        return a[b];
    }
}
