package studio.dreamys.lilac;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;
import studio.dreamys.lilac.clickgui.ClickGUI;
import studio.dreamys.lilac.module.Module;
import studio.dreamys.lilac.module.ModuleManager;
import studio.dreamys.lilac.setting.SettingsManager;
import studio.dreamys.lilac.util.SaveLoad;

import java.io.IOException;
import java.util.ArrayList;

@Getter
public class lilac {
    private ModuleManager moduleManager;
    private SettingsManager settingsManager;
    private ClickGUI clickGUI;
    private SaveLoad saveLoad;
    private ArrayList<String> categories;

    @Getter
    private static lilac instance;

    public lilac(String modid, ArrayList<Class<? extends Module>> modules, ArrayList<String> categories) {
        instance = this;
        this.categories = categories;

        MinecraftForge.EVENT_BUS.register(this);

        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager(modules);
        clickGUI = new ClickGUI();
        saveLoad = new SaveLoad(modid);
    }

    @SubscribeEvent
    public void key(InputEvent.KeyInputEvent e) {
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        if (Keyboard.getEventKeyState()) {
            int keyCode = Keyboard.getEventKey();
            if (keyCode <= 0) return;
            for (Module m : moduleManager.getModules()) {
                if (m.getKey() == keyCode) m.toggle();
            }
        }
    }
}