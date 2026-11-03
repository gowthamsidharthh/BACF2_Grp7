/**
 * Every stage a customer can be in, from arriving to leaving.
 */
public enum CustomerStatus {
    WAITING,       // in the queue, no table yet
    SEATED,        // at a table, has not ordered
    ORDERED,       // order placed, food not served yet
    EATING,        // food served, eating this turn
    READY_TO_PAY,  // finished eating, waiting for the Cashier
    PAID,          // paid and left happily
    LEFT           // satisfaction hit 0, left without paying
}
