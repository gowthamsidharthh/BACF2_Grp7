# AI usage - Gowtham - Stage 3

My Stage 3 work used an AI assistant for drafting the new features and for reviewing an encapsulation pass that replaced direct field setting with behaviour methods. I verified that pass kept the recorded runs byte identical.

## Session

**Prompt 1:**
I've changed the customer and order state changes into behaviour methods like seatAt, startEating and markServed. I want to make sure there are no rule change. The recorded victory and defeat runs must come out identical.

**Reply 1:**
It reads as a clean refactor. The state changes are identical, they are simply reached through methods now, so the recorded victory and defeat runs should come out byte for byte the same. That identical output is your proof that moving to behaviour methods did not smuggle in a rule change. One consistency detail while you are in there: keep the invalid-choice messages uniform across the find methods, so the controller reports one clear reason no matter which lookup fails, otherwise the same mistake can read differently depending on who triggers it.

**Prompt 2:**
Then the new role: a Host that seats. I've kept it out of the loop's logic by having the controller pass the Host, Waiter, Chef and Cashier around as one staff list. Confirm nothing needs to know the Host by name.

**Reply 2:**
Nothing needs to know the Host by name. The welcome screen and the turn loop already operate on the staff list and call each role's duty method, so a Host that behaves like the rest slots straight in without a name check anywhere. That is the earlier polymorphism decision paying off, the same way VIP and Critic dropped in for Stage 2. The only rule to keep straight is that the Host seats one customer a turn in addition to the Waiter's task, and the two share the exact same seating method, so they cannot disagree about where a customer sits.

**Prompt 3:**
Happy Hour as its own policy object, applied after any customer discount and locked at order time, once per game for two turns. Does that match the brief's wording?

**Reply 3:**
That matches the wording. Apply the customer's own discount first, then the Happy Hour discount on top, lock the cents when the order is taken, and let Happy Hour run once, for that turn and the next. A single policy object is the right home for it: it keeps the pricing rule out of the staff class and out of the controller, which is consistent with how the rest of the game pushes rules down to the domain. Just remember the recovery side too, waiting customers gain 15 satisfaction back while it is active, which is the part that makes the choice a real trade.
