package com.blonicx.basecore.api.utils.text;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;

import java.awt.*;

public class TextAPI {
     // Creates colored text with optional styles. //
    public static Text coloredText(String message, int color, boolean bold, boolean italic) {
        Style style = Style.EMPTY
                .withColor(TextColor.fromRgb(color))
                .withBold(bold)
                .withItalic(italic);
        return Text.literal(message).setStyle(style);
    }

     // Applies a gradient effect to a string. //
    public static Text gradientText(String text, int startColor, int endColor) {
        MutableText finalText = Text.literal("");
        int length = text.length();

        for (int i = 0; i < length; i++) {
            float ratio = (float) i / (length - 1);
            int blendedColor = blendColors(startColor, endColor, ratio);
            finalText.append(coloredText(String.valueOf(text.charAt(i)), blendedColor, false, false));
        }

        return finalText;
    }

     // Blends two colors based on a ratio. //
    private static int blendColors(int startColor, int endColor, float ratio) {
        Color color1 = new Color(startColor);
        Color color2 = new Color(endColor);

        int r = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
        int g = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
        int b = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);

        return new Color(r, g, b).getRGB();
    }

    // Creates a rainbow text effect. //
    public static Text rainbowText(String text) {
        MutableText finalText = Text.literal("");
        int[] rainbowColors = {0xFF0000, 0xFF7F00, 0xFFFF00, 0x00FF00, 0x0000FF, 0x4B0082, 0x8B00FF};

        for (int i = 0; i < text.length(); i++) {
            int color = rainbowColors[i % rainbowColors.length];
            finalText.append(coloredText(String.valueOf(text.charAt(i)), color, false, false));
        }

        return finalText;
    }

     // Creates a formatted text object. //
    public static Text formattedText(String message, Formatting format) {
        return Text.literal(message).styled(style -> style.withFormatting(format));
    }
}
