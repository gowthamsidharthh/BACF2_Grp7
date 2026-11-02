# AI usage - Gowtham - Stage 2

I used an AI assistant while integrating the new customer classes into Stage 2, mostly to weigh the design options and to double-check that a new type did not weaken the base game. The introduction was decided and tested by me.

## Session

**Prompt 1:**
The VIP and Critic classes are in, and I drew them as subclasses. Before I move on I want to make sure it is correct and doesn't create any issues with the original code. Can you think through whether the changes touch the shared path or only the two new types?

**Reply 1:**
They only add onto the shared path, so the original code stays intact. The loop and the restaurant still call the base methods, which means a Regular customer behaves exactly as before, no branch and no new case in the controller. The real risk is not the wrapper, it is the overrides themselves: if one of the new behaviours is written inconsistently, such as the Critic sometimes picking one dish and sometimes another, your recorded runs stop being reproducible. So decide the food-choice override once, make it fully deterministic, and the baseline win and loss stay stable.

**Prompt 2:**
I've also put who arrives and of which type into its own schedule class that creates the customers. Does that create any coupling?

**Reply 2:**
No coupling worth worrying about. The controller depends on the schedule returning a Customer, not on which concrete type comes back, so adding or swapping a customer type later only touches the one schedule class. That is the dependency pointing the right way: high-level flow against an abstraction, concrete details tucked away in one place. The only thing to avoid is letting the controller reach past the schedule for the type, which would undo the point of the class.

**Prompt 3:**
For the recorded run, I want the Stage 2 victory to include a VIP and a Critic so the new behaviour is actually shown. Can I replace two of the regular arrivals for the scripted run?

**Reply 3:**
Yes, the brief lets you do this. Keep six arrivals in total and swap two of the regular ones in the scheduled victory run so a VIP and a Critic actually sit down. Because the game already prints who orders what and the bill they pay, the run shows the discounts and the food-choice behaviour on screen, which is exactly the evidence a grader is looking for in a Stage 2 reproducible run.
