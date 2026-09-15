package com.aetherteam.aetherii.client.event.listeners;

import net.minecraft.client.Minecraft;

public class LevelClientListener {
    public static void onKeyPress(int key) {
        Minecraft minecraft = Minecraft.getInstance();
        if (key == 297) { //F8
            minecraft.grabPanoramixScreenshot(minecraft.gameDirectory);
        }
    }
}