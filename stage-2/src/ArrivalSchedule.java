/**
 * Decides WHEN customers arrive and WHAT TYPE each one is (Stage 2).
 *
 * This is the only place in the program that knows about the different
 * customer types. After a customer is created, the rest of the game
 * treats it as a plain Customer and polymorphism does the rest.
 *
 * Base setting: six arrivals. Two of the regular arrivals are replaced
 * by a VIP (turn 4) and a Critic (turn 10).
 */
public class ArrivalSchedule {
    private static final int[] ARRIVAL_TURNS = {1, 4, 7, 10, 13, 16};
    private static final String[] CUSTOMER_TYPES = {"Regular", "VIP", "Regular", "Critic", "Regular", "Regular"};

    public boolean isArrivalTurn(int turn) {
        return findIndex(turn) != -1;
    }

    /**
     * Creates the customer who arrives on this turn,
     * or returns null if nobody arrives.
     */
    public Customer createArrival(int turn, int customerId) {
        int index = findIndex(turn);
        if (index == -1) {
            return null;
        }
        switch (CUSTOMER_TYPES[index]) {
            case "VIP":
                return new VIPCustomer(customerId);
            case "Critic":
                return new CriticCustomer(customerId);
            default:
                return new Customer(customerId);
        }
    }

    /** Position of this turn in ARRIVAL_TURNS, or -1 if it is not an arrival turn. */
    private int findIndex(int turn) {
        for (int i = 0; i < ARRIVAL_TURNS.length; i++) {
            if (ARRIVAL_TURNS[i] == turn) {
                return i;
            }
        }
        return -1;
    }
}
