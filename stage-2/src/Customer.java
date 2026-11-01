/**
 * A regular customer.
 *
 * The methods getSatisfactionLoss(), calculateBill() and chooseMenuItem()
 * are kept inside the customer on purpose: if other kinds of customer
 * are needed later, they can extend this class and override them
 * (polymorphism), so the GameController never needs type checks.
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
     * cycling through the menu: Salad, Burger, Pasta, Salad, Burger, Pasta.
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

    public void loseSatisfaction() {
        satisfaction = satisfaction - getSatisfactionLoss();
        if (satisfaction < 0) {
            satisfaction = 0;
        }
    }

    // ----- getters and setters -----

    public int getId() {
        return id;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getServedTurn() {
        return servedTurn;
    }

    public void setServedTurn(int servedTurn) {
        this.servedTurn = servedTurn;
    }

    public int getReadyToPayTurn() {
        return readyToPayTurn;
    }

    public void setReadyToPayTurn(int readyToPayTurn) {
        this.readyToPayTurn = readyToPayTurn;
    }

    @Override
    public String toString() {
        return "C" + id + " (" + getTypeName() + ")";
    }
}
