package net.nekozouneko.economy.items;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import com.google.common.base.Preconditions;
import net.nekozouneko.economy.items.listener.DisableActionListener;
import net.nekozouneko.economy.items.listener.PlayerDeathListener;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Arrays;

public final class NSEconomyItems extends JavaPlugin {

    // アイテム保持のお守り
    public static ItemStack keepInventoryAmulet() {
        ItemStack item = new ItemStack(Material.SOUL_LANTERN);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer c = meta.getPersistentDataContainer();

        meta.setDisplayName("§d保持のお守り");
        meta.setLore(Arrays.asList(
                "§f所持していると1回だけ死んだ時の被害がなくなるとか...?",
                " ",
                "§5効果：",
                "§9死んだ際、1回だけアイテム, 経験値と所持金を保持",
                "§8§o(限定アイテム)"
        ));

        c.set(
                new NamespacedKey(getInstance(), "item_type"),
                PersistentDataType.STRING, NSEItemType.KEEP_INVENTORY_AMULET.name()
        );

        item.setItemMeta(meta);
        return item;
    }

    public static boolean isEconomyItem(ItemStack item, NSEItemType type) {
        Preconditions.checkArgument(item != null && type != null && item.getType() != Material.AIR);
        PersistentDataContainer c = item.getItemMeta().getPersistentDataContainer();

        return c.has(
                new NamespacedKey(getInstance(), "item_type"), PersistentDataType.STRING
        ) && c.get(
                new NamespacedKey(getInstance(), "item_type"), PersistentDataType.STRING
        ).equals(type.name());
    }

    private static NSEconomyItems instance;

    public static NSEconomyItems getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Skriptのアドオンとして登録
        if (getServer().getPluginManager().getPlugin("Skript") != null) {
            SkriptAddon addon = Skript.registerAddon(this);

            try {
                addon.loadClasses("net.nekozouneko.economy.items", "expression");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        getServer().getPluginManager().registerEvents(new DisableActionListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
    }
}
