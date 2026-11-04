/**
 * The Waiter does ONE of these per turn: seat, take an order, or serve.
 * Each method checks the rules first and throws InvalidActionException
 * if the action is not allowed, so no task is used up.
 */
public class Waiter extends Staff {

    public Waiter() {
        super("Waiter");
    }

    @Override
    public String getDuty() {
        return "seat a customer, take an order, or serve a READY dish (one per turn)";
    }

    public void seatCustomer(Customer customer, Restaurant restaurant) throws InvalidActionException {
        // Stage 3: the seating rules now live in Restaurant, shared with the Host
        restaurant.seatCustomer(customer);
    }

    public Order takeOrder(Customer customer, Menu menu, Restaurant restaurant) throws InvalidActionException {
        if (customer == null || customer.getStatus() != CustomerStatus.SEATED) {
            throw new InvalidActionException("Only a seated customer who has not ordered yet can order.");
        }
        // The customer picks the food and works out their own bill.
        MenuItem item = customer.chooseMenuItem(menu);
        int price = customer.calculateBill(item.getPriceCents());
        // Stage 3: Happy Hour is applied AFTER the customer's own discount (e.g. VIP).
        // The price is locked into the Order now, so later changes do not affect it.
        price = restaurant.getHappyHour().applyDiscount(price);

        Order order = new Order(customer, item, price);
        customer.placeOrder(order);
        restaurant.addOrder(order);
        return order;
    }

    public void serveOrder(Order order, int turn, Restaurant restaurant) throws InvalidActionException {
        if (order == null || order.getStatus() != OrderStatus.READY) {
            throw new InvalidActionException("Only a READY order can be served.");
        }
        order.markServed();
        order.getCustomer().startEating(turn);
        restaurant.recordServed();
    }
}
