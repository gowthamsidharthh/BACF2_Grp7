import java.util.ArrayList;

/**
 * Holds the state of the restaurant: tables, the waiting queue,
 * every customer and order, and the running totals (revenue etc.).
 */
public class Restaurant {
    private ArrayList<Table> tables;
    private ArrayList<Customer> waitingQueue;   // customers without a table, in arrival order
    private ArrayList<Customer> allCustomers;   // every customer who has arrived
    private ArrayList<Order> orders;
    private HappyHour happyHour;                // Stage 3 pricing policy

    private int revenueCents;
    private int paidCount;
    private int totalPaidSatisfaction;  // sum of satisfaction recorded at payment
    private int servedCount;
    private int unhappyDepartures;

    public Restaurant(int numberOfTables) {
        tables = new ArrayList<>();
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
        waitingQueue = new ArrayList<>();
        allCustomers = new ArrayList<>();
        orders = new ArrayList<>();
        happyHour = new HappyHour();
    }

    // ----- arrivals and the queue -----

    public void addArrival(Customer customer) {
        waitingQueue.add(customer);
        allCustomers.add(customer);
    }

    /**
     * Seats a queued customer at the first free table.
     * Stage 3: moved here from Waiter so that both the Waiter and the Host
     * use the same seating rules (no duplicated code).
     */
    public void seatCustomer(Customer customer) throws InvalidActionException {
        if (customer == null || customer.getStatus() != CustomerStatus.WAITING) {
            throw new InvalidActionException("That customer is not in the waiting queue.");
        }
        Table table = findFreeTable();
        if (table == null) {
            throw new InvalidActionException("No free table. A table frees up only when its customer pays or leaves.");
        }
        table.seat(customer);
        customer.seatAt(table);
        waitingQueue.remove(customer);
    }

    public Table findFreeTable() {
        for (Table t : tables) {
            if (t.isFree()) {
                return t;
            }
        }
        return null;
    }

    /**
     * Finds a customer by number, or throws so the manager can be told
     * clearly why the choice was rejected and chooses again.
     */
    public Customer findCustomer(int id) throws InvalidActionException {
        for (Customer c : allCustomers) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new InvalidActionException("There is no customer with number C" + id + ".");
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    /** Finds an order by number, or throws with a clear reason. */
    public Order findOrder(int id) throws InvalidActionException {
        for (Order o : orders) {
            if (o.getId() == id) {
                return o;
            }
        }
        throw new InvalidActionException("There is no order with number " + id + ".");
    }

    // ----- end-of-turn rules -----

    /** Step 4a: customers served on an earlier turn finish eating. */
    public void finishEating(int turn) {
        for (Customer c : allCustomers) {
            if (c.getStatus() == CustomerStatus.EATING && c.getServedTurn() < turn) {
                c.becomeReadyToPay(turn);
            }
        }
    }

    /** Called by the Cashier when a customer pays. */
    public void recordPayment(Customer customer) {
        Order order = customer.getOrder();
        revenueCents += order.getPriceCents();
        paidCount++;
        totalPaidSatisfaction += customer.getSatisfaction();

        order.markPaid();
        customer.markPaid();
        customer.getTable().free();
        customer.clearTable();
    }

    /**
     * Step 5: everyone still waiting for a table or food loses satisfaction.
     * Anyone who reaches 0 leaves without paying.
     * Returns the customers who left this turn (so the UI can show them).
     */
    public ArrayList<Customer> applyWaitingPenalty() {
        ArrayList<Customer> leavers = new ArrayList<>();
        for (Customer c : allCustomers) {
            if (c.isWaitingForService()) {
                c.loseSatisfaction();
                if (c.getSatisfaction() == 0) {
                    leavers.add(c);
                }
            }
        }
        for (Customer c : leavers) {
            customerLeaves(c);
        }
        return leavers;
    }

    private void customerLeaves(Customer c) {
        if (c.getOrder() != null) {
            c.getOrder().cancel();
        }
        if (c.getTable() != null) {
            c.getTable().free();
        }
        waitingQueue.remove(c);
        c.clearTable();
        c.markLeft();
        unhappyDepartures++;
    }

    /** Stage 3: while Happy Hour is active, waiting customers recover satisfaction. */
    public void applyHappyHourRecovery() {
        if (!happyHour.isActive()) {
            return;
        }
        for (Customer c : allCustomers) {
            if (c.isWaitingForService()) {
                c.recoverSatisfaction(happyHour.getRecoveryPoints());
            }
        }
    }

    public void recordServed() {
        servedCount++;
    }

    // ----- results -----

    /** Average satisfaction of paid customers, or -1 if nobody has paid. */
    public double getAverageSatisfaction() {
        if (paidCount == 0) {
            return -1;
        }
        return (double) totalPaidSatisfaction / paidCount;
    }

    // ----- getters -----

    public ArrayList<Table> getTables() {
        return tables;
    }

    public ArrayList<Customer> getWaitingQueue() {
        return waitingQueue;
    }

    public ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public HappyHour getHappyHour() {
        return happyHour;
    }

    public int getRevenueCents() {
        return revenueCents;
    }

    public int getPaidCount() {
        return paidCount;
    }

    public int getServedCount() {
        return servedCount;
    }

    public int getUnhappyDepartures() {
        return unhappyDepartures;
    }
}
