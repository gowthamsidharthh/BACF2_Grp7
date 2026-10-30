# Restaurant Rush (SC2002 Group Project)

Turn-based restaurant management game played in the terminal.
This version is **Stage 1: Basic restaurant operation** (git tag `stage-1`).

Repository: https://github.com/gowthamsidharthh/BACF2_Grp7

## How to build and run

Requires Java 17 or newer (`java -version` to check).

```
javac -d out stage-1/src/*.java
java -cp out Main
```

## How to play

Each turn you choose one task for the Waiter and one for the Chef. Type the number shown.

- **Waiter:** 1 = seat a queued customer, 2 = take a seated customer's order, 3 = serve a READY order, 0 = wait
- **Chef:** 1 = cook one preparation unit of an order, 0 = wait
- The Cashier collects one payment per turn automatically.
- Customers are numbered C1-C6. Each customer's order has the same number (C3's order is Order 3).

If a choice is not allowed, the game explains why and you choose again without losing the task.

## Menu

| Item | Price | Prep units |
|---|---|---|
| Salad | $9.00 | 1 |
| Burger | $15.00 | 1 |
| Pasta | $18.00 | 2 |

## Customer types

Regular customers only in Stage 1: lose 8 satisfaction per waiting turn, pay full price.
Arrivals: one customer at the start of turns 1, 4, 7, 10, 13 and 16 (six in total).

## Documented rules (our choices)

- **Food choice rule:** customers choose their own dish, cycling through the menu by arrival number: C1 Salad, C2 Burger, C3 Pasta, C4 Salad, C5 Burger, C6 Pasta. Fully deterministic, so runs are reproducible.
- **Seating:** a seated customer goes to the lowest-numbered free table.
- **Display:** the start-of-turn screen is shown after that turn's arrival, so the manager can see the new customer.
- **Money:** stored as integer cents, shown with two decimals.

## Win condition

After 18 turns, win if all three targets are met: at least 4 paid customers,
revenue at least $45.00, and average satisfaction of paid customers at least 60.
Otherwise defeat. There is no early ending.

## Reproducible test runs

```
java -cp out Main < stage-1/tests/victory-run.txt
java -cp out Main < stage-1/tests/defeat-run.txt
```

| Run | Strategy | Result |
|---|---|---|
| `victory-run.txt` | Seat, order, cook and serve each customer as fast as possible | 5 paid, $66.00, avg satisfaction 79.2, **VICTORY** |
| `defeat-run.txt` | Starts with invalid inputs (text, serving a non-READY order, option 9, ordering for an unseated customer), then staff always wait | 0 paid, 2 unhappy departures, **DEFEAT** |

## Class overview (Stage 1)

| Class | Type | Responsibility |
|---|---|---|
| `Main` | entry | Creates the UI and controller, starts the game |
| `ConsoleUI` | boundary | Prints game state, reads the manager's choices. No game rules. |
| `GameController` | control | 18-turn loop and the six steps of each turn, victory check |
| `Restaurant` | entity | Tables, queue, customers, orders, revenue and totals; eating, payment and waiting-penalty rules |
| `Customer` | entity | Satisfaction, status, food choice, bill and satisfaction loss (overridable for Stage 2) |
| `Order` | entity | One dish being cooked, its locked price and status |
| `Table` | entity | Holds at most one customer |
| `Menu`, `MenuItem` | entity | The three dishes with price (cents) and preparation units |
| `Staff` (abstract) | entity | Parent of all staff |
| `Waiter`, `Chef`, `Cashier` | entity | Each staff member's actions and their rule checks |
| `CustomerStatus`, `OrderStatus` | enum | The states a customer or order moves through |
| `InvalidActionException` | exception | Explains why a manager choice was rejected |
| `Money` | helper | Formats cents as dollars |

## Stage comparisons

From Stage 2 onward, this file lists the new and changed files for each stage
transition, with reasons and compare links.

## Video timestamps

_To be filled in._
