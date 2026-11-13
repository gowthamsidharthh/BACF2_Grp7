# AI history: Zaahid Akbar Tinwala — Review of the group submission (10 Nov 2026)

Same Claude conversation as the earlier file. Prompts and replies verbatim and in order; tool use shown as bracketed notes.

---

### Prompt 5 (10 Nov 2026, 14:27)

*[No text. Attached: the group's review package `For_Review_Grp_7.zip` (report, diagrams, PlantUML sources, READMEs), the earlier code zips, screenshots of the assignment brief, the work split, the Group 7 member list and the GitHub access page, and an export of the group's chat.]*

`[non-project content removed: the group chat export itself is not reproduced here]`

### Reply 5

I'll go through your group's review package and check it against the brief.

*[Claude unpacked the review zip, read the report and READMEs, cloned the public GitHub repo, checked the tags, commit authors and dates, built and ran every tag, cross-checked the class and sequence diagrams against the Stage 3 code, and read the group chat export.]*

`[non-project content removed]`

Here's the review of the group's package. I checked it against the brief, the briefing slides and the actual GitHub repo, not just the files in the zip.

## What's solid

- **All three tags build and run from a fresh copy.** Every result in the report's evidence table (VICTORY $66.00 / $73.50 / $62.10, the DEFEAT runs) matches what the code really produces.
- **The class diagram matches the Stage 3 code exactly.** Every class, method and field in the diagram exists in the code, and nothing is missing. That's worth 25 marks, and it's clean.
- **The sequence diagram is strong.** Every call in it is a real method. It covers the whole flow the brief asks for (arrival → Happy Hour → order → READY → SERVED → eating → payment → result) with `opt`, `loop` and `alt`.
- **The design discussion is good.** It's honest about ISP not applying, it argues Host-extends-Staff for LSP, and it includes a real alternative design (`PricingPolicy`).

## Missing items (required)

1. **No AI history branches.** The briefing needs each member's chats on their own branch, e.g. `ai-history/stage-1/chat-01.md`. The repo has only `main`.
2. **No individual reflections.** Section 6 is written as "we". The briefing needs a separate section per member under their own name covering what you learned, how you applied it, what challenged you and how you solved it.
3. **Only one class diagram.** The briefing slides ask for three (one per stage). The assignment doc says final only, so this is the same conflict I flagged at the start, and doing all three is safer. Stage 1 and 2 diagrams are just the Stage 3 one with classes removed.
4. **Video timestamps** are still "to be filled in".
5. **Declaration forms** are still a placeholder.

## Smaller fixes

- **Report section 5:** the "representative tail" isn't real output (the game never prints a "Turn 18" line there). Paste the real final screen instead.
- **Report section 4:** it points to `_review/design_discussion.md`, which doesn't exist. Delete that sentence.
- **Report 4.3:** the paragraph headed "Composition for customer behaviour" is actually about inheritance. Rename the heading.
- **Sequence diagram:** the `askHostAction()`, `askWaiterAction()` and `askChefAction()` arrows point from UI to controller, but in the code the controller calls the UI. Flip them, with the choice coming back as a return arrow. The payment `alt` also has only one branch, so it should be `opt`.

For your own part: accept the GitHub invite, upload this chat as your AI history, write your reflection, and do the 2-minute gameplay demo. 

---

### Prompt 6 (10 Nov 2026, 14:34)

> wait what else is left in the github and whats my role in the thing

### Reply 6

## Your role

From Gowtham's split in the group chat (29 Oct):

- **Code.** Done. The code you sent on 2 Nov is what's in the repo.
- **Video: gameplay demo, 2 minutes.** You play the game on screen and explain the choices. It covers the brief's video segments 2 and 3: a decision where staff are limited, and a dish going READY → SERVED → paid.
- **Everyone, including you:** your own reflection section in the report, your AI chat history on GitHub, your individual-contributions bit in the video, the GAI declaration form, and peer evaluation.

## What's left on GitHub

The repo has only the three stage folders and the README. Missing:

