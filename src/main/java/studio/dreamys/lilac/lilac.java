package studio.dreamys.lilac;

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

import java.util.ArrayList;

public class lilac {
    private final ModuleManager moduleManager;
    private final SettingsManager settingsManager;
    private final ClickGUI clickGUI;
    private final SaveLoad saveLoad;
    private final ArrayList<String> categories;

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

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public ClickGUI getClickGUI() {
        return clickGUI;
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public static lilac getInstance() {
        return instance;
    }
}