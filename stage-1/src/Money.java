/**
 * Small helper for showing money.
 * The method is static because it does not need any object data:
 * we call it as Money.format(1800) without creating a Money object.
 */
public class Money {
    public static String format(int cents) {
        return String.format("$%.2f", cents / 100.0);
    }
}
