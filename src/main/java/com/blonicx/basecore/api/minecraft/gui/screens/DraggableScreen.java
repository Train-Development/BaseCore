package com.blonicx.basecore.api.minecraft.gui.screens;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class DraggableScreen extends Screen {
    protected int x, y; // Window position
    protected int backgroundColor; // Background color
    protected int width, height; // Window size
    private boolean dragging = false;
    private int dragX, dragY;


    protected DraggableScreen(Text title, int backgroundColor, int width, int height) {
        super(title);
        this.width = width;
        this.height = height;
        this.backgroundColor = backgroundColor;
        this.x = 0; // Default X position
        this.y = 0; // Default Y position
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Draw draggable background
        context.fill(x, y, x + width, y + height, backgroundColor);

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0 && isMouseOver(mouseX, mouseY)) {
            dragging = true;
            dragX = (int) (mouseX - x);
            dragY = (int) (mouseY - y);
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        if (button == 0) {
            dragging = false;
        }
        return super.mouseReleased(mouseX, mouseY, button);
    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (dragging) {
            x = (int) mouseX - dragX;
            y = (int) mouseY - dragY;
            this.clearChildren(); // Clear previous buttons before re-adding
            init(); // Reinitialize UI components to update positions
            return true;
        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
