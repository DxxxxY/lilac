package studio.dreamys.lilac.clickgui.component;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;
import studio.dreamys.lilac.clickgui.ClickGUI;
import studio.dreamys.lilac.clickgui.component.components.Button;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.module.Module;

import java.util.ArrayList;

public class Frame {
    private final int width;
    private final int barHeight;
    public final ArrayList<Component> components;
    public final String category;
    public int dragX;
    public int dragY;
    private boolean open;
    private int y;
    private int x;
    private boolean isDragging;

    public Frame(String cat) {
        components = new ArrayList<>();
        category = cat;
        width = 88;
        x = 5;
        y = 5;
        barHeight = 13;
        dragX = 0;
        open = false;
        isDragging = false;
        int tY = barHeight;

        for (Module mod : lilac.getInstance().getModuleManager().getModulesInCategory(category)) {
            Button modButton = new Button(mod, this, tY);
            components.add(modButton);
            tY += 12;
        }
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public void setDrag(boolean drag) {
        isDragging = drag;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void renderFrame(FontRenderer fontRenderer) {
        Gui.drawRect(x, y, x + width, y + barHeight, ClickGUI.color);
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        fontRenderer.drawStringWithShadow(category, (x + 2) * 2 + 5, (y + 2.5f) * 2 + 5, 0xFFFFFFFF);
        fontRenderer.drawStringWithShadow(open ? "-" : "+", (x + width - 10) * 2 + 5, (y + 2.5f) * 2 + 5, -1);
        GL11.glPopMatrix();
        if (open) {
            if (!components.isEmpty()) {
                for (Component component : components) {
                    component.renderComponent();
                }
            }
        }
    }

    public void refresh() {
        int off = barHeight;
        for (Component comp : components) {
            comp.setOff(off);
            off += comp.getHeight();
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int newX) {
        x = newX;
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        y = newY;
    }

    public int getWidth() {
        return width;
    }

    public void updatePosition(int mouseX, int mouseY) {
        if (isDragging) {
            setX(mouseX - dragX);
            setY(mouseY - dragY);
        }
    }

    public boolean isWithinHeader(int x, int y) {
        return x >= this.x && x <= this.x + width && y >= this.y && y <= this.y + barHeight;
    }
}
