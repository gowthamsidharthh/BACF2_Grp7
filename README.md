# Restaurant Rush (SC2002 Group Project)

Turn-based restaurant management game played in the terminal.
This version is **Stage 3: New role and menu policy** (git tag `stage-3`).

Repository: https://github.com/gowthamsidharthh/BACF2_Grp7

## How to build and run

Requires Java 17 or newer (`java -version` to check).

```
javac -d out stage-3/src/*.java
java -cp out Main
```

Stages 1 and 2 build the same way from their own folders:
`javac -d out stage-1/src/*.java` (or `stage-2/src`), then `java -cp out Main`.

## How to play

At the start of each turn (until it is used) you are asked whether to switch on **Happy Hour**.
Then you choose one task each for the Host, the Waiter and the Chef, in that order.

- **Host:** 1 = seat a queued customer, 0 = wait
- **Waiter:** 1 = seat, 2 = take an order, 3 = serve a READY order, 0 = wait
- **Chef:** 1 = cook one preparation unit of an order, 0 = wait
- The Cashier collects one payment per turn automatically.

## Menu

| Item | Price | Prep units |
|---|---|---|
| Salad | $9.00 | 1 |
| Burger | $15.00 | 1 |
| Pasta | $18.00 | 2 |
| Salad + Burger Combo | $21.60 | 2 |
| Salad + Pasta Combo | $24.30 | 3 |

Combo price = 90% of the two items' combined price, rounded to the nearest cent.

## Customer types

| Customer | Loss per waiting turn | Bill | Special behaviour |
|---|---|---|---|
| Regular | 8 | Full price | None |
| VIP | 5 | 10% off | None |
| Critic | 12 | Full price | Always orders the most expensive item on the menu |

Arrivals: C1 Regular (turn 1), C2 VIP (turn 4), C3 Regular (turn 7), C4 Critic (turn 10), C5 Regular (turn 13), C6 Regular (turn 16).

## Happy Hour

- Can be switched on once per game, at the start of a turn. Lasts that turn and the next.
- Orders taken while active get 20% off, applied after the VIP discount. Example: VIP Burger = $15 × 0.9 × 0.8 = $10.80.
- While active, customers waiting for a table or food recover 15 satisfaction at the start of the turn (max 100).
- The price is locked when the order is taken.

## Documented rules (our choices)

- **Food choice rule (including combos):** customers cycle through the whole menu by arrival number: C1 Salad, C2 Burger, C3 Pasta, C4 Salad + Burger Combo, C5 Salad + Pasta Combo, C6 Salad. Critics override this and pick the most expensive item (the Salad + Pasta Combo).
- **Turn order:** Happy Hour choice → Host → Waiter → Chef.
- **Seating:** lowest-numbered free table.
- **Money:** stored as integer cents, shown with two decimals.

## Win condition

After 18 turns, win if all three targets are met: at least 4 paid customers,
revenue at least $45.00, and average satisfaction of paid customers at least 60.
Otherwise defeat. There is no early ending.

## Reproducible test runs

```
java -cp out Main < stage-3/tests/victory-run.txt
java -cp out Main < stage-3/tests/defeat-run.txt
```

| Run | Result |
|---|---|
| `victory-run.txt` | Happy Hour switched on at turn 4; VIP Burger costs $10.80. Host seats customers so the Waiter can take orders the same turn. 4 paid, $62.10, avg satisfaction 85.0, **VICTORY** |
| `defeat-run.txt` | Invalid inputs first (Happy Hour answer 5, Host seating customer 9, serving an unready order), then all staff wait. **DEFEAT** |

## Class overview (Stage 3)

