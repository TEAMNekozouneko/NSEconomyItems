package net.nekozouneko.economy.items.listener;

import net.nekozouneko.economy.items.NSEItemType;
import net.nekozouneko.economy.items.NSEconomyItems;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * 限定アイテムで指定のアクションをさせないようにするリスナー
 */
public final class DisableActionListener implements Listener {

    @EventHandler
    public void onCraft(CraftItemEvent e) {
        boolean isLimited = false;
        ItemStack limited = null;
        for (ItemStack item : e.getInventory().getMatrix()) {
            isLimited = NSEconomyItems.isEconomyItem(item, NSEItemType.KEEP_INVENTORY_AMULET);

            if (isLimited) {
                limited = item;
                break;
            }
        }

        if (isLimited) {
            e.getWhoClicked().sendMessage("§c限定アイテム §f["+ limited.getItemMeta().getDisplayName() +"§f] §cはクラフトに使用できません。");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (
                NSEconomyItems.isEconomyItem(e.getItemInHand(), NSEItemType.KEEP_INVENTORY_AMULET)
        ) {
            e.getPlayer().sendMessage("§c限定アイテム §f["+ e.getItemInHand().getItemMeta().getDisplayName() +"§f] §cは設置できません。");
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPrepareAnvil(PrepareAnvilEvent e) {
        Bukkit.broadcastMessage(e.getInventory().getSize() + " is anvil inv size");
        Bukkit.broadcastMessage(Arrays.toString(e.getInventory().getContents()));

        boolean isLimited = false;
        ItemStack limited = null;
        for (ItemStack item : e.getInventory().getContents()) {
            isLimited = NSEconomyItems.isEconomyItem(item, NSEItemType.KEEP_INVENTORY_AMULET);

            if (isLimited) {
                limited = item;
                break;
            }
        }

        if (isLimited) {
            e.setResult(null);
        }
    }

}
