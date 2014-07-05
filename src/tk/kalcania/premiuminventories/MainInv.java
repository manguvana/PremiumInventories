package tk.kalcania.premiuminventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/* import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory; */
//import org.bukkit.plugin.Plugin;
//import org.bukkit.enchantments.Enchantment;

public class MainInv extends JavaPlugin implements Listener {
    private Player p;
    private ItemStack is;

    public static Inventory tools1 = Bukkit.createInventory(null, 45, "Tools");
    public static Inventory inv = Bukkit.createInventory(null, 9, "Enchanting");

    private static Integer eff1Cost;
    private static Integer eff2Cost;
    private static Integer eff3Cost;
    private static Integer eff4Cost;
    private static Integer eff5Cost;
    private static Integer effXCost;

    static {
        createDisplay(Material.DIAMOND_SWORD, inv, 0, "§4Combat", "§2Sword enchantments");
        createDisplay(Material.DIAMOND_PICKAXE, inv, 8, "§4Tools", "§2Tool enchantments");
        createDisplay(Material.DIAMOND_HELMET, inv, 4, "§4Armor", "§2Armour enchantments");
        //seperator
        /*
        createDisplay(Material.ENCHANTED_BOOK, tools1, 0, "§4Efficiency I", "§2Cost §4$" + eff1Cost);
        createDisplay(Material.ENCHANTED_BOOK, tools1, 9, "§4Efficiency II", "§2Cost §4$" + eff2Cost);
        createDisplay(Material.ENCHANTED_BOOK, tools1, 18, "§4Efficiency III", "§2Cost §4$" + eff3Cost);
        createDisplay(Material.ENCHANTED_BOOK, tools1, 27, "§4Efficiency IV", "§2Cost §4$" + eff4Cost);
        createDisplay(Material.ENCHANTED_BOOK, tools1, 36, "§4Efficiency V", "§2Cost §4$" + eff5Cost); */
    }

    static Plugin plugin;

    List<Material> swords = new ArrayList<Material>();
    List<Material> tools = new ArrayList<Material>();
    List<Material> stuff = new ArrayList<Material>();
    List<Material> armor = new ArrayList<Material>();
    List<Material> others = new ArrayList<Material>();

    public static void openTools(Player p) {
        MainInv i = new MainInv();
        p.openInventory(i.tools1);
    }

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);

        tools.add(Material.DIAMOND_AXE);
        armor.add(Material.DIAMOND_BOOTS);
        armor.add(Material.DIAMOND_CHESTPLATE);
        armor.add(Material.DIAMOND_HELMET);
        armor.add(Material.DIAMOND_LEGGINGS);
        tools.add(Material.DIAMOND_PICKAXE);
        tools.add(Material.DIAMOND_SPADE);
        swords.add(Material.DIAMOND_SWORD);
        stuff.add(Material.DIAMOND_HOE);

        tools.add(Material.IRON_AXE);
        armor.add(Material.IRON_BOOTS);
        armor.add(Material.IRON_CHESTPLATE);
        tools.add(Material.IRON_PICKAXE);
        armor.add(Material.IRON_HELMET);
        armor.add(Material.IRON_LEGGINGS);
        tools.add(Material.IRON_SPADE);
        swords.add(Material.IRON_SWORD);
        stuff.add(Material.IRON_HOE);

        tools.add(Material.STONE_AXE);
        tools.add(Material.STONE_PICKAXE);
        tools.add(Material.STONE_SPADE);
        swords.add(Material.STONE_SWORD);
        stuff.add(Material.STONE_HOE);

        tools.add(Material.WOOD_AXE);
        tools.add(Material.WOOD_PICKAXE);
        tools.add(Material.WOOD_SPADE);
        swords.add(Material.WOOD_SWORD);
        stuff.add(Material.WOOD_HOE);

        stuff.add(Material.SHEARS);
        others.add(Material.BOW);
        stuff.add(Material.FLINT_AND_STEEL);
        stuff.add(Material.CARROT_STICK);
        others.add(Material.FISHING_ROD);

        saveDefaultConfig();
        eff1Cost = getConfig().getInt("ench.eff1");
        eff2Cost = getConfig().getInt("ench.eff2");
        eff3Cost = getConfig().getInt("ench.eff3");
        eff4Cost = getConfig().getInt("ench.eff4");
        eff5Cost = getConfig().getInt("ench.eff5");
        effXCost = getConfig().getInt("ench.effX");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
         /*if(sender.hasPermission("pi.player.creative")){
            if (sender instanceof Player) {
                Player p = (Player) sender;
                Inventory inv = Bukkit.createInventory(p, InventoryType.CREATIVE);
                p.openInventory(inv);
                return true;
            } else { instance.getLogger().info("InGame only!"); }
        }*/

        if (cmd.getName().equals("ench")) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (p.hasPermission("pi.player.ench")) {
                    ItemStack is = p.getItemInHand();
                    if (swords.contains(is.getType()) || armor.contains(is.getType()) || tools.contains(is.getType()) || stuff.contains(is.getType()) || others.contains(is.getType())) {


                        p.openInventory(inv);
                        createToolsMenu(tools1);

                    } else {
                        p.sendMessage("§4That item is unenchantable!");
                    }
                } else {
                    p.sendMessage("§2You dont have permission to use this.");
                }
            } else {
                getLogger().info("InGame only! ):");
            }
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
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        is = e.getCurrentItem();
        p = (Player) e.getWhoClicked();

        if (e.getInventory().getName().equals(MainInv.inv.getName())) {
            if (is.getItemMeta().getDisplayName() == "§4Tools") {
                p.closeInventory();
                p.openInventory(tools1);
            } else if (is.getItemMeta().getDisplayName() == "§4Armor"){
                p.sendMessage("Still workin :P");
                e.setCancelled(true);
                p.closeInventory();
            } else if(is.getItemMeta().getDisplayName() == "§4Combat") {
                p.sendMessage("Still workin :P");
                e.setCancelled(true);
                p.closeInventory();
            } else {
                e.setCancelled(true);
            }
        } else if(e.getInventory().getName().equals(tools1.getName())){
            if(is.getItemMeta().getDisplayName().equals("§4Efficiency V")){
                p.sendMessage("Dis iis Eff V");
                e.setCancelled(true);
            } else {
                e.setCancelled(true);
            }
        }
    }

    private void createToolsMenu(Inventory i){
        createDisplay(Material.ENCHANTED_BOOK, i, 0, "§4Efficiency I", "§2Cost §4$" + eff1Cost);
        createDisplay(Material.ENCHANTED_BOOK, i, 9, "§4Efficiency II", "§2Cost §4$" + eff2Cost);
        createDisplay(Material.ENCHANTED_BOOK, i, 18, "§4Efficiency III", "§2Cost §4$" + eff3Cost);
        createDisplay(Material.ENCHANTED_BOOK, i, 27, "§4Efficiency IV", "§2Cost §4$" + eff4Cost);
        createDisplay(Material.ENCHANTED_BOOK, i, 36, "§4Efficiency V", "§2Cost §4$" + eff5Cost);
    }
}

