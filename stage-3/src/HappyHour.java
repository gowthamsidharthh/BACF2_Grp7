/**
 * The Happy Hour pricing policy (Stage 3).
 *
 * - The manager can switch it on once per game, at the start of a turn.
 * - It lasts for that turn and the next one (2 turns).
 * - Orders taken while it is active get 20% off (after any VIP discount).
 * - While active, customers waiting for a table or food recover
 *   15 satisfaction at the start of each turn (up to 100).
 */
public class HappyHour {
    private static final int DURATION = 2;
    private static final double DISCOUNT_RATE = 0.20;
    private static final int RECOVERY_POINTS = 15;

    private boolean used;
    private int turnsRemaining;

    public HappyHour() {
        used = false;
        turnsRemaining = 0;
    }

    public boolean canActivate() {
        return !used;
    }

    public void activate() throws InvalidActionException {
        if (used) {
            throw new InvalidActionException("Happy Hour can only be used once per game.");
        }
        used = true;
        turnsRemaining = DURATION;
    }

    public boolean isActive() {
        return turnsRemaining > 0;
    }

    public int getTurnsRemaining() {
        return turnsRemaining;
    }

    public int getRecoveryPoints() {
        return RECOVERY_POINTS;
    }

    /** Returns the price after Happy Hour, or the same price if it is not active. */
    public int applyDiscount(int priceCents) {
        if (!isActive()) {
            return priceCents;
        }
        return (int) Math.round(priceCents * (1 - DISCOUNT_RATE));
    }

    /** Called at the end of every turn to count down the remaining duration. */
    public void endTurn() {
        if (turnsRemaining > 0) {
            turnsRemaining--;
        }
    }

    public String getStatusText() {
        if (isActive()) {
            return "ACTIVE (" + turnsRemaining + " turn(s) left, including this one)";
        } else if (used) {
            return "used";
        } else {
            return "available (can be switched on once)";
        }
    }
}
