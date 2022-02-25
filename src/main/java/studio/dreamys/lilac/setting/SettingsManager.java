package studio.dreamys.lilac.setting;

import studio.dreamys.lilac.module.Module;

import java.util.ArrayList;

public class SettingsManager {
    private final ArrayList<Setting> settings;

    public SettingsManager() {
        long l = System.currentTimeMillis();
        settings = new ArrayList<>();
        System.out.println("Initialized Settings Manager. (" + (System.currentTimeMillis() - l) + "ms)");
    }

    public void rSetting(Setting in) {
        settings.add(in);
    }

    public ArrayList<Setting> getSettings() {
        return settings;
    }

    public ArrayList<Setting> getSettingsByMod(Module mod) {
        ArrayList<Setting> out = new ArrayList<>();
        for (Setting s : getSettings()) {
            if (s.getParentMod().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }

    public Setting getSettingByName(Module mod, String name) {
        for (Setting set : getSettings()) {
            if (set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod) {
                return set;
            }
        }
        System.err.println("[lilac] Error Setting NOT found: '" + name + "'!");
        return null;
    }

}