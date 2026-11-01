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
    }

    // ----- arrivals and the queue -----

    public void addArrival(Customer customer) {
        waitingQueue.add(customer);
        allCustomers.add(customer);
    }

    public void removeFromQueue(Customer customer) {
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

    public Customer findCustomer(int id) {
        for (Customer c : allCustomers) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Order findOrder(int id) {
        for (Order o : orders) {
            if (o.getId() == id) {
                return o;
            }
        }
        return null;
    }

    // ----- end-of-turn rules -----

    /** Step 4a: customers served on an earlier turn finish eating. */
    public void finishEating(int turn) {
        for (Customer c : allCustomers) {
            if (c.getStatus() == CustomerStatus.EATING && c.getServedTurn() < turn) {
                c.setStatus(CustomerStatus.READY_TO_PAY);
                c.setReadyToPayTurn(turn);
            }
        }
    }

    /** Called by the Cashier when a customer pays. */
    public void recordPayment(Customer customer) {
        Order order = customer.getOrder();
        revenueCents += order.getPriceCents();
        paidCount++;
        totalPaidSatisfaction += customer.getSatisfaction();

        order.setStatus(OrderStatus.PAID);
        customer.setStatus(CustomerStatus.PAID);
        customer.getTable().free();
        customer.setTable(null);
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
            c.getOrder().setStatus(OrderStatus.CANCELLED);
        }
        if (c.getTable() != null) {
            c.getTable().free();
            c.setTable(null);
        }
        waitingQueue.remove(c);
        c.setStatus(CustomerStatus.LEFT);
        unhappyDepartures++;
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
