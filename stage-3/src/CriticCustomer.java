/**
 * A food critic (Stage 2).
 * Impatient: loses 12 satisfaction per waiting turn.
 * Pays full price.
 *
 * Special behaviour (our documented rule): a critic always orders
 * the most expensive item on the menu, to test the kitchen.
 * With the Stage 2 menu this is the Pasta (2 preparation units),
 * so the manager has to plan the Chef's time carefully.
 */
public class CriticCustomer extends Customer {
    private static final int SATISFACTION_LOSS = 12;

    public CriticCustomer(int id) {
        super(id);
    }

    @Override
    public int getSatisfactionLoss() {
        return SATISFACTION_LOSS;
    }

    @Override
    public MenuItem chooseMenuItem(Menu menu) {
        MenuItem mostExpensive = menu.getItem(0);
        for (int i = 1; i < menu.size(); i++) {
            if (menu.getItem(i).getPriceCents() > mostExpensive.getPriceCents()) {
                mostExpensive = menu.getItem(i);
            }
        }
        return mostExpensive;
    }

    @Override
    public String getTypeName() {
        return "Critic";
    }
}
