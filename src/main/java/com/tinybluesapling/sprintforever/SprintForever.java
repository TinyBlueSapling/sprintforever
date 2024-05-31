package com.tinybluesapling.sprintforever;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = SprintForever.MODID, version = SprintForever.VERSION)
public class SprintForever
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(new theMagicStuff());
    }

    public class theMagicStuff {
        boolean sprinting = false;

        @SubscribeEvent
        public void onKeyPress(InputEvent.KeyInputEvent event) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
                sprinting = !sprinting;
                Minecraft.getMinecraft().thePlayer.addChatMessage(new net.minecraft.util.ChatComponentText("Sprint Toggled: " + sprinting));
            }
        }

        @SubscribeEvent
        public void onTick(TickEvent.ClientTickEvent event) {
            try {
                Minecraft.getMinecraft().thePlayer.setSprinting(sprinting);
            } catch(Exception e) {}
        }
    }
}
