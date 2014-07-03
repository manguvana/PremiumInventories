package tk.kalcania.premiuminventories;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class InvListener implements Listener {
    private Plugin instance = MainInv.instance;
    private Player p;
    private String name;
    private ItemStack is;

    public InvListener() {
        instance.getServer().getPluginManager().registerEvents(this, new MainInv());
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        p = (Player) e.getWhoClicked();
        if (e.getInventory().getName().equals(MainInv.inv.getName())) {

            new BukkitRunnable() {
                @Override
                public void run() {
                    if (MainInv.inv.) {
                        MainInv.openTools(p);
                    }
                }
            }.runTaskLater(new MainInv(), 1);

        }
    }

}
