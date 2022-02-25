package studio.dreamys.test;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import studio.dreamys.lilac.module.Module;
import studio.dreamys.lilac.setting.Setting;

import java.util.ArrayList;
import java.util.Arrays;

public class ExampleModule extends Module {
    public ExampleModule() {
        super("Example", "EXAMPLE");
        set(new Setting("Checkbox", this, false));
        set(new Setting("Slider", this, 1.0, 0.1, 3.0, false));
        set(new Setting("ModeButton", this, "Mode1", new ArrayList<>(Arrays.asList("Mode1", "Mode2", "Mode3"))));
    }

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        addMessage("Module toggled: " + isToggled());
        addMessage("Checkbox toggled: " + getSetting("Checkbox").getValBoolean());
        addMessage("Slider value: " + getSetting("Slider").getValDouble());
        addMessage("ModeButton Mode: " + getSetting("ModeButton").getValString());
    }

    @Override
    public void onEnable() {
        super.onEnable();
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        addMessage("enabled!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        addMessage("disabled!");
    }

    @Override
    public void onToggled() {
        super.onToggled();
        if (Minecraft.getMinecraft().theWorld == null || Minecraft.getMinecraft().thePlayer == null) return;
        addMessage("toggled!");
    }

    public void addMessage(String msg) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(msg));
    }
}
