/**
 * A table holds at most one customer at a time.
 */
public class Table {
    private int id;
    private Customer customer;  // null means the table is free

    public Table(int id) {
        this.id = id;
        this.customer = null;
    }

    public boolean isFree() {
        return customer == null;
    }

    public void seat(Customer customer) {
        this.customer = customer;
    }

    public void free() {
        this.customer = null;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }
}
