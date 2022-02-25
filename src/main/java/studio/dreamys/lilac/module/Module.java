package studio.dreamys.lilac.module;

import lombok.Getter;
import net.minecraftforge.common.MinecraftForge;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.setting.Setting;

@Getter
public class Module {
    private String name;
    private String category;
    private int key;
    private boolean toggled;

    public Module(String name, String category) {
        this.name = name;
        key = 0;
        this.category = category;
        toggled = false;
    }

    public void key(int key) {
        this.key = key;
        if (lilac.getInstance().getSaveLoad() != null) {
            lilac.getInstance().getSaveLoad().save();
        }
    }
    
    public void set(Setting set) {
        lilac.getInstance().getSettingsManager().rSetting(set);
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;

        if (this.toggled) onEnable();
        else onDisable();

        if (lilac.getInstance().getSaveLoad() != null) lilac.getInstance().getSaveLoad().save();
    }

    public void toggle() {
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

