package me.itsmyunderscore.utils;

import org.bukkit.DyeColor;

import java.util.*;

public class ColorUtils {
    private static final Random random = new Random();
    public static DyeColor getRandomColor() {
        DyeColor[] colors = DyeColor.values();

        return colors[random.nextInt(colors.length)];
    }
}
