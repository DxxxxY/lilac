package studio.dreamys.lilac.util;

import net.minecraft.client.Minecraft;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.module.Module;
import studio.dreamys.lilac.setting.Setting;

import java.io.*;
import java.util.ArrayList;

public class SaveLoad {
    private File file;

    public SaveLoad(String modid) {
        File dir = new File(Minecraft.getMinecraft().mcDataDir, modid);
        file = new File(dir, "config.txt");
        try {
            if (!dir.exists()) dir.mkdir();
            if (!file.exists()) file.createNewFile();
            load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            ArrayList<String> toSave = new ArrayList<>();
            for (Module mod : lilac.getInstance().getModuleManager().getModules()) {
                toSave.add("MOD:" + mod.getName() + ":" + mod.isToggled() + ":" + mod.getKey());
            }
            for (Setting set : lilac.getInstance().getSettingsManager().getSettings()) {
                if (set.isSlider()) {
                    toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValDouble());
                }
                if (set.isCheck()) {
                    toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValBoolean());
                }
                if (set.isCombo()) {
                    toSave.add("SET:" + set.getName() + ":" + set.getParentMod().getName() + ":" + set.getValString());
                }
            }
            PrintWriter pw = new PrintWriter(file);
            for (String str : toSave) {
                pw.println(str);
            }
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            ArrayList<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            for (String s : lines) {
                String[] args = s.split(":");
                if (s.toLowerCase().startsWith("mod:")) {
                    Module m = lilac.getInstance().getModuleManager().getModule(args[1]);
                    if (m != null) {
                        m.setToggled(Boolean.parseBoolean(args[2]));
                        m.key(Integer.parseInt(args[3]));
                    }
                }
                if (s.toLowerCase().startsWith("set:")) {
                    Module m = lilac.getInstance().getModuleManager().getModule(args[2]);
                    if (m != null) {
                        Setting set = lilac.getInstance().getSettingsManager().getSettingByName(m, args[1]);
                        if (set != null) {
                            if (set.isSlider()) {
                                set.setValDouble(Double.parseDouble(args[3]));
                            }
                            if (set.isCheck()) {
                                set.setValBoolean(Boolean.parseBoolean(args[3]));
                            }
                            if (set.isCombo()) {
                                set.setValString(args[3]);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
