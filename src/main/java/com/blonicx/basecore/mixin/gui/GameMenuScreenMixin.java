package com.blonicx.basecore.mixin.gui;

import com.blonicx.basecore.api.minecraft.gui.elements.text.Footer;
import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public abstract class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(title);
    }

    @Inject(method = "initWidgets", at = @At("TAIL"))
    private void addFooter(CallbackInfo ci) {
        // Create and add the Footer to the Screen //
        TextWidget footer = new TextWidget(this.width, this.height + 200, Footer.getFooter(), textRenderer);
        this.addDrawableChild(footer);
    }
}