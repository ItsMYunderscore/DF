package me.itsmyunderscore.managers;

import me.itsmyunderscore.SpigotPlugin;

public abstract class SpigotManager {
    protected SpigotPlugin main;

    public SpigotManager(SpigotPlugin main) {
        this.main = main;
    }
}
