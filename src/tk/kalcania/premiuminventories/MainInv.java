package tk.kalcania.premiuminventories;

// import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
/* import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory; */
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class MainInv extends JavaPlugin implements Listener {
    private static Plugin instance;

    public void onEnable() {
        List<Inventory> invs = new ArrayList<Inventory>();
        instance = this;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
         /*if(sender.hasPermission("pi.player.creative")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                Inventory inv = Bukkit.createInventory(p, InventoryType.CREATIVE);
                p.openInventory(inv);
                return true;
            } else { instance.getLogger().info("InGame only!"); }
        }*/
        Player p = (Player) sender;
        if(cmd.getName() == "invsize") {
            if(p.hasPermission("pi.player.invsize")){
                //WIP

            }
        }
        return true;
    }
}
