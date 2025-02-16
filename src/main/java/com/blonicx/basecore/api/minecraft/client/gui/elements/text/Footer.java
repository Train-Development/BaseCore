package com.blonicx.basecore.api.minecraft.client.gui.elements.text;

import net.minecraft.text.Text;
import java.util.function.Supplier;

public class Footer {
    private static Supplier<Text> footerSupplier = () -> Text.empty();

    public static void setFooter(Supplier<Text> textSupplier) {
        footerSupplier = textSupplier;
    }

    public static Text getFooter() {
        return footerSupplier.get();
    }
}