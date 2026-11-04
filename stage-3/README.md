# Restaurant Rush (SC2002 Group Project)

Turn-based restaurant management game played in the terminal.
This version is **Stage 3: New role and menu policy** (git tag `stage-3`).

Repository: https://github.com/gowthamsidharthh/BACF2_Grp7

## How to build and run

Requires Java 17 or newer (`java -version` to check).

```
javac -d out src/*.java
java -cp out Main
```

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

## Reproducible test runs

```
java -cp out Main < tests/victory-run.txt
java -cp out Main < tests/defeat-run.txt
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

New and changed files with reasons are listed in the root `README.md` (Stage comparisons).
Compare links:
- stage-1 → stage-2: https://github.com/gowthamsidharthh/BACF2_Grp7/compare/stage-1...stage-2
- stage-2 → stage-3: https://github.com/gowthamsidharthh/BACF2_Grp7/compare/stage-2...stage-3
