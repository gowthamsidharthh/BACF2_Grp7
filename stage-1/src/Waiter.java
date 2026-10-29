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
        if (customer == null || customer.getStatus() != CustomerStatus.WAITING) {
            throw new InvalidActionException("That customer is not in the waiting queue.");
        }
        Table table = restaurant.findFreeTable();
        if (table == null) {
            throw new InvalidActionException("No free table. A table frees up only when its customer pays or leaves.");
        }
        table.seat(customer);
        customer.setTable(table);
        customer.setStatus(CustomerStatus.SEATED);
        restaurant.removeFromQueue(customer);
    }

    public Order takeOrder(Customer customer, Menu menu, Restaurant restaurant) throws InvalidActionException {
        if (customer == null || customer.getStatus() != CustomerStatus.SEATED) {
            throw new InvalidActionException("Only a seated customer who has not ordered yet can order.");
        }
        // The customer picks the food and works out their own bill.
        MenuItem item = customer.chooseMenuItem(menu);
        int price = customer.calculateBill(item.getPriceCents());

        Order order = new Order(customer, item, price);
        customer.setOrder(order);
        customer.setStatus(CustomerStatus.ORDERED);
        restaurant.addOrder(order);
        return order;
    }

    public void serveOrder(Order order, int turn, Restaurant restaurant) throws InvalidActionException {
        if (order == null || order.getStatus() != OrderStatus.READY) {
            throw new InvalidActionException("Only a READY order can be served.");
        }
        order.setStatus(OrderStatus.SERVED);
        Customer customer = order.getCustomer();
        customer.setStatus(CustomerStatus.EATING);
        customer.setServedTurn(turn);
        restaurant.recordServed();
    }
}
