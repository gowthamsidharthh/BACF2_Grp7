/**
 * One customer's order. Each customer orders once, so the order uses
 * the same id as its customer (order 3 belongs to customer C3).
 *
 * The price is worked out and locked in when the order is taken.
 */
public class Order {
    private int id;
    private Customer customer;
    private MenuItem item;
    private int priceCents;
    private int unitsDone;
    private OrderStatus status;

    public Order(Customer customer, MenuItem item, int priceCents) {
        this.id = customer.getId();
        this.customer = customer;
        this.item = item;
        this.priceCents = priceCents;
        this.unitsDone = 0;
        this.status = OrderStatus.PREPARING;
    }

    /** The Chef calls this to do one unit of cooking. */
    public void prepareOneUnit() {
        unitsDone++;
        if (unitsDone >= item.getPrepUnits()) {
            status = OrderStatus.READY;
        }
    }

    /** Called by the Waiter when the dish reaches the table. */
    public void markServed() {
        status = OrderStatus.SERVED;
    }

    /** Called when the customer pays. */
    public void markPaid() {
        status = OrderStatus.PAID;
    }

    /** Called when the customer leaves before being served. */
    public void cancel() {
        status = OrderStatus.CANCELLED;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public int getUnitsDone() {
        return unitsDone;
    }

    public OrderStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Order " + id + ": " + item.getName() + " for " + customer
                + " [" + status + ", " + unitsDone + "/" + item.getPrepUnits() + " units, "
                + Money.format(priceCents) + "]";
    }
}
