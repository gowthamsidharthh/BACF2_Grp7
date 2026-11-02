# AI usage - Austin - Stage 2

I used an AI assistant to help produce and check the Stage 2 class diagram and the stage comparison notes. I generated the diagram from the code, reviewed it by hand against the classes, and wrote up the new and changed files with their reasons myself.

## Session

**Prompt 1:**
I'm preparing the Stage 2 class diagram. I sketched how the new customer types attach as subclasses and where the arrival logic moved. Can you check that how I plan is correct?

**Reply 1:**
The subclass split is right. New customer types extend the base and override the behaviour methods that Stage 1 left behind, so the loop and the restaurant keep calling the base type and never branch on which customer arrived. Moving the arrival decision behind a schedule is what frees the controller from type checks. When you render the diagram, check it against the actual Stage 2 source: every inheritance arrow, association and multiplicity you draw must name a real class, field or call, otherwise you have invented a relationship the code does not have. The diagram proves the design, but only if it matches what is really there.
