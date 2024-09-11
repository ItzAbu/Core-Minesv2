package org.itzabu.coreminesv2.commands.admins;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.itzabu.coreminesv2.Core_Minesv2;
import org.itzabu.coreminesv2.InventoryManager.CustomInventoryManager;
import org.jetbrains.annotations.NotNull;

public class AdminGui implements CommandExecutor {


    //Comando per aprire il menu
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String arg, @NotNull String[] args) {

        //Controllo se il sender è un player
        if(!(sender instanceof Player)) {
            sender.sendMessage("§cDevi essere un player per eseguire questo comando");
            return true;
        }

        //Controllo se il player ha i permessi
        if(!sender.hasPermission("coremines.admin")) {
            sender.sendMessage("§cNon hai i permessi per eseguire questo comando");
            return true;
        }

        //Apro il menu
        Player player = (Player) sender;
        //Implementare inv

        player.openInventory(CustomInventoryManager.getGUI1());
        Core_Minesv2.usingchange();
        return false;
    }
}
