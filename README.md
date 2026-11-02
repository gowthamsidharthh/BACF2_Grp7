# Restaurant Rush (SC2002 Group Project)

Turn-based restaurant management game played in the terminal.
This version is **Stage 2: Customer behaviour extension** (git tag `stage-2`).

Repository: https://github.com/gowthamsidharthh/BACF2_Grp7

## How to build and run

Requires Java 17 or newer (`java -version` to check).

```
javac -d out stage-2/src/*.java
java -cp out Main
```

## How to play

Each turn you choose one task for the Waiter and one for the Chef. Type the number shown.

- **Waiter:** 1 = seat a queued customer, 2 = take a seated customer's order, 3 = serve a READY order, 0 = wait
- **Chef:** 1 = cook one preparation unit of an order, 0 = wait
- The Cashier collects one payment per turn automatically.
- Customers are numbered C1-C6. Each customer's order has the same number.

## Menu

| Item | Price | Prep units |
|---|---|---|
| Salad | $9.00 | 1 |
| Burger | $15.00 | 1 |
| Pasta | $18.00 | 2 |

## Customer types

| Customer | Loss per waiting turn | Bill | Special behaviour |
|---|---|---|---|
| Regular | 8 | Full price | None |
| VIP | 5 | 10% off | None |
| Critic | 12 | Full price | Always orders the most expensive item on the menu (Pasta) |

Arrivals: C1 Regular (turn 1), C2 VIP (turn 4), C3 Regular (turn 7), C4 Critic (turn 10), C5 Regular (turn 13), C6 Regular (turn 16).

## Documented rules (our choices)

- **Food choice rule:** customers cycle through the menu by arrival number (C1 Salad, C2 Burger, C3 Pasta, ...). Critics override this and always pick the most expensive item.
- **Seating:** lowest-numbered free table.
- **Money:** stored as integer cents, shown with two decimals. Discounts round to the nearest cent.

## Win condition

After 18 turns, win if all three targets are met: at least 4 paid customers,
revenue at least $45.00, and average satisfaction of paid customers at least 60.
Otherwise defeat. There is no early ending.

## Reproducible test runs

```
java -cp out Main < stage-2/tests/victory-run.txt
java -cp out Main < stage-2/tests/defeat-run.txt
```

| Run | Result |
|---|---|
| `victory-run.txt` | 5 paid, $73.50, avg satisfaction 75.6, **VICTORY**. VIP pays $13.50 for a Burger; Critic orders Pasta and pays at satisfaction 52. |
| `defeat-run.txt` | Invalid inputs first, then staff always wait. Regular C1 and the Critic leave unhappy. **DEFEAT** |

## Class overview (Stage 2)

| Class | Type | Responsibility |
|---|---|---|
| `Main` | entry | Creates the UI and controller, starts the game |
| `ConsoleUI` | boundary | Prints game state, reads the manager's choices. No game rules. |
| `GameController` | control | 18-turn loop and the six steps of each turn, victory check |
| `ArrivalSchedule` | control | Which turns customers arrive and which type each is |
| `Restaurant` | entity | Tables, queue, customers, orders, totals; eating, payment and waiting-penalty rules |
| `Customer` | entity | Regular customer: satisfaction, status, food choice, bill, satisfaction loss |
| `VIPCustomer`, `CriticCustomer` | entity | Subclasses that override loss, bill or food choice |
| `Order`, `Table`, `Menu`, `MenuItem` | entity | Orders, tables and dishes |
| `Staff` (abstract), `Waiter`, `Chef`, `Cashier` | entity | Staff actions and their rule checks |
| `CustomerStatus`, `OrderStatus` | enum | States a customer or order moves through |
| `InvalidActionException` | exception | Explains why a manager choice was rejected |
| `Money` | helper | Formats cents as dollars |

## Stage comparisons

### Stage 1 → Stage 2 (VIP and Critic customers)

**New files**

| File | Responsibility |
|---|---|
| `VIPCustomer.java` | Subclass of `Customer`. Overrides `getSatisfactionLoss()` (5), `calculateBill()` (10% off) and `getTypeName()`. |
| `CriticCustomer.java` | Subclass of `Customer`. Overrides `getSatisfactionLoss()` (12), `chooseMenuItem()` (always orders the most expensive item) and `getTypeName()`. |
| `ArrivalSchedule.java` | Knows which turns customers arrive on and which type each one is. The only class that creates the different customer types. |

**Changed files**

| File | Change | Reason |
|---|---|---|
| `GameController.java` | Removed `ARRIVAL_TURNS` and `isArrivalTurn()`. Now asks `ArrivalSchedule` whether someone arrives and to create that customer. | Arrivals now come in different types. Moving creation into its own class keeps the controller free of type checks (Single Responsibility). A small setup change, as the brief expects. |

**Unchanged files:** `Customer`, `Waiter`, `Chef`, `Cashier`, `Restaurant`, `Order`, `Table`, `Menu`, `ConsoleUI` and all others are untouched. This works because Stage 1 already put the behaviour that differs (satisfaction loss, bill, food choice) inside `Customer` as methods. The subclasses override them, and code like `Restaurant.applyWaitingPenalty()` calls `c.loseSatisfaction()` without knowing the customer's type. That is polymorphism (dynamic binding) and the Open-Closed Principle.

Compare: https://github.com/gowthamsidharthh/BACF2_Grp7/compare/stage-1...stage-2

## Video timestamps

_To be filled in._
