package org.itzabu.coreminesv2.CustomConfigs;

import jdk.internal.net.http.common.Pair;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class customConfig {

    File file;
    File Mines;
    FileConfiguration config;
    FileConfiguration minesConfig;


    public void setup() throws IOException {
        file = new File("plugins/CoreMinesV2/Invs/config.yml");
        config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
        config.set("Inventory Save", true);

        Mines = new File("plugins/CoreMinesV2/Mines/config.yml");
        minesConfig = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(Mines);
        //Commenti List
        List<String> comments = new ArrayList<>();
        comments.add("This is the config file for CoreMinesV2 ");
        comments.add("Do not edit unless you know what you are doing");
        minesConfig.setComments("", comments);


        try {
            config.save(file);
            minesConfig.save(Mines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Inventory Manager

    public static void newInv(Inventory inventory,ItemStack[] Armor, ItemStack offHand, UUID uuid) {
        File file_inv = new File("plugins/CoreMinesV2/Invs/" + uuid + ".yml");
        FileConfiguration config_inv = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file_inv);

        for (int i = 0; i < 36; i++) {
            config_inv.set("inventory.slot." + i, inventory.getItem(i));
        }

        // Salva l'armatura (4 slot)
        for (int i = 0; i < 4; i++) {
            config_inv.set("armor.slot." + i, Armor[i]);
        }

        // Salva la mano secondaria (1 slot)
        config_inv.set("offhand.slot", offHand);

        try {
            config_inv.save(file_inv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Inventory getInv(UUID uuid) {
        File file_inv = new File("plugins/CoreMinesV2/Invs/" + uuid + ".yml");
        FileConfiguration config_inv = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file_inv);
        Inventory inventory = org.bukkit.Bukkit.createInventory(null, 45);
        for (int i = 0; i < 36; i++) {
            ItemStack item = config_inv.getItemStack("inventory.slot." + i);
            inventory.setItem(i, item);
        }

        // Carica l'armatura (4 slot)
        for (int i = 0; i < 4; i++) {
            ItemStack item = config_inv.getItemStack("armor.slot." + i);
            inventory.setItem(36 + i, item);
        }

        // Carica la mano secondaria (1 slot)
        ItemStack offHandItem = config_inv.getItemStack("offhand.slot");
        inventory.setItem(40, offHandItem);
        //File deletion
        try {
            file_inv.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inventory;

    }


    public static void newMine(int x1, int y1, int z1, int x2, int y2, int z2, String name, World world) {
        File file = new File("plugins/CoreMinesV2/Mines/" + name + ".yml");
        FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
        config.set("Name", name);
        config.set("World", world.getName());
        config.set("x1", x1);
        config.set("y1", y1);
        config.set("z1", z1);
        config.set("x2", x2);
        config.set("y2", y2);
        config.set("z2", z2);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Location> getLocations(String name) {
        File file = new File("plugins/CoreMinesV2/Mines/" + name + ".yml");
        FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
        List<Location> Locations = new ArrayList<>();
        Locations.add(new Location(Objects.requireNonNull(Bukkit.getServer().getWorld(config.getString("World"))),
                config.getInt("x1"), config.getInt("y1"), config.getInt("z1")));
        Locations.add(new Location(Objects.requireNonNull(Bukkit.getServer().getWorld(config.getString("World"))),
                config.getInt("x2"), config.getInt("y2"), config.getInt("z2")));


        return Locations;
    }

    public static List<Location> getLocationsnoyml(String name) {
        File file = new File("plugins/CoreMinesV2/Mines/" + name);
        FileConfiguration config = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(file);
        List<Location> Locations = new ArrayList<>();
        Locations.add(new Location(Objects.requireNonNull(Bukkit.getServer().getWorld(config.getString("World"))),
                config.getInt("x1"), config.getInt("y1"), config.getInt("z1")));
        Locations.add(new Location(Objects.requireNonNull(Bukkit.getServer().getWorld(config.getString("World"))),
                config.getInt("x2"), config.getInt("y2"), config.getInt("z2")));


        return Locations;
    }

    public static void delMine(String name) {
        File file = new File("plugins/CoreMinesV2/Mines/" + name + ".yml");
        try {
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getAllMine() {
        return new File("plugins/CoreMinesV2/Mines/");
    }

    public static int minesCount() {
        File file = new File("plugins/CoreMinesV2/Mines/");
        if(file.listFiles() == null) {
            return 0;
        }
        return file.listFiles().length;
    }

}
