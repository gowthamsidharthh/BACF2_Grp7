/**
 * The Host (Stage 3) seats at most one queued customer per turn,
 * in addition to whatever the Waiter does.
 *
 * Host extends Staff, not Waiter: a Host cannot take orders or serve
 * food, so it would be wrong to inherit those methods from Waiter.
 */
public class Host extends Staff {

    public Host() {
        super("Host");
    }

    @Override
    public String getDuty() {
        return "seat one queued customer (one per turn, on top of the Waiter's task)";
    }

    public void seatCustomer(Customer customer, Restaurant restaurant) throws InvalidActionException {
        restaurant.seatCustomer(customer);
    }
}
