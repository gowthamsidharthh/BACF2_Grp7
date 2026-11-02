# AI usage - Yong Han - Stage 2

For Stage 2 I used an AI assistant while adding the customer types. I mapped the subclasses and the arrival schedule myself, and I verified a Regular-only run still behaved as Stage 1 before I trusted the new types.

## Session

**Prompt 1:**
Before writing the code I drew a class diagram for Stage 2. I want VIP and Critic to behave differently without touching the controller. Can you check whether attaching the difference to the customer classes as overrides matches the diagram I sketched?

**Reply 1:**
Your diagram matches the design, and the reason it works is that Stage 1 already put the varying behaviour behind methods on the customer base. The game calls satisfaction loss, the bill and the food choice through that base, so VIP and Critic only override the methods that differ, and nothing else in the loop has to branch on type. That is the open/closed idea from the first stage paying off. One thing to keep an eye on: make the food-choice override explicit and deterministic, especially for the Critic, so a run with the same inputs cannot wander between two different dishes and break your reproducibility.

**Prompt 2:**
I'm putting which type arrives on which turn into a small schedule class that also creates the customers. Is that correct?

**Reply 2:**
It is correct, and it is the sort of small class that earns its place. One object decides which turns get a customer and which type to create, which means the controller and the turn loop never look at a customer's concrete type or perform a type check anywhere. If the cast of customers changes later, exactly one file changes. And because it returns the base Customer type, the rest of the program has no idea how the schedule made it, which is the direction you want dependencies pointing.

**Prompt 3:**
I want to be sure I did not change the regular behaviour. Can I just rerun the Stage 1 scripts against my Stage 2 build to confirm the baseline still holds?

**Reply 3:**
Almost, with one caveat about which build you use. You have to build and run the Stage 2 code with its own inputs, not reuse the Stage 1 classes. A Regular customer must still lose 8 satisfaction per waiting turn and pay full price, so replay the baseline victory and defeat scripts and the Stage 2 output should match Stage 1 exactly. Your new types will only show up in the runs you intentionally script to include them, which is what tells you the override did not quietly change the regular path.
