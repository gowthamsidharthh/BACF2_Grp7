/**
 * The Chef does one preparation unit of one order per turn.
 */
public class Chef extends Staff {

    public Chef() {
        super("Chef");
    }

    @Override
    public String getDuty() {
        return "advance one order by one preparation unit (one per turn)";
    }

    public void cook(Order order) throws InvalidActionException {
        if (order == null || order.getStatus() != OrderStatus.PREPARING) {
            throw new InvalidActionException("That order is not waiting to be cooked.");
        }
        order.prepareOneUnit();
    }
}
