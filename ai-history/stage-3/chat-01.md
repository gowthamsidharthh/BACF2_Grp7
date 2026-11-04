# AI usage - Yong Han - Stage 3

My Stage 3 work used an AI assistant to check the designs for the new role and the menu policy, and to confirm the discount order. I drew the additions as extending the existing classes before coding them, and re-ran both scripts after.

## Session

**Prompt 1:**
Stage 3 adds a Host that also seats. I drew it extending Staff, and I want the controller and the welcome screen to keep working off the staff list without naming the Host. Can you check that design?

**Reply 1:**
The design works because the controller and the UI already talk to staff through the list and through one duty method, not through the specific role. If the Host extends Staff and reveals its duty the same way the Waiter and Chef already do, the welcome screen keeps looping over the staff list and calling getDuty, so nothing anywhere has to mention Host by name. That keeps the turn loop closed to the new role, which is exactly the payoff for passing staff around as a list in the first place. One small thing to get right: seat the Host before the Waiter in a turn, so the order of operations reads clearly in the output and the two seating paths never collide.

**Prompt 2:**
For the combos I made ComboMeal a MenuItem that also holds two MenuItems, so the existing order and chef code treats it like any dish.

**Reply 2:**
Being a MenuItem (a dish) and also holding two MenuItems (the components) is the right call here, and it is not over-layering. The important consequence is that Order, Chef, Waiter and Cashier already treat everything as a MenuItem, so a combo flows through the whole kitchen with no changes anywhere. The prep units become the sum of its two parts, and the price is 90% of the combined cost rounded to the nearest cent, computed and locked once when the order is taken. That small rounding step matters because 90% of a sum can land on a half cent; round in the one place that builds the price and every later owner is consistent.
