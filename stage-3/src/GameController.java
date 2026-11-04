import java.util.ArrayList;

/**
 * Runs the game: the 18-turn loop and the six steps of each turn.
 * It holds the game rules about ORDER of events, while the UI only
 * asks questions and prints things.
 */
public class GameController {
    public static final int MAX_TURNS = 18;
    public static final int NUMBER_OF_TABLES = 2;

    // Victory targets
    private static final int TARGET_PAID = 4;
    private static final int TARGET_REVENUE_CENTS = 4500;
    private static final int TARGET_AVG_SATISFACTION = 60;

    private Restaurant restaurant;
    private Menu menu;
    private Host host;
    private Waiter waiter;
    private Chef chef;
    private Cashier cashier;
    private ArrivalSchedule schedule;
    private ConsoleUI ui;
    private int nextCustomerId;
    private int turnsUsed;

    public GameController(ConsoleUI ui) {
        this.ui = ui;
        this.restaurant = new Restaurant(NUMBER_OF_TABLES);
        this.menu = new Menu();
        this.host = new Host();
        this.waiter = new Waiter();
        this.chef = new Chef();
        this.cashier = new Cashier();
        this.schedule = new ArrivalSchedule();
        this.nextCustomerId = 1;
    }

    public void run() {
        // Stage 3: staff are passed as one list, so adding a new role
        // does not change the UI method (polymorphism through getDuty()).
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.add(host);
        staffList.add(waiter);
        staffList.add(chef);
        staffList.add(cashier);

        ui.showWelcome(MAX_TURNS, menu, staffList);
        for (int turn = 1; turn <= MAX_TURNS; turn++) {
            playTurn(turn);
            turnsUsed = turn;
        }
        ui.showFinalResult(isVictory(), restaurant, turnsUsed);
    }

    private void playTurn(int turn) {
        // Step 1: arrivals
        if (schedule.isArrivalTurn(turn)) {
            Customer c = schedule.createArrival(turn, nextCustomerId);
            nextCustomerId++;
            restaurant.addArrival(c);
            ui.showMessage(c + " arrives and joins the queue.");
        }

        // Stage 3: Happy Hour can be switched on at the start of a turn
        HappyHour happyHour = restaurant.getHappyHour();
        if (happyHour.canActivate() && ui.askActivateHappyHour()) {
            try {
                happyHour.activate();
                ui.showMessage("Happy Hour is ON for this turn and the next!");
            } catch (InvalidActionException e) {
                ui.showError(e.getMessage());
            }
        }
        if (happyHour.isActive()) {
            restaurant.applyHappyHourRecovery();
            ui.showMessage("Happy Hour: waiting customers recover " + happyHour.getRecoveryPoints() + " satisfaction.");
        }

        ui.showTurnStart(turn, MAX_TURNS, restaurant);

        // Steps 2 and 3: Host seats first, then the Waiter, then the Chef
        doHostTurn();
        doWaiterTurn(turn);
        doChefTurn();

        // Step 4: eating, then payment
        restaurant.finishEating(turn);
        Customer payer = cashier.collectPayment(restaurant, turn);
        if (payer != null) {
            ui.showMessage("Cashier collects " + Money.format(payer.getOrder().getPriceCents())
                    + " from " + payer + ". Satisfaction recorded: " + payer.getSatisfaction());
        }

        // Step 5: waiting customers lose satisfaction, some may leave
        ArrayList<Customer> leavers = restaurant.applyWaitingPenalty();
        for (Customer c : leavers) {
            ui.showMessage(c + " got fed up and left without paying.");
        }

        happyHour.endTurn();
        ui.showEndOfTurn(turn, restaurant);
    }

    /** Stage 3: keeps asking until the manager picks a valid Host task (or waits). */
    private void doHostTurn() {
        boolean done = false;
        while (!done) {
            int choice = ui.askHostAction();
            try {
                if (choice == 0) {
                    ui.showMessage("Host waits.");
                } else if (choice == 1) {
                    Customer c = restaurant.findCustomer(ui.askNumber("Customer number to seat: C"));
                    host.seatCustomer(c, restaurant);
                    ui.showMessage("Host seats " + c + " at Table " + c.getTable().getId() + ".");
                } else {
                    throw new InvalidActionException("Please choose 0 or 1.");
                }
                done = true;
            } catch (InvalidActionException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /** Keeps asking until the manager picks a valid Waiter task (or waits). */
    private void doWaiterTurn(int turn) {
        boolean done = false;
        while (!done) {
            int choice = ui.askWaiterAction();
            try {
                switch (choice) {
                    case 0:
                        ui.showMessage("Waiter waits.");
                        break;
                    case 1: {
                        Customer c = restaurant.findCustomer(ui.askNumber("Customer number to seat: C"));
                        waiter.seatCustomer(c, restaurant);
                        ui.showMessage("Waiter seats " + c + " at Table " + c.getTable().getId() + ".");
                        break;
                    }
                    case 2: {
                        Customer c = restaurant.findCustomer(ui.askNumber("Customer number to take order from: C"));
                        Order o = waiter.takeOrder(c, menu, restaurant);
                        ui.showMessage(c + " orders " + o.getItem().getName()
                                + " (" + Money.format(o.getPriceCents()) + ").");
                        break;
                    }
                    case 3: {
                        Order o = restaurant.findOrder(ui.askNumber("Order number to serve: "));
                        waiter.serveOrder(o, turn, restaurant);
                        ui.showMessage("Waiter serves " + o.getItem().getName() + " to " + o.getCustomer() + ".");
                        break;
                    }
                    default:
                        throw new InvalidActionException("Please choose 0, 1, 2 or 3.");
                }
                done = true;
            } catch (InvalidActionException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /** Keeps asking until the manager picks a valid Chef task (or waits). */
    private void doChefTurn() {
        boolean done = false;
        while (!done) {
            int choice = ui.askChefAction();
            try {
                if (choice == 0) {
                    ui.showMessage("Chef waits.");
                } else if (choice == 1) {
                    Order o = restaurant.findOrder(ui.askNumber("Order number to cook: "));
                    chef.cook(o);
                    ui.showMessage("Chef works on " + o.getItem().getName() + " ("
                            + o.getUnitsDone() + "/" + o.getItem().getPrepUnits() + ")"
                            + (o.getStatus() == OrderStatus.READY ? " - now READY!" : "."));
                } else {
                    throw new InvalidActionException("Please choose 0 or 1.");
                }
                done = true;
            } catch (InvalidActionException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private boolean isVictory() {
        return restaurant.getPaidCount() >= TARGET_PAID
                && restaurant.getRevenueCents() >= TARGET_REVENUE_CENTS
                && restaurant.getAverageSatisfaction() >= TARGET_AVG_SATISFACTION;
    }
}
