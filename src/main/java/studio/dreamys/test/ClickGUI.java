package studio.dreamys.test;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.module.Module;

public class ClickGUI extends Module {
    public ClickGUI() {
        super("ClickGUI", "HUD");

        key(Keyboard.KEY_RSHIFT);
    }

    @Override
    public void onEnable() {
        Minecraft.getMinecraft().displayGuiScreen(lilac.getInstance().getClickGUI());
        setToggled(false); //unclickable effect
    }
}
