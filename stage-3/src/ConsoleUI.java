import java.util.Scanner;

/**
 * The command-line interface (the "boundary" class).
 * It ONLY prints information and reads the manager's choices.
 * No game rules live here.
 */
public class ConsoleUI {
    private Scanner sc;

    public ConsoleUI() {
        sc = new Scanner(System.in);
    }

    /** Reads a whole number, asking again if the input is not a number. */
    public int askNumber(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("  ! Please enter a whole number.");
            }
        }
    }

    public int askWaiterAction() {
        System.out.println("WAITER: 1) Seat customer  2) Take order  3) Serve READY order  0) Wait");
        return askNumber("Choose Waiter task: ");
    }

    public int askChefAction() {
        System.out.println("CHEF:   1) Cook one unit of an order  0) Wait");
        return askNumber("Choose Chef task: ");
    }

    public void showMessage(String message) {
        System.out.println("  > " + message);
    }

    public void showError(String message) {
        System.out.println("  ! Not allowed: " + message + " Try again.");
    }

    public void showWelcome(int maxTurns, Menu menu, Waiter waiter, Chef chef, Cashier cashier) {
        System.out.println("=========================================");
        System.out.println("        WELCOME TO RESTAURANT RUSH");
        System.out.println("=========================================");
        System.out.println("You are the manager. The game lasts " + maxTurns + " turns.");
        System.out.println("Win: at least 4 paid customers, revenue >= $45.00,");
        System.out.println("     and average satisfaction of paid customers >= 60.");
        System.out.println("Menu:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.println("  - " + menu.getItem(i));
        }
        System.out.println("Staff:");
        System.out.println("  - " + waiter.getName() + ": " + waiter.getDuty());
        System.out.println("  - " + chef.getName() + ": " + chef.getDuty());
        System.out.println("  - " + cashier.getName() + ": " + cashier.getDuty());
    }

    public void showTurnStart(int turn, int maxTurns, Restaurant r) {
        System.out.println();
        System.out.println("============== TURN " + turn + " / " + maxTurns + " ==============");
        System.out.println("Revenue: " + Money.format(r.getRevenueCents())
                + "   Avg satisfaction (paid): " + formatAverage(r.getAverageSatisfaction()));

        System.out.print("Waiting queue: ");
        if (r.getWaitingQueue().isEmpty()) {
            System.out.print("(empty)");
        }
        for (Customer c : r.getWaitingQueue()) {
            System.out.print(c + " sat=" + c.getSatisfaction() + "   ");
        }
        System.out.println();

        System.out.println("Tables:");
        for (Table t : r.getTables()) {
            if (t.isFree()) {
                System.out.println("  Table " + t.getId() + ": free");
            } else {
                Customer c = t.getCustomer();
                System.out.println("  Table " + t.getId() + ": " + c + " sat=" + c.getSatisfaction()
                        + " status=" + c.getStatus());
            }
        }

        System.out.println("Orders:");
        boolean anyOrder = false;
        for (Order o : r.getOrders()) {
            if (o.getStatus() == OrderStatus.PREPARING || o.getStatus() == OrderStatus.READY
                    || o.getStatus() == OrderStatus.SERVED) {
                System.out.println("  " + o);
                anyOrder = true;
            }
        }
        if (!anyOrder) {
            System.out.println("  (none)");
        }
        System.out.println("Staff tasks: Waiter (1 task), Chef (1 unit), Cashier (automatic)");
        System.out.println("-----------------------------------------");
    }

    public void showEndOfTurn(int turn, Restaurant r) {
        System.out.println("--- End of turn " + turn + ": served " + r.getServedCount()
                + ", unhappy departures " + r.getUnhappyDepartures()
                + ", revenue " + Money.format(r.getRevenueCents())
                + ", avg satisfaction " + formatAverage(r.getAverageSatisfaction())
                + ", turns used " + turn + " ---");
    }

    public void showFinalResult(boolean victory, Restaurant r, int turnsUsed) {
        System.out.println();
        System.out.println("=============== GAME OVER ===============");
        System.out.println("Paid customers:     " + r.getPaidCount());
        System.out.println("Dishes served:      " + r.getServedCount());
        System.out.println("Unhappy departures: " + r.getUnhappyDepartures());
        System.out.println("Revenue:            " + Money.format(r.getRevenueCents()));
        System.out.println("Avg satisfaction:   " + formatAverage(r.getAverageSatisfaction()));
        System.out.println("Turns used:         " + turnsUsed);
        System.out.println(victory ? "*** VICTORY! ***" : "*** DEFEAT ***");
    }

    private String formatAverage(double avg) {
        if (avg < 0) {
            return "N/A";
        }
        return String.format("%.1f", avg);
    }
}
