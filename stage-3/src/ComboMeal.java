/**
 * A combo made of two existing menu items (Stage 3).
 *
 * Inheritance: a ComboMeal IS-A MenuItem, so Order, Chef and Waiter
 * can use it exactly like a normal dish without any changes.
 * Composition: a ComboMeal HAS-A first item and a second item.
 *
 * Preparation units = sum of the two items.
 * Price = 90% of the two prices added together, rounded to the nearest cent.
 * Example: Salad + Pasta = (900 + 1800) x 0.9 = 2430 cents = $24.30, 3 units.
 */
public class ComboMeal extends MenuItem {
    private static final double COMBO_RATE = 0.9;

    private MenuItem firstItem;
    private MenuItem secondItem;

    public ComboMeal(MenuItem firstItem, MenuItem secondItem) {
        super(firstItem.getName() + " + " + secondItem.getName() + " Combo",
                (int) Math.round((firstItem.getPriceCents() + secondItem.getPriceCents()) * COMBO_RATE),
                firstItem.getPrepUnits() + secondItem.getPrepUnits());
        this.firstItem = firstItem;
        this.secondItem = secondItem;
    }

    public MenuItem getFirstItem() {
        return firstItem;
    }

    public MenuItem getSecondItem() {
        return secondItem;
    }
}
