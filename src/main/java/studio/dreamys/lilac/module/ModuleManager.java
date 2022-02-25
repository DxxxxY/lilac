package studio.dreamys.lilac.module;

import java.util.ArrayList;
import java.util.Objects;

public class ModuleManager {
    private final ArrayList<Module> modules;

    public ModuleManager(ArrayList<Class<? extends Module>> modules) {
        long l = System.currentTimeMillis();
        this.modules = new ArrayList<>();
        try {
            for (Class<? extends Module> module : modules) {
                this.modules.add(module.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("Initialized Module Manager. (" + (System.currentTimeMillis() - l) + "ms)");
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public Module getModule(String name) {
        for (Module m : modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }

    public ArrayList<Module> getModulesInCategory(String c) {
        ArrayList<Module> mods = new ArrayList<>();
        for (Module m : modules) {
            if (Objects.equals(m.getCategory(), c)) {
                mods.add(m);
            }
        }
        return mods;
    }
}
