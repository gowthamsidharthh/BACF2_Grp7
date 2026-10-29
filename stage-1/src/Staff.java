/**
 * Parent class for every staff member.
 * Abstract because we never create a plain "Staff" - only a
 * Waiter, Chef or Cashier.
 */
public abstract class Staff {
    private String name;

    public Staff(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /** Short description of what this staff member can do each turn. */
    public abstract String getDuty();
}
