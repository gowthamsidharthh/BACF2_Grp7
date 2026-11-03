/**
 * A VIP customer (Stage 2).
 * Patient: loses only 5 satisfaction per waiting turn.
 * Gets 10% off the bill.
 *
 * Everything else (status, table, order, food choice) is inherited
 * from Customer. We only override the behaviour that is different.
 */
public class VIPCustomer extends Customer {
    private static final int SATISFACTION_LOSS = 5;
    private static final double DISCOUNT_RATE = 0.10;

    public VIPCustomer(int id) {
        super(id);
    }

    @Override
    public int getSatisfactionLoss() {
        return SATISFACTION_LOSS;
    }

    /** 10% off, rounded to the nearest cent. */
    @Override
    public int calculateBill(int basePriceCents) {
        return (int) Math.round(basePriceCents * (1 - DISCOUNT_RATE));
    }

    @Override
    public String getTypeName() {
        return "VIP";
    }
}
