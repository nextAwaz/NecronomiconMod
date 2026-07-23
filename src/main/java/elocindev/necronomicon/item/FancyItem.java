package elocindev.necronomicon.item;

import elocindev.necronomicon.api.text.AnimatedText;
import elocindev.necronomicon.api.text.IAnimatedText;

//#if FABRIC==1
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//#else
//$$ import net.minecraft.world.item.Item;
//$$ import net.minecraft.world.item.ItemStack;
//#endif

public class FancyItem extends Item implements IAnimatedText {

    public AnimatedText nameAnimation;

    public FancyItem(
        //#if FABRIC==1
        Settings settings,
        //#else
        //$$ Properties properties,
        //#endif
        AnimatedText animatedTextType
    ) {
        //#if FABRIC==1
        super(settings);
        //#else
        //$$ super(properties);
        //#endif
        this.nameAnimation = animatedTextType;
    }

    @Override
    public AnimatedText getAnimatedName(ItemStack stack) {
       return this.nameAnimation;
    }
}
