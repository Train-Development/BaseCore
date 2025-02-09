package com.blonicx.basecore.api.minecraft.client.gui.buttons;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

public class RoundButtonWidget extends ButtonWidget {
    private final Text buttonText;
    private final int normalColor;
    private final int hoverColor;
    private final int textColor;

    public RoundButtonWidget(int x, int y, int diameter, Text text, PressAction onPress, int normalColor, int hoverColor, int textColor) {
        super(x, y, diameter, diameter, text, onPress, DEFAULT_NARRATION_SUPPLIER);
        this.buttonText = text;
        this.normalColor = normalColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        int centerX = getX() + getWidth() / 2;
        int centerY = getY() + getHeight() / 2;
        int radius = getWidth() / 2;

        boolean hovered = isMouseOver(mouseX, mouseY);
        int color = hovered ? hoverColor : normalColor;

        drawCircle(context, centerX, centerY, radius, color);

        // Get the TextRenderer from MinecraftClient
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int textWidth = textRenderer.getWidth(buttonText);

        // Draw text at the center of the button
        context.drawTextWithShadow(textRenderer, buttonText, centerX - textWidth / 2, centerY - 4, textColor);
    }

    private void drawCircle(DrawContext context, int x, int y, int radius, int color) {
        for (int dx = -radius; dx <= radius; dx++) {
            for (int dy = -radius; dy <= radius; dy++) {
                if (dx * dx + dy * dy <= radius * radius) {
                    context.fill(x + dx, y + dy, x + dx + 1, y + dy + 1, color);
                }
            }
        }
    }
}