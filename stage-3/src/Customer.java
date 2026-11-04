/**
 * A customer.
 *
 * Behaviour that differs between customer types (satisfaction loss,
 * bill, food choice) lives here and in subclasses, so a new type can
 * be added by overriding these methods without touching the rest of
 * the game (polymorphism, Open-Closed Principle).
 *
 * State changes go through small behaviour methods (seatAt, placeOrder,
 * startEating, ...) instead of letting other classes set the fields
 * directly. This keeps the customer's own rules in one place and makes
 * the state changes visible and consistent.
 */
public class Customer {
    private static final int SATISFACTION_LOSS = 8;
    private static final int MAX_SATISFACTION = 100;

    private int id;
    private int satisfaction;
    private CustomerStatus status;
    private Table table;
    private Order order;
    private int servedTurn;      // turn the food was served
    private int readyToPayTurn;  // turn the customer finished eating

    public Customer(int id) {
        this.id = id;
        this.satisfaction = MAX_SATISFACTION;
        this.status = CustomerStatus.WAITING;
    }

    /** How many points this customer loses per waiting turn. */
    public int getSatisfactionLoss() {
        return SATISFACTION_LOSS;
    }

    /** Regular customers pay the full price. */
    public int calculateBill(int basePriceCents) {
        return basePriceCents;
    }

    /**
     * Customers choose their own food (the manager never does).
     * Documented rule: customer 1 picks item 1, customer 2 picks item 2, ...
     * cycling through the whole menu. In Stage 3 the menu also has two combos,
     * so customers 4 and 5 pick combos (unless their subclass overrides this).
     */
    public MenuItem chooseMenuItem(Menu menu) {
        return menu.getItem((id - 1) % menu.size());
    }

    public String getTypeName() {
        return "Regular";
    }

    /** True if the customer is still waiting for a table or for food. */
    public boolean isWaitingForService() {
        return status == CustomerStatus.WAITING
                || status == CustomerStatus.SEATED
                || status == CustomerStatus.ORDERED;
    }

    // ----- state changes (encapsulated) -----

    /** Seats the customer at a table (called by the Restaurant). */
    public void seatAt(Table table) {
        this.table = table;
        this.status = CustomerStatus.SEATED;
    }

    /** Places an order, taken by the Waiter. */
    public void placeOrder(Order order) {
        this.order = order;
        this.status = CustomerStatus.ORDERED;
    }

    /** The dish was served this turn: the customer starts eating. */
    public void startEating(int turn) {
        this.status = CustomerStatus.EATING;
        this.servedTurn = turn;
    }

    /** Finished a full turn of eating and is now ready to pay. */
    public void becomeReadyToPay(int turn) {
        this.status = CustomerStatus.READY_TO_PAY;
        this.readyToPayTurn = turn;
    }

    /** Paid the bill and left happily. */
    public void markPaid() {
        this.status = CustomerStatus.PAID;
    }

    /** Left without paying (satisfaction hit 0). */
    public void markLeft() {
        this.status = CustomerStatus.LEFT;
    }

    /** Frees the customer from the table (after paying or leaving). */
    public void clearTable() {
        this.table = null;
    }

    /** Stage 3: Happy Hour lets waiting customers recover satisfaction (max 100). */
    public void recoverSatisfaction(int points) {
        satisfaction = satisfaction + points;
        if (satisfaction > MAX_SATISFACTION) {
            satisfaction = MAX_SATISFACTION;
        }
    }

    public void loseSatisfaction() {
        satisfaction = satisfaction - getSatisfactionLoss();
        if (satisfaction < 0) {
            satisfaction = 0;
        }
    }

    // ----- getters -----

    public int getId() {
        return id;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public Table getTable() {
        return table;
    }

    public Order getOrder() {
        return order;
    }

    public int getServedTurn() {
        return servedTurn;
    }

    public int getReadyToPayTurn() {
        return readyToPayTurn;
    }

    @Override
    public String toString() {
        return "C" + id + " (" + getTypeName() + ")";
    }
}
