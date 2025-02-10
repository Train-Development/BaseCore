package com.blonicx.basecore.mixin;

import com.blonicx.basecore.api.minecraft.client.events.blocks.SignChangeEvent;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SignBlockEntity.class)
public class SignChangeMixin {
    @Inject(method = "setText", at = @At("HEAD"))
    private void onSignTextSet(SignText text, boolean front, CallbackInfoReturnable<Boolean> cir) {
        StringBuilder signText = new StringBuilder();

        for (int i = 0; i < 4; i++) { // 4 lines of text
            signText.append(text.getMessage(i, false).getString());
            if (i < 3) signText.append("\n");
        }

        // Call the event
        SignChangeEvent.SIGN_CHANGE.invoker().onSignChange(signText.toString());
    }
}