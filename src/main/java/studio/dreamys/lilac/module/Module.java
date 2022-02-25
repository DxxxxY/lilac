package studio.dreamys.lilac.module;

import net.minecraftforge.common.MinecraftForge;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.setting.Setting;

public class Module {
    private final String name;
    private final String category;
    private int key;
    private boolean toggled;

    public Module(String name, String category) {
        this.name = name;
        key = 0;
        this.category = category;
        toggled = false;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getKey() {
        return key;
    }

    public void key(int key) {
        this.key = key;
        if (lilac.getInstance().getSaveLoad() != null) {
            lilac.getInstance().getSaveLoad().save();
        }
    }

    public Setting getSetting(String name) {
        return lilac.getInstance().getSettingsManager().getSettingByName(this, name);
    }

    public void set(Setting set) {
        lilac.getInstance().getSettingsManager().rSetting(set);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (this.toggled) onEnable();
        else onDisable();

        onToggled();

        if (lilac.getInstance().getSaveLoad() != null) lilac.getInstance().getSaveLoad().save();
    }

    public boolean isToggled() {
        return toggled;
    }

    public void toggle()  {
        toggled = !toggled;

        if (toggled) onEnable();
        else onDisable();

        onToggled();

        if (lilac.getInstance().getSaveLoad() != null) lilac.getInstance().getSaveLoad().save();
    }

    public void onToggled() {

    }

    public void onEnable() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}

