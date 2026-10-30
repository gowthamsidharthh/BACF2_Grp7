# AI usage - Zeon - Stage 1

For Stage 1, the base game, I used an AI assistant as a sounding board. I decided what the stage had to contain, sketched the design before writing code, and treated the assistant as a reviewer and a scribe. Every suggested pattern I re-read against the briefing and ran myself before passing it on.

## Session

**Prompt 1:**
I'm about to start coding Stage 1 and I am sorting the structure right now. The turn is described as six steps and I come up with a rough class breakdown myself. Can you sanity-check where the responsibilities sit, so the loop stays thin and the rules don't scatter everywhere? I also want the design easy to change later if needed.

**Reply 1:**
Your breakdown is on the right track. Keep the controller as a thin orchestrator: it should only decide the order of the six steps and the victory check, and nothing else. Let the Waiter, Chef and Cashier own their own rule checks, each one task a turn, and keep the ConsoleUI free of rules entirely, which the brief asks for explicitly. The UI reads a choice, shows the state, and repeats; it should never have to know whether an action is legal. For the open-later part, resist the urge to pile logic into the loop. Put the behaviour that might vary behind small methods on the base classes, so a change later extends the design instead of editing it. And do not reach for interfaces yet; they add a level of indirection that gives you nothing this stage.

**Prompt 2:**
If changes later have to arrive without me knowing them now, is a subclass overriding a method enough? I only need to extend, I won't be swapping behaviour at runtime.

**Reply 2:**
Yes, a subclass override is enough, on one condition: the varying behaviour already leaves through a method on the base class. In your case satisfaction loss, the bill and the food choice all go through the customer, so a new variant simply overrides those and the rest of the program never notices it was swapped in. That is the whole point of programming to the base type. I would not add interfaces here, because you said yourself you will not swap behaviour at runtime, and interfaces only earn their keep when unrelated classes need a shared contract. Keep it plain, keep the base stable, and let subclasses carry the difference.

**Prompt 3:**
The brief wants an invalid choice to explain why and not spend the staff turn. I don't want to put checks through the controller. What's a tidy pattern?

**Reply 3:**
A tidy and common pattern is a custom exception plus a retry loop in the controller. Give the exception the human reason, so it reads like "No free table" or "Only a READY order can be served", and throw it from the staff or domain method as soon as the rule fails. Back in the controller, wrap the choice in a while loop that catches the exception, prints the reason, and asks again. The key detail is that you throw before any state changes, so an invalid attempt can never consume the staff turn. It keeps the rules near the data, gives you exactly one place per message, and it mirrors the exception approach from our Tutorial 4, so the pattern should feel familiar.

**Prompt 4:**
Last one. I want a grader to replay the win and the loss. Can I run the whole game from a saved input file, and is that a fair way to show both endings?

**Reply 4:**
Yes, and it is a fair and good way to show both endings, with one caveat. Build the game normally, then feed a text file in as the input using a redirect from the shell, so the simulation reads its choices from the file exactly as if you had typed them. Because the game is deterministic, the same file lands on the same final output every run, no luck involved. Make two saved scripts, a victory one and a defeat one, and they become your reproducible evidence: a grader can run the exact command and get the exact result you recorded. That is stronger than a screenshot and it is precisely the kind of proof the assignment asks for.
