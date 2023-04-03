package net.nekozouneko.economy.items.listener;

import net.nekozouneko.economy.items.NSEItemType;
import net.nekozouneko.economy.items.NSEconomyItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent e) {
        for (ItemStack item : e.getEntity().getInventory().getContents()) {
            if (NSEconomyItems.isEconomyItem(item, NSEItemType.KEEP_INVENTORY_AMULET)) {
                e.getEntity().getInventory().remove(NSEconomyItems.keepInventoryAmulet());
                e.setKeepInventory(true);
                e.getEntity().sendMessage("§dお守りが被害を抑えてくれたが、壊れてしまった！");
            }
        }
    }

}
