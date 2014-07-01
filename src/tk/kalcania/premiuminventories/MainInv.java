package tk.kalcania.premiuminventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
/* import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory; */
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class MainInv extends JavaPlugin implements Listener {
    private static Plugin instance;

    public void onEnable() {
        List<ItemStack> invsize = new ArrayList<ItemStack>();
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
        if(cmd.getName() == "ench") {
            if (p instanceof Player) {
                if (p.hasPermission("pi.player.ench")) {
                    ItemStack is = p.getItemInHand();
                    Inventory inv = Bukkit.createInventory(null, 9, "Enchanting");
                    createDisplay(Material.DIAMOND_SWORD, inv, 1, "Combat", "Sword enchantments");
                    createDisplay(Material.DIAMOND_PICKAXE, inv, 9, "Tools", "Tool enchantments");
                    Inventory tools = Bukkit.createInventory(null, 9, "Tools");
                    createDisplay(Material.ENCHANTED_BOOK, tools, 1, "Efficiency I", "Cost" + cost);

                }
            } else { getLogger().info("InGame only! ):"); }
        }
        return true;
    }
    public static void createDisplay(Material material, Inventory inv, int Slot, String name, String lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(lore);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        inv.setItem(Slot, item);

    }
}
