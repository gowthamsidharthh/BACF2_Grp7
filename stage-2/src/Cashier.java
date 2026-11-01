/**
 * The Cashier works automatically: each turn it takes payment from
 * at most one READY_TO_PAY customer, oldest first.
 */
public class Cashier extends Staff {

    public Cashier() {
        super("Cashier");
    }

    @Override
    public String getDuty() {
        return "automatically collect one payment per turn (oldest first)";
    }

    /**
     * Finds the customer who has been ready to pay the longest.
     * A customer who became READY_TO_PAY this turn must wait until next turn.
     * Returns the customer who paid, or null if nobody could pay.
     */
    public Customer collectPayment(Restaurant restaurant, int turn) {
        Customer oldest = null;
        for (Customer c : restaurant.getAllCustomers()) {
            if (c.getStatus() == CustomerStatus.READY_TO_PAY && c.getReadyToPayTurn() < turn) {
                if (oldest == null || c.getReadyToPayTurn() < oldest.getReadyToPayTurn()) {
                    oldest = c;
                }
            }
        }
        if (oldest != null) {
            restaurant.recordPayment(oldest);
        }
        return oldest;
    }
}
