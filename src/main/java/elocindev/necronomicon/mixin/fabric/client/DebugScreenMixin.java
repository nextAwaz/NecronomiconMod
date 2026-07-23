package elocindev.necronomicon.mixin.fabric.client;

//#if FABRIC==1

import java.util.List;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import elocindev.necronomicon.CommonInitializer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.gui.hud.DebugHud;

@Mixin(DebugHud.class)
public class DebugScreenMixin {
    @Inject(method = "getLeftText", at = @At("RETURN"))
	protected void getLeftText(CallbackInfoReturnable<List<String>> info) {
        info.getReturnValue().add("Necronomicon API v"+CommonInitializer.VERSION);
	}
}

//#endif
