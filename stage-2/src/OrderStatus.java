/**
 * Every stage an order can be in.
 */
public enum OrderStatus {
    PREPARING,  // taken by the Waiter, Chef still cooking
    READY,      // all preparation units done
    SERVED,     // Waiter brought it to the table
    PAID,       // customer has paid for it
    CANCELLED   // customer left before it was served
}
