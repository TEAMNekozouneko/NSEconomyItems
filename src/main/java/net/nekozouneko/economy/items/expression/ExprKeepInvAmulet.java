package net.nekozouneko.economy.items.expression;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.skript.doc.Name;

import net.nekozouneko.economy.items.NSEconomyItems;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;


public final class ExprKeepInvAmulet extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprKeepInvAmulet.class, ItemStack.class, ExpressionType.SIMPLE,
                "[the] economy items of (keep inventory|keepinv) amulet"
        );
    }

    @Override
    protected ItemStack[] get(Event event) {
        return new ItemStack[] {NSEconomyItems.keepInventoryAmulet()};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public String toString(Event event, boolean b) {
        return "The economy items of Keep Inventory amult";
    }

    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult) {
        return NSEconomyItems.getInstance() != null;
    }
}
