import java.util.ArrayList;

/**
 * Holds all the dishes the restaurant sells.
 * Composition: a Menu HAS-A list of MenuItems.
 */
public class Menu {
    private ArrayList<MenuItem> items;

    public Menu() {
        items = new ArrayList<>();
        items.add(new MenuItem("Salad", 900, 1));
        items.add(new MenuItem("Burger", 1500, 1));
        items.add(new MenuItem("Pasta", 1800, 2));

        // Stage 3: combos are built from the items above
        MenuItem salad = items.get(0);
        MenuItem burger = items.get(1);
        MenuItem pasta = items.get(2);
        items.add(new ComboMeal(salad, burger));  // $21.60, 2 units
        items.add(new ComboMeal(salad, pasta));   // $24.30, 3 units
    }

    public MenuItem getItem(int index) {
        return items.get(index);
    }

    public int size() {
        return items.size();
    }
}