| Class | Type | Responsibility |
|---|---|---|
| `Main` | entry | Creates the UI and controller, starts the game |
| `ConsoleUI` | boundary | Prints game state, reads the manager's choices. No game rules. |
| `GameController` | control | 18-turn loop and the steps of each turn, victory check |
| `ArrivalSchedule` | control | Which turns customers arrive and which type each is |
| `Restaurant` | entity | Tables, queue, customers, orders, totals, Happy Hour; seating, eating, payment and penalty rules |
| `Customer`, `VIPCustomer`, `CriticCustomer` | entity | Customer behaviour; subclasses override loss, bill or food choice |
| `MenuItem`, `ComboMeal`, `Menu` | entity | Dishes; a combo is a menu item made of two menu items |
| `HappyHour` | entity | The Happy Hour pricing policy and its remaining duration |
| `Order`, `Table` | entity | Orders and tables |
| `Staff` (abstract), `Host`, `Waiter`, `Chef`, `Cashier` | entity | Staff actions and their rule checks |
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

### Stage 2 → Stage 3 (Host, ComboMeal, Happy Hour)

**New files**

| File | Responsibility |
|---|---|
| `Host.java` | Subclass of `Staff`. Seats one queued customer per turn on top of the Waiter's task. |
| `ComboMeal.java` | Subclass of `MenuItem` (IS-A menu item) that is also composed of two `MenuItem`s (HAS-A). Prep units = sum; price = 90% of combined price, rounded to the nearest cent. |
| `HappyHour.java` | Pricing policy: once per game, lasts 2 turns, 20% off orders taken while active, waiting customers recover 15 satisfaction per active turn. |

**Changed files**

| File | Change | Reason |
|---|---|---|
| `Restaurant.java` | Added `seatCustomer()` (moved from `Waiter`), a `HappyHour` field with its getter, and `applyHappyHourRecovery()`. Removed `removeFromQueue()` (now done inside `seatCustomer()`). | The Waiter and the Host both seat customers. Keeping one copy of the seating rules avoids duplicated code. The restaurant owns its pricing policy. |
| `Waiter.java` | `seatCustomer()` now calls `restaurant.seatCustomer()`. `takeOrder()` applies Happy Hour after the customer's own discount. | Shared seating rules. The brief says VIP discount first, then Happy Hour, with the price locked when the order is taken. |
| `Customer.java` | Added `recoverSatisfaction()`. Updated the food-choice comment. | Needed for Happy Hour recovery (capped at 100). |
| `Menu.java` | Added two combos: Salad + Burger ($21.60, 2 units) and Salad + Pasta ($24.30, 3 units). | New menu policy. Because `ComboMeal` is a `MenuItem`, `Order`, `Chef`, `Waiter` and `Cashier` needed no changes for combos. |
| `GameController.java` | Added the Host step, the Happy Hour question at the start of each turn, recovery, and the countdown at turn end. Staff are passed to the UI as an `ArrayList<Staff>`. | New role and policy need new turn steps. The staff list means a future role will not change the welcome-screen method. |
| `ConsoleUI.java` | Added `askHostAction()` and `askActivateHappyHour()`. Shows Happy Hour status each turn. `showWelcome()` now loops over `ArrayList<Staff>` calling `getDuty()`. | Collect the new manager choices and show Happy Hour's remaining duration, as the brief requires. |

**Design decisions.** Host extends `Staff` rather than `Waiter`: a Host cannot take orders or serve food, so inheriting from `Waiter` would break Liskov Substitution. `ComboMeal` combines inheritance and composition (it IS-A `MenuItem` and HAS-A two `MenuItem`s). The food-choice rule extended without any code change: customers cycle through the whole menu, so C4 and C5 pick combos, and the Critic's most-expensive-item rule picks the Salad + Pasta Combo automatically.

**Code quality pass (within Stage 3):** customer and order state changes were moved into behaviour methods (`Customer.seatAt`, `Customer.placeOrder`, `Customer.startEating`, `Customer.becomeReadyToPay`, `Customer.markPaid`, `Customer.markLeft`, `Customer.clearTable`, `Order.markServed`, `Order.markPaid`, `Order.cancel`) and the public setters were removed. `findCustomer` / `findOrder` now throw `InvalidActionException` with a clear reason for unknown numbers. No game-rule behaviour changed; both recorded runs still produce identical results.

Compare: https://github.com/gowthamsidharthh/BACF2_Grp7/compare/stage-2...stage-3

## Video timestamps

_To be filled in._
