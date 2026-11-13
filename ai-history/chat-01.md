# AI history: Zaahid Akbar Tinwala — Stage 1

- **Tool:** Claude (Anthropic), one ongoing conversation inside a Claude project ("OOP Java") that contained the course lecture slides, tutorial questions, assignment brief and briefing slides
- **Date of this part:** 28 October 2026
- **What it covers:** understanding the brief, and the first work-split discussion
- **Format:** prompts and replies are copied verbatim and in order. Where Claude used tools (reading files, writing and running code), this is shown as a bracketed note. 

---

### Prompt 1 (28 Oct 2026, 15:35)

> ok need to get started on my lab project. can you read the context files and summarise what i need to do

### Reply 1

*[Claude read the project files: the assignment brief `SC2002_Restaurant_Rush_Group_Assignment.docx` and `SC2002_Group_Project_Briefing.pdf`.]*

Here's what the lab project (Restaurant Rush, SC2002) asks for. It's due **Sunday 15 Nov 2026, 11:59pm**, so you have about 2.5 weeks.

## The big picture

Your group builds a **turn-based restaurant game in Java** that runs in the terminal (no GUI needed). The player is the restaurant manager: each turn they decide what the Waiter and Chef do, and customers get less happy the longer they wait. You build it in **three stages**, and each stage has to be saved as its own working version on GitHub (tagged `stage-1`, `stage-2`, `stage-3`).

The key idea the course is testing: **does your OOP design make it easy to add new features later?** Code is only 20% of the marks. Diagrams and design explanations are most of the grade.

## The game rules (base setting)

- **18 turns**, **2 tables**, one Waiter, one Chef, one Cashier
- One customer arrives on turns 1, 4, 7, 10, 13, 16 (six total)
- Menu: Salad $9 (1 prep unit), Burger $15 (1 unit), Pasta $18 (2 units). Customers pick their own food using a rule you define.
- **To win** after turn 18: at least 4 paid customers, at least $45 revenue, and average satisfaction of paid customers at least 60

**Each turn, in order:**
1. A scheduled customer arrives (satisfaction 100)
2. The manager chooses one Waiter task (seat a customer / take an order / serve a ready dish / wait) and one Chef task (cook one unit of an order / wait)
3. Waiter acts before the Chef, so a dish finished this turn can only be served next turn
4. Served customers eat for one turn, then become ready to pay; the Cashier collects one payment per turn automatically
5. Anyone waiting and not yet served loses satisfaction (8 points for regulars). At 0 they leave without paying.
6. After turn 18, show Victory or Defeat

Invalid choices must show an error and let the manager try again. Game rules must live in your classes, not in the menu/printing code.

## The three stages

| Stage | What you add |
|---|---|
| **1** | The full basic game: regular customers, menu, Waiter, Chef, Cashier, tables, queue, win/lose |
| **2** | **VIP** customers (lose 5/turn, 10% off bill) and **Critic** customers (lose 12/turn, plus a special behaviour you invent). These must work through **polymorphism** — each customer object handles its own behaviour, not a long chain of `if (type == VIP)` checks. |
| **3** | A **Host** who can also seat one customer per turn; **ComboMeals** (two items, 90% of combined price); **Happy Hour** (20% off for two turns, waiting customers recover 15 satisfaction) |

Stage 2 is basically your Chapter 3 and 5 material (inheritance and polymorphism) put to work, which is also what your exam focuses on.

## What you submit (one zip per group)

- **Java source code** for all 3 stages, with run instructions
- **README** with the GitHub link, what changed between stages and why, and video timestamps
- **PDF report** with diagrams, design discussion (which SOLID principles you genuinely used, trade-offs, one alternative design), and **your own individual reflection section**
- **~15 min recorded video** where each member explains part of the work
- **Declarations** (original work + GAI use forms)

## Marks

| Part | Marks |
|---|---|
| UML class diagram | 25 |
| UML sequence diagram | 20 |
| Design considerations | 15 |
| Code | 20 |
| Process (stage tags, change analysis, reflection, video) | 20 |

## Two things to watch out for

**AI chat history is required.** You're allowed to use AI, but every member must upload their full project-related AI conversations (in order, not summaries) to their own branch, e.g. `ai-history/stage-1/chat-01.md`. That includes this chat, so keep these sessions saved.

**The two documents disagree on diagrams.** The assignment brief asks for one final class diagram matching Stage 3, but the briefing slides say **three class diagrams (one per stage)**. Doing all three is the safe choice; worth confirming with your TA.

Since you're starting from zero, the sensible path is: first get Java running on your laptop and learn the basics of Git, then build Stage 1 step by step with full explanations, then extend. Before I plan that out, what part of the project has your group given you? (For example, coding a specific stage, the diagrams, or the report.) That decides where we start.

---

### Prompt 2 (28 Oct 2026, 15:41)

> ok im running out of time, my grp has 5 people rn. i need your help in splitting the work. 

### Reply 2
## Splitting the work across 5 people

Code is only 20 of 100 marks. The diagrams (45) and design/process write-ups (35) matter more, so the split puts people there.

| Person | Main job | Marks it targets |
|---|---|---|
| **1. GitHub lead** | Create the repo, push code, tag it `stage-1`, set up everyone's `ai-history/` branch. Later writes the README stage comparisons (1→2, 2→3). | Process (20) |
| **2. Stage 2 coder** | `VIPCustomer` and `CriticCustomer` extending `Customer`, plus the Critic's special behaviour. Tag `stage-2`. | Code (20) |
| **3. Stage 3 coder** | `Host` (extends `Staff`), `ComboMeal`, Happy Hour. Tag `stage-3`. | Code (20) |
| **4. UML lead** | Three class diagrams (one per stage) that exactly match the code. | Class diagram (25) |
| **5. Sequence + design lead** | The sequence diagram (VIP or Happy Hour scenario) and the design discussion: SOLID principles, trade-offs, one alternative design. | Sequence (20) + Design (15) |

**Everyone** writes their own reflection section, explains their part in the 15-minute video, and uploads their full AI chat history (this chat counts for you).

**Timeline to 15 Nov:**
- **By 1 Nov:** Stage 1 coded
- **By 6 Nov:** Stages 2 and 3 coded
- **By 10 Nov:** diagrams final
- **By 13 Nov:** report and video done, leaving 2 days of buffer

Which role are you taking? If you're the GitHub lead or a coder, the next session is getting Java and Git working on your laptop and walking through these files one by one.

---

### Prompt 3 (28 Oct 2026, 15:54)

> i dont understand the split 
3. **Tell your group who does what.** Here's the split in plain words:
   - **2 people write code:** one adds Stage 1 & 2, one adds the Host, combo meals and Happy Hour (Stage 3)
   - **2 people draw diagrams:** one draws the class diagrams (boxes showing each class and how they connect), one draws the sequence diagram (arrows showing who calls whom during one turn) and writes the "why we designed it this way" section
   - **1 person runs GitHub** and writes the README

   Everyone writes a short reflection about their own part and talks in the video. You can just paste that list into your group chat.