| Item | Who |
|---|---|
| Accept your invite (still pending for ZaahidT14) | **You** |
| Each member's AI history on their own branch | **Everyone**, you included |
| README links to every member's AI history | Gowtham (GitHub lead) |
| Diagrams and report added to the repo (the brief says "PDF report **and repository**") | Zeon / Yong Han |
| Video timestamps in the README | Zeon, after editing |
| Decision on the backdated commits | Whole group (I'd raise it in the chat) |


---

### Prompt 7 (10 Nov 2026, 14:44)

> https://github.com/gowthamsidharthh/BACF2_Grp7

### Reply 7

I'll pull the latest version of the repo and check what's changed since I looked an hour ago.

*[Claude fetched the repo again and listed its branches, commits and files.]*

Nothing has changed since I checked earlier. The repo is the same as before:

- **One branch** (`main`), with the same 14 commits and the backdated history.
- **Files:** only `stage-1`, `stage-2`, `stage-3` and the README.
- **No AI history from anyone**: no member branches, no `ai-history` folders.
- **No diagrams or report** in the repo yet.
- **Video timestamps** are still blank in the README.

The code side is finished: all three tags build and win/lose correctly. Everything left is the AI histories, diagrams, report and README updates from the earlier list.

Your first step is still accepting the invite. If it worked, you'd see a **Settings**-free repo page with no "pending invitation" banner, and `git push` would succeed. Once you've accepted, I can turn this chat into the three `ai-history` markdown files so you can push your branch in one go.

---

### Prompt 8 (10 Nov 2026, 14:45)

> look at hte new code

### Reply 8

I'll re-download the repo fresh, in case my earlier fetch missed something.

*[Claude made a fresh clone and listed every remote branch and tag.]*

Nothing new has been pushed. GitHub's latest commit is still the 4 Nov "Stage 3: runs and readme", and there's no other branch. If you meant the group's version of the code, I'll compare it file by file:

There's no new commit on GitHub. 

## What the group changed in Stage 3

**1. Setters replaced with "behaviour" methods (encapsulation).**

Before, other classes could set a customer's fields directly:
```java
customer.setStatus(CustomerStatus.EATING);
customer.setServedTurn(turn);
```
Now they have to ask the customer to do a named action:
```java
customer.startEating(turn);
```
All the `setXxx()` methods in `Customer` and `Order` were deleted and replaced with methods like `seatAt()`, `placeOrder()`, `startEating()`, `becomeReadyToPay()`, `markPaid()`, `markLeft()`, `markServed()` and `cancel()`.

Why this is better: with a setter, any class could put a customer into any state, even a nonsense one like PAID before they've ordered. With named actions, `Customer` controls its own state changes, and the code reads like the game rules. This is encapsulation from Chapter 2, and it helps the class diagram's "attributes, operations and encapsulation" marks.

**2. `findCustomer()` and `findOrder()` now throw an error instead of returning `null`.** If the manager types a customer number that doesn't exist, the message is now "There is no customer with number C9" instead of the vaguer "not in the waiting queue". The controller already catches `InvalidActionException`, so the game just asks again.

## Is it correct?

Yes. The change didn't break any rules.

There's one tiny slip. In Stage 3's `Staff.java`, the comment now says "only a Waiter, Chef or Cashier" and leaves out the Host. It's a one-word fix, and only a comment, but a grader reading Stage 3 might notice.

## What it means for you

- **For your gameplay demo,** nothing changes. The game plays exactly the same.
- **If the group draws Stage 1 and 2 class diagrams,** those must still show the setters, because the setters only disappeared in Stage 3. Each diagram has to match its own stage's code.
- **If a grader asks why this changed in Stage 3,** the README already explains it under "Code quality pass".

---


### Prompt 9 (10 Nov 2026, 14:48)

> whats the breakdown of the video agian?

### Reply 9

Here's how the video fits together, from the brief, the briefing slides and Gowtham's split.

## What the video must show (the brief's 7 segments)

All of these need timestamps in the README:

1. The game launching from the submitted project
2. Meaningful manager choices, including a decision where staff are constrained
3. A dish going READY → SERVED → payment
4. A VIP or Critic behaviour
5. A Stage 3 feature (Host, Combo or Happy Hour)
6. One victory and one defeat
7. The stage tag comparisons and the diagrams

## Who does what (your group's split)

| Person | Part | Time |
|---|---|---|
| Gowtham | Game overview and the three stages | 2 min |
| **Zaahid** | **Gameplay demo** | **2 min** |
| Zeon | Key outcomes (also edits the video) | 2 min |
| Austin | Class diagrams and stage changes | 4 min |
| Yong Han | Sequence diagram | 3 min |
| Everyone | Your own contribution and what you learned | ~2 min total |

That's about 15 minutes, matching the briefing's suggested timing.

## How the 7 segments map onto people

The split doesn't say who covers segments 4–6, so it's worth agreeing on this in the group chat. My suggestion:

- **You (2 min):** segments **1, 2, 3 and 4**. Launch the game, play a few turns where the Waiter can't do everything at once, show a dish go READY → SERVED → paid, and point out the VIP getting a discount or the Critic losing 12 per turn.
- **Zeon (2 min):** segments **5 and 6**. Show Happy Hour (the $10.80 VIP Burger) or the Host, then the end screens of the victory and defeat runs. He can feed in the test files so it's quick.
- **Gowtham:** the start of segment **7** (the stage tags on GitHub).
- **Austin and Yong Han:** the diagrams part of segment **7**.

Your 2 minutes is the most hands-on part, so it's worth practising the exact moves beforehand. We can plan that once the game runs on your laptop.

---

### Prompt 10 (10 Nov 2026, 14:51)

> firstly are there any code commit changes we need to do?

### Reply 10

I went back through the brief's rules (turn display, the six steps, eating and payment timing, abandonment, invalid choices, VIP/Critic, Host, Combo, Happy Hour) against the repo code. **No code changes are needed.** Everything required is there and works, and all three stages win and lose correctly.

The only code-level thing is optional:

- **`stage-3/src/Staff.java` comment:** it says "only a Waiter, Chef or Cashier" but should be "a Host, Waiter, Chef or Cashier". It's a comment, so the game doesn't care, but a grader reading Stage 3 might. If you fix it, change it **only in stage-3** (Stages 1 and 2 have no Host, so they're correct as they are). Don't move the `stage-3` tag for this unless the group agrees.

The commits still needed are all non-code:

| Commit | Where | Who |
|---|---|---|
| Each member's AI history | Their own branch | Everyone |
| Links to every member's AI history | Root README | Gowtham |
| Diagrams (and report PDF) | Repo, e.g. a `docs/` folder | Zeon / Yong Han |
| Video timestamps | Root README | Zeon, after editing |
| Note that each stage is a full folder copy, so GitHub's compare links show every file as new; the change tables in the README are the real comparison | Root README | Gowtham |
| How the git history was made (if the group decides to disclose it) | README and report | Group decision |

So for code, you're done. Your next real task is getting the game running on your laptop for the demo. Windows or Mac?
