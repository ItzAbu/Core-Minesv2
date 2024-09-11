package org.itzabu.coreminesv2.Utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class ResetFunction {


    public static void resetMine(World world, Location corner1, Location corner2, Material material) {
        int x1 = corner1.getBlockX();
        int y1 = corner1.getBlockY();
        int z1 = corner1.getBlockZ();
        int x2 = corner2.getBlockX();
        int y2 = corner2.getBlockY();
        int z2 = corner2.getBlockZ();
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    world.getBlockAt(x, y, z).setType(material);
                }
            }
        }
    }

    public static void checkMine(World world, Location corner1, Location corner2) {
        int x1 = corner1.getBlockX();
        int y1 = corner1.getBlockY();
        int z1 = corner1.getBlockZ();
        int x2 = corner2.getBlockX();
        int y2 = corner2.getBlockY();
        int z2 = corner2.getBlockZ();
        int count = 0;
        for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                for (int z = Math.min(z1, z2); z <= Math.max(z1, z2); z++) {
                    if(world.getBlockAt(x, y, z).getType() == Material.STONE) {
                        count++;
                    }
                }
            }
        }
        int size = (Math.max(x1, x2) - Math.min(x1, x2)) * (Math.max(y1, y2) - Math.min(y1, y2)) * (Math.max(z1, z2) - Math.min(z1, z2));
        if(count < (size)/2) {
            ResetFunction.resetMine(world, corner1, corner2, Material.STONE);
        }
    }
}
