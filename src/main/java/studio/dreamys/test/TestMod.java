package studio.dreamys.test;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import studio.dreamys.lilac.lilac;
import studio.dreamys.lilac.module.Module;

import java.util.ArrayList;
import java.util.Arrays;

@Mod(modid = TestMod.MODID, name = TestMod.NAME, version = TestMod.VERSION)
public class TestMod {
    public static final String MODID = "test";
    public static final String NAME = "test";
    public static final String VERSION = "1.0";

    public static final ArrayList<Class<? extends Module>> modules =
            new ArrayList<>(Arrays.asList(
                    ClickGUI.class,
                    ExampleModule.class
            ));

    public static final ArrayList<String> categories =
            new ArrayList<>(Arrays.asList(
                    "HUD",
                    "EXAMPLE"
            ));

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        new lilac(MODID, modules, categories);
    }
}