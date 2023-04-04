package net.nekozouneko.economy.items.condition;

import ch.njol.skript.Skript;
import ch.njol.skript.doc.Examples;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.google.common.base.Preconditions;
import net.nekozouneko.economy.items.NSEItemType;
import net.nekozouneko.economy.items.NSEconomyItems;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

@Examples("{_itemstack} is economy item of \"KEEP_INVENTORY_AMULET\"")
public final class CondIsEconomyItem extends Condition {

    static {
        Skript.registerCondition(
                CondIsEconomyItem.class,
                "%itemstack% is eco[nomy] item of %string%"
        );
    }

    private Expression<ItemStack> item;
    private Expression<String> type;

    @Override
    public boolean check(Event event) {
        ItemStack it = item.getSingle(event);
        NSEItemType ty = NSEItemType.valueOf(type.getSingle(event));

        Preconditions.checkNotNull(it);
        Preconditions.checkNotNull(ty);

        return NSEconomyItems.isEconomyItem(it, ty);
    }

    @Override
    public String toString(Event event, boolean b) {
        return item.getSingle(event) + " is economy item of " + type.getSingle(event);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        item = ((Expression<ItemStack>) expressions[0]);
        type = ((Expression<String>) expressions[1]);
        return item != null && type != null && NSEconomyItems.getInstance() != null;
    }
}
