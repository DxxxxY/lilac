package studio.dreamys.lilac.module;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.Objects;

@Getter
public class ModuleManager {
    private ArrayList<Module> modules;

    @SneakyThrows
    public ModuleManager(ArrayList<Class<? extends Module>> modules) {
        long l = System.currentTimeMillis();
        this.modules = new ArrayList<>();
        for (Class<? extends Module> module : modules) {
            this.modules.add(module.newInstance());
        }
        System.out.println("Initialized Module Manager. (" + (System.currentTimeMillis() - l) + "ms)");
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
