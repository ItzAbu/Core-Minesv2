package org.itzabu.coreminesv2;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.itzabu.coreminesv2.GUIS.MainGui.Items.AxeSelector;
import org.itzabu.coreminesv2.GUIS.MainGui.Listeners.OnAxeClicking;
import org.itzabu.coreminesv2.GUIS.MainGui.Listeners.OnCloseInv;
import org.itzabu.coreminesv2.GUIS.MainGui.Listeners.onClickEventList;
import org.itzabu.coreminesv2.GUIS.MainGui.Task.MineResetChecker;
import org.itzabu.coreminesv2.InventoryManager.CustomInventoryManager;
import org.itzabu.coreminesv2.commands.admins.AdminGui;

public final class Core_Minesv2 extends JavaPlugin {
    private static CustomInventoryManager customInventoryManager;
    public static boolean using = false;
    public static boolean onMineCreation = false;
    @Override
    public void onEnable() {
        customInventoryManager = new CustomInventoryManager();
        AxeSelector axeSelector = new AxeSelector();

        //Task

        startRepeatingTask();


        // Plugin startup logic
        getCommand("mines").setExecutor(new AdminGui());


        //Listeners
        getServer().getPluginManager().registerEvents(new onClickEventList(), this);
        getServer().getPluginManager().registerEvents(new OnAxeClicking(), this);
        getServer().getPluginManager().registerEvents( new OnCloseInv(), this);


    }

    public void startRepeatingTask() {
        new MineResetChecker().runTaskTimer(this, 0L, 800L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public CustomInventoryManager getCustomInventoryManager() {
        return customInventoryManager;
    }
    public static void updateCustomInvMan() {
        customInventoryManager = new CustomInventoryManager();
    }

    //Instance get
    public static Core_Minesv2 getInstance() {
        return getPlugin(Core_Minesv2.class);
    }

    public static void usingchange() {
        using = !using;
    }

    public static boolean isUsing() {
        return using;
    }

    public static void onMineCreationChange() {
        onMineCreation = !onMineCreation;
    }

    public static boolean isOnMineCreation() {
        return onMineCreation;
    }
}
