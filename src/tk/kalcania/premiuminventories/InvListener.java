package tk.kalcania.premiuminventories;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        MainInv i = new MainInv();
        if(e.getInventory().getName().equals("inv")){
            String name = e.getCursor().getItemMeta().getDisplayName();
            if(name.equals("§4Tools")){
                Player p = (Player) e.getWhoClicked();
                i.openTools(p);
            }
        }
    }
}
