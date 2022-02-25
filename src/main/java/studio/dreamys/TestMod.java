package studio.dreamys;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import studio.dreamys.lilac.lilac;

import java.util.ArrayList;
import java.util.Arrays;

@Mod(modid = TestMod.MODID, name = TestMod.NAME, version = TestMod.VERSION)
public class TestMod {
    public static final String MODID = "test";
    public static final String NAME = "test";
    public static final String VERSION = "1.0";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        new lilac(MODID, new ArrayList<>(Arrays.asList(ClickGUI.class)), new ArrayList<>(Arrays.asList("HUD")));
    }
}