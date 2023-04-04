package net.nekozouneko.economy.items.listener;

import net.nekozouneko.economy.items.NSEItemType;
import net.nekozouneko.economy.items.NSEconomyItems;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeathListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onDeath(PlayerDeathEvent e) {
        if (e.getKeepInventory()) return;

        for (ItemStack item : e.getDrops()) {
            if (item == null) continue;
            if (NSEconomyItems.isEconomyItem(item, NSEItemType.KEEP_INVENTORY_AMULET)) {
                if (item.getAmount() == 1)
                    e.getEntity().getInventory().remove(item);
                else item.setAmount(item.getAmount() - 1);
                e.setKeepInventory(true);
                e.setKeepLevel(true);
                e.getEntity().sendMessage("§dお守りが被害を抑えてくれたが、壊れてしまった！");
                e.getEntity().playSound(
                        e.getEntity(),
                        Sound.ENTITY_ITEM_BREAK,
                        1,1
                );
            }
        }
    }

}
