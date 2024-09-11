package org.itzabu.coreminesv2.GUIS.MainGui.Task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.CustomConfigs.customConfig;
import org.itzabu.coreminesv2.Utils.ResetFunction;

import java.io.File;
import java.util.List;

public class MineResetChecker extends BukkitRunnable{


    @Override
    public void run() {
        File mines = customConfig.getAllMine();
        if(mines.list() == null) {
            System.out.println("No mines found");
            return;
        }

        for(String mine : mines.list()) {
            File mineFile = new File("plugins/CoreMinesV2/Mines/" + mine);
            FileConfiguration mineConfig = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(mineFile);
            List<Location> locs = customConfig.getLocationsnoyml(mine);
            ResetFunction.checkMine(locs.get(0).getWorld(), locs.get(0), locs.get(1));
        }
    }
}
