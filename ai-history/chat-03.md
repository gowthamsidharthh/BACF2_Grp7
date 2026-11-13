# AI history: Zaahid Akbar Tinwala — Recording the gameplay demo (11 Nov 2026)

Same Claude conversation as the earlier files. Prompts and replies verbatim and in order; tool use shown as bracketed notes. Long pastes of the game's own console output are shortened and marked.

---

### Prompt 11 (11 Nov 2026, 00:54)

*[Attached: screenshot of the group's video plan — Slot 2 "Gameplay demonstration", 4:00, Zaahid: interactive demo (2:30), covering segments 2–5; Zeon: victory + defeat (1:30).]*

> this is the split. Im zaahid. lets go and actually record the video now. walk me through step by step on what to do

### Reply 11

Your slot is 2:30 and covers segments 2–5. I'll plan an exact set of moves that shows all four in 5 turns, then test it on the real repo code so you know what to expect on screen.

*[Claude compiled the repo's Stage 3 code and ran the planned 5-turn sequence of inputs.]*

I tested the exact moves below on the repo's Stage 3 code. They hit all four of your segments in 5 turns, about 2:30 of talking. I've assumed Windows from your screenshots; Mac differences are at the end.

## Part A: Set up (about 10 minutes, one time)

**1. Check Java is installed.** Press the Windows key, type `cmd`, press Enter. In the black window, type:
```
java -version
```
This asks Java to print its version. If you see something like `openjdk version "21..."`, you're fine. If it says *"not recognized"*, install the free JDK 21 from **adoptium.net** (the `.msi` file). During install, turn on **"Add to PATH"**, which lets `cmd` find Java. Then close and reopen `cmd` and try again.

**2. Download the code.** On the GitHub repo page, click the green **Code** button → **Download ZIP**, then right-click the zip → **Extract All**.

**3. Open `cmd` inside the project folder.** In File Explorer, open the extracted folder until you can see `stage-1`, `stage-2`, `stage-3`. Click the address bar at the top, type `cmd`, and press Enter. That opens a command window already in the right folder, so you don't have to type any paths.

**4. Compile the game:**
```
javac -d out stage-3\src\*.java
```
`javac` turns the `.java` files into runnable `.class` files. `-d out` puts them in a folder called `out`, and `stage-3\src\*.java` means all the Stage 3 source files. If it prints nothing, it worked.

**5. Test-run it:**
```
java -cp out Main
```
This runs the `Main` class, with `-cp out` telling Java to look in `out` for it. You should see the welcome screen. Press **Ctrl+C** to quit.

**6. Make it readable for the video.** Maximise the window and make the text bigger with **Ctrl + mouse wheel** (or Ctrl + `=`). Type `cls` and press Enter to clear the screen before recording.

## Part B: What you'll type and say

Every number is followed by **Enter**. Each turn asks its questions in this order: Happy Hour → Host → Waiter → Chef.

**Turn 1 (~35s): segment 2, staff constraint, and segment 3, READY**

| Prompt | Type |
|---|---|
| Happy Hour? | `0` |
| Host | `1` then `1` |
| Waiter | `2` then `1` |
| Chef | `1` then `1` |

> "I'm the manager. Each turn I get exactly one task from the Host, the Waiter and the Chef. Customer C1 has arrived. I'll save Happy Hour for later. The Waiter can only do one thing per turn, so if the Waiter seated C1, nobody could take her order this turn. Instead the Host seats her, which frees the Waiter to take her order straight away. She chooses the Salad herself; the manager never picks food. The Chef cooks it, and Salad needs one unit, so it's now READY. But the Waiter already acted this turn, so it can't be served until next turn."

**Turn 2 (~20s): segment 3, SERVED**

| Prompt | Type |
|---|---|
| Happy Hour? | `0` |
| Host | `0` |
| Waiter | `3` then `1` |
| Chef | `0` |

> "Order 1 is READY, so the Waiter serves it, and it's now SERVED. C1 lost 8 satisfaction while waiting last turn, but once served she stops losing any."

**Turn 3 (~10s)**

| Prompt | Type |
|---|---|
| Happy Hour? | `0` |
| Host / Waiter / Chef | `0`, `0`, `0` |

> "C1 spends this turn eating, so everyone waits."

**Turn 4 (~45s): segment 3, payment; segment 4, VIP; segment 5, Happy Hour and Host**

| Prompt | Type |
|---|---|
| Happy Hour? | `1` |
| Host | `1` then `2` |
| Waiter | `2` then `2` |
| Chef | `1` then `2` |

> "A VIP customer has arrived, so now I switch on Happy Hour. It lasts this turn and the next, which the screen shows. The Host seats the VIP and the Waiter takes her order: a Burger, normally $15. As a VIP she gets 10% off, which makes $13.50, then Happy Hour takes 20% off that, so $10.80, and the price is locked in now. The Chef cooks it. And look: the Cashier automatically collected $9 from C1. So that's READY, SERVED, and now paid. Revenue is $9, and her satisfaction of 92 is recorded."

**Turn 5 (~20s)**

| Prompt | Type |
|---|---|
| Host | `0` |
| Waiter | `3` then `2` |
| Chef | `0` |

(No Happy Hour question this time, because it's already been used.)

> "The Burger is READY, so the Waiter serves it. Happy Hour also restored the VIP's satisfaction while she waited. That's my part. Zeon will now show a full victory and defeat run."

Then press **Ctrl+C** to stop.

## Part C: Record

1. **Rehearse once without recording.** Write the inputs on paper or a sticky note so you're not reading off a second screen.
2. Run `cls`, then `java -cp out Main`. You can let the welcome screen show (Zeon can trim it, since Gowtham covers the launch).
3. Press **Win + Shift + R** (Snipping Tool screen recording). Drag a box around the command window, turn on the **microphone** icon, and click **Start**.
4. Play turns 1–5 while talking, and press **Stop** when done.
5. Save it (Ctrl+S) as `Zaahid_Seg2-5.mp4`.

If Snipping Tool has no mic option, use **Win + Alt + R** instead (Xbox Game Bar, which records the active window; **Win + Alt + M** toggles the mic). The file lands in `Videos\Captures`.

## Part D: Send it

Send the file to Zeon with rough timestamps inside your clip: **Turn 1 ≈ 0:00** (staff constraint, READY), **Turn 2 ≈ 0:35** (SERVED), **Turn 4 ≈ 1:05** (VIP, Happy Hour, payment). That helps him fill in the README timestamps.

On a Mac, use **Terminal**, swap the backslashes for forward slashes (`stage-3/src/*.java`), and record with **Cmd + Shift + 5**.

Tell me if any step shows something different from what I described, and paste what you see.

---

### Prompt 12 (11 Nov 2026, 00:56)

```
C:\Users\zaahi>java -version
java version "26.0.2" 2026-07-21
Java(TM) SE Runtime Environment (build 26.0.2+10-55)
Java HotSpot(TM) 64-Bit Server VM (build 26.0.2+10-55, mixed mode, sharing)
```

### Reply 12

Java is installed. Version 26 is newer than the 17 the game needs, so you're good.

One quick extra check. `java` *runs* programs, but you also need `javac` to *compile* them, and some installs only include the first. Type:
```
javac -version
```
- If it prints something like `javac 26.0.2`, move on to step 2 below.
- If it says *"not recognized"*, tell me and we'll fix it before going further.

**Step 2, download the code:**
1. Open `https://github.com/gowthamsidharthh/BACF2_Grp7` in your browser.
2. Click the green **Code** button → **Download ZIP**.
3. In your Downloads folder, right-click `BACF2_Grp7-main.zip` → **Extract All** → **Extract**.
4. Open the extracted folder. You might need to double-click once more into an inner `BACF2_Grp7-main` folder. Stop when you can see `stage-1`, `stage-2`, `stage-3` and `README.md`.

**Step 3:** click the address bar at the top of that File Explorer window (where the folder path is), delete what's there, type `cmd`, and press Enter. A new command window opens, already in that folder.

In that new window, type `dir`. This lists the files in the current folder, so you can confirm you're in the right place. You should see `stage-1`, `stage-2` and `stage-3` in the list. Paste what you get.

---

### Prompt 13 (11 Nov 2026, 01:01)

*[Pasted output of `dir` in `C:\Users\zaahi\Downloads\BACF2_Grp7-main\BACF2_Grp7-main`, listing `.gitignore`, `README.md`, `stage-1`, `stage-2`, `stage-3`.]*

### Reply 13

You're in the right folder: `stage-1`, `stage-2` and `stage-3` are all there.

**Step 4, compile Stage 3.** Type:
```
javac -d out stage-3\src\*.java
```
- `javac` is the Java compiler. It turns the human-readable `.java` files into `.class` files the computer can run.
- `-d out` puts those `.class` files into a new folder called `out`, keeping them separate from the source code.
- `stage-3\src\*.java` means "every file ending in `.java` inside `stage-3\src`". The `*` is a wildcard meaning "anything".

If it works, **it prints nothing** and just shows the prompt again. That silence is success. If you see red text or *"'javac' is not recognized"*, paste it here.

**Step 5, test-run it:**
```
java -cp out Main
```
- `java` runs a compiled program.
- `-cp out` sets the "classpath", the folder where Java should look for the `.class` files.
- `Main` is the class with the `main` method, the program's starting point.

You should see the **WELCOME TO RESTAURANT RUSH** banner, the menu and the staff list, then *"Switch on Happy Hour now?"*. Don't play yet. Press **Ctrl+C** to quit, then paste me the first few lines you saw.


### Prompt 14 (11 Nov 2026, 01:08)

*[Pasted game output from launch through Turn 1: Host seats C1, C1 orders Salad ($9.00), Chef: Salad now READY, end of turn 1 "served 0"; waiting at the Turn 2 Happy Hour question.]*

### Reply 14

Turn 1 went exactly as planned: the Host seated C1, the Waiter took the Salad order, and the Chef made it READY. One note: when it asks *"Customer number to seat: C"*, the **C** is already printed for you, so you only type the number, which is what you did.

Notice the end-of-turn line says **served 0**. The Salad is READY but not served yet, because the Waiter acted *before* the Chef finished cooking. That's the rule you explain in your Turn 1 lines.

**Turn 2:** the game is asking about Happy Hour again. Type:

- `0` (no Happy Hour yet)
- `0` (Host waits, since nobody is in the queue)
- `3`, then `1` (Waiter serves order 1)
- `0` (Chef waits, since there's nothing to cook)

Things to check on screen at the **start** of Turn 2, before you type anything:
- **Table 1** shows `C1 ... sat=92 status=ORDERED`. She lost 8 satisfaction for waiting during Turn 1.
- **Orders** shows `Order 1: Salad ... [READY ...]`.

After the Waiter serves, you should see `Waiter serves Salad to C1`, and the end line should change to **served 1**.

Then do **Turn 3** (`0`, `0`, `0`, `0`): C1 is eating. At the start of Turn 3 her status should say **EATING**. Paste it here once you reach the Turn 4 Happy Hour question. Turn 4 is the busiest turn, so we'll go through it together.

---

### Prompt 15 (11 Nov 2026, 01:12)

*[Pasted game output through Turn 2: C1 sat=92 ORDERED, Order 1 READY, Waiter serves Salad to C1, end of turn 2 "served 1".]*

### Reply 15

Turn 2 matches exactly: `sat=92 status=ORDERED`, the order was READY, the Waiter served it, and the end line now says **served 1**. That's the READY → SERVED part of segment 3.

**Turn 3:** type `0`, `0`, `0`, `0` (no Happy Hour, and all three staff wait).

At the start of Turn 3, check that Table 1 says **`status=EATING`** and the order says **SERVED**. C1 stays at 92, because served customers stop losing satisfaction.

**Turn 4, the big one.** After Turn 3 ends, you'll see **`> C2 (VIP) arrives and joins the queue.`** and then the Happy Hour question. Type:

- `1` (switch Happy Hour ON)
- `1`, then `2` (Host seats C2)
- `2`, then `2` (Waiter takes C2's order)
- `1`, then `2` (Chef cooks order 2)

What you should see:
- **"Happy Hour: ACTIVE (2 turn(s) left, including this one)"** at the top of Turn 4
- **`C2 (VIP) orders Burger ($10.80)`**: $15 → VIP 10% off → $13.50 → Happy Hour 20% off → $10.80
- **`Cashier collects $9.00 from C1`**: the payment part of segment 3. You didn't press anything for this; the Cashier does it automatically.
- End line: **revenue $9.00, avg satisfaction 92.0**

Then do **Turn 5**. There's no Happy Hour question this time because it's already been used, so it goes straight to the Host: `0`, then `3` then `2`, then `0`. You should see `Waiter serves Burger to C2`.

Paste it once Turn 5 is done. If it all matches, the rehearsal is complete and you can record for real.

---

### Prompt 16 (11 Nov 2026, 01:14)

*[Attached text file of game output through Turn 3: C1 EATING, order SERVED, "C2 (VIP) arrives and joins the queue", waiting at the Turn 4 Happy Hour question.]*

### Reply 16

Turn 3 matches: C1 is **EATING**, the order is **SERVED**, she's holding at 92, and the VIP has just arrived. You're at the Turn 4 Happy Hour question.

**Turn 4.** Type these in order:

| Prompt | Type | What it does |
|---|---|---|
| Switch on Happy Hour now? | `1` | Turns Happy Hour on for this turn and the next |
| Choose Host task | `1` | Host will seat someone |
| Customer number to seat: C | `2` | Seats the VIP, C2 |
| Choose Waiter task | `2` | Waiter will take an order |
| Customer number to take order from: C | `2` | C2 orders |
| Choose Chef task | `1` | Chef will cook |
| Order number to cook | `2` | Cooks C2's Burger |

What to look for:
1. Right after you type `1`: **"Happy Hour is ON for this turn and the next!"**
2. At the top of Turn 4: **"Happy Hour: ACTIVE (2 turn(s) left, including this one)"**, and Table 1 shows C1 as **READY_TO_PAY**. She finished eating and is now waiting to pay.
3. **`C2 (VIP) orders Burger ($10.80)`**. That's your VIP and Happy Hour moment.
4. After the Chef cooks: **`Cashier collects $9.00 from C1`**. You never pressed anything for this; the Cashier pays out automatically. That completes READY → SERVED → paid.

**Turn 5** (no Happy Hour question this time):

| Prompt | Type |
|---|---|
| Choose Host task | `0` |
| Choose Waiter task | `3` |
| Order number to serve | `2` |
| Choose Chef task | `0` |

You should see `Waiter serves Burger to C2 (VIP)`. Then press **Ctrl+C** and paste Turns 4 and 5.

---

### Prompt 17 (11 Nov 2026, 01:15)

*[Attached text file of game output through Turn 5: Happy Hour ON, Host seats C2, C2 (VIP) orders Burger ($10.80), Chef: Burger READY, Cashier collects $9.00 from C1 (satisfaction 92), Turn 5 Waiter serves Burger to C2 (VIP).]*

### Reply 17

The rehearsal matched the plan exactly. Every segment showed up on screen:

- **Seg 2, staff constraint:** Turn 1, the Host seats so the Waiter can take the order the same turn
- **Seg 3, READY → SERVED → paid:** C1's Salad is READY in Turn 1, SERVED in Turn 2, and **paid $9.00** in Turn 4
- **Seg 4, VIP:** C2's Burger costs **$10.80** instead of $15
- **Seg 5, Stage 3 feature:** Happy Hour shows **ACTIVE (2 turns left)**, plus the Host seating

Press **Ctrl+C** to quit. Now record for real.

## Record

1. Type `cls` and press Enter (clears the screen).
2. Type `java -cp out Main` but **don't press Enter yet**.
3. Press **Win + Shift + R**, drag a box around the command window, switch the **microphone on**, and click **Start**.
4. After the 3-second countdown, press **Enter** to launch the game, then play.
5. When you see `Waiter serves Burger to C2 (VIP)` and the Turn 6 screen appears, click **Stop**.
6. Save it as `Zaahid_Seg2-5.mp4`.

## Cheat sheet (write this down)

| Turn | Type | Say (short version) |
|---|---|---|
| 1 | `0` · `1` `1` · `2` `1` · `1` `1` | "One task per staff per turn. Host seats C1 so the Waiter is free to take her order now. She picks Salad herself. Chef cooks it, so it's READY, but the Waiter already acted, so it's served next turn." |
| 2 | `0` · `0` · `3` `1` · `0` | "She lost 8 satisfaction waiting. Waiter serves it, so it's SERVED, and she stops losing satisfaction." |
| 3 | `0` · `0` · `0` · `0` | "She's eating this turn, so everyone waits." |
| 4 | `1` · `1` `2` · `2` `2` · `1` `2` | "A VIP arrived, so I switch on Happy Hour for 2 turns. Burger is $15, VIP 10% off makes $13.50, Happy Hour 20% off makes **$10.80**, locked in. And the Cashier automatically collected $9 from C1: READY, SERVED, paid." |
| 5 | `0` · `3` `2` · `0` | "Burger's ready, Waiter serves it. Happy Hour also topped the VIP back up to 100. Zeon will now show the full victory and defeat runs." |

## Tips

- **Talk first, then type.** Say the line while looking at the screen, then type the inputs. Pausing is fine, since Zeon can trim gaps.
- If you mistype, the game shows an error and asks again. Just type the right number and keep going; it even shows the error handling working.
- Aim for about 2:30. If it runs over, that's fine; Zeon can cut Turn 3 down.

Send the file to Zeon with these timestamps: **Turn 1 ≈ 0:00**, **Turn 2 ≈ 0:35**, **Turn 4 ≈ 1:05**. Adjust them after watching it back.

---

### Prompt 18 (11 Nov 2026, 01:15)

> give me the exact script to follow

### Reply 18

This runs about 2:30 at a normal speaking pace. **SAY** lines are spoken, **TYPE** lines are typed (press Enter after each number). Pause briefly after typing so the screen updates before you talk.

---

### Before recording
Type `cls` → Enter. Type `java -cp out Main` (don't press Enter). Start recording (**Win + Shift + R**, mic on, Start).

---

### Opening (≈10s)

**SAY:** "Hi, I'm Zaahid. I'll walk through the gameplay: the manager's choices, a dish going from ready to served to paid, a VIP customer, and the Stage 3 Happy Hour."

**TYPE:** press **Enter** (game launches)

---

### Turn 1 (≈40s)

**SAY:** "Customer C1 has just arrived. I'll save Happy Hour for later."

**TYPE:** `0`

**SAY:** "Each staff member gets exactly one task per turn. If the Waiter seated C1, nobody could take her order this turn. So I use the Host to seat her, which frees up the Waiter."

**TYPE:** `1` → `1`

**SAY:** "Now the Waiter takes her order. The customer chooses her own food. The manager never picks the dish."

**TYPE:** `2` → `1`

**SAY:** "She's ordered a Salad. The Chef cooks it. Salad takes one preparation unit, so it's now READY."

**TYPE:** `1` → `1`

**SAY:** "But notice it says served zero. The Waiter acts before the Chef, so a dish finished this turn can only be served next turn."

---

### Turn 2 (≈25s)

**TYPE:** `0`

**SAY:** "At the start of turn 2, C1's satisfaction has dropped from 100 to 92. Regular customers lose 8 points every turn they wait. Her Salad is READY, so the Host has nothing to do, and the Waiter serves it."

**TYPE:** `0` → `3` → `1`

**SAY:** "The order is now SERVED, and served customers stop losing satisfaction. Nothing to cook, so the Chef waits."

**TYPE:** `0`

---

### Turn 3 (≈10s)

**SAY:** "C1 spends this turn eating, so all the staff wait."

**TYPE:** `0` → `0` → `0` → `0`

---

### Turn 4 (≈50s)

**SAY:** "Now a VIP customer, C2, has arrived. This is a good moment for Happy Hour, so I'll switch it on."

**TYPE:** `1`

**SAY:** "Happy Hour is active for this turn and the next. You can see the two turns remaining at the top. C1 has finished eating and is ready to pay. The Host seats the VIP."

**TYPE:** `1` → `2`

**SAY:** "The Waiter takes her order."

**TYPE:** `2` → `2`

**SAY:** "She orders a Burger, normally 15 dollars. As a VIP she gets 10 percent off, which makes 13.50. Then Happy Hour takes another 20 percent off, so she pays 10.80. That price is locked in when the order is taken. The Chef cooks it."

**TYPE:** `1` → `2`

**SAY:** "And here, the Cashier automatically collected 9 dollars from C1. I didn't press anything. That completes her journey: READY, SERVED, and paid. Revenue is now 9 dollars, and her satisfaction of 92 is recorded."

---

### Turn 5 (≈20s)

**SAY:** "Happy Hour is on its last turn. The VIP's Burger is READY, so the Waiter serves it."

**TYPE:** `0` → `3` → `2` → `0`

**SAY:** "And while she waited, Happy Hour restored her satisfaction back to 100. That's the gameplay. Zeon will now show a full victory and defeat run."

**Stop recording.**

---

If you stumble on a line, keep going. Zeon can cut small gaps, and re-recording a whole 2:30 at 1am isn't worth it for one fumbled sentence.

---

### Prompt 19 (11 Nov 2026, 01:29)

> wait are u sure a GUI will not help in this?

### Reply 19

Yes, I'm sure. A GUI wouldn't help you here, and it would add risk.

**The brief rules it out as a source of marks.** Section 1 of the brief says *"A GUI is optional and earns no extra marks by itself."* Here's where the 100 marks come from:

| Criterion | Marks | Would a GUI help? |
|---|---|---|
| Class diagram | 25 | No, and it would make it bigger and harder to match |
| Sequence diagram | 20 | No |
| Design considerations | 15 | No |
| Code | 20 | No, it marks the game rules and code quality |
| Process and video | 20 | No |

**It would cost you a lot with 4 days left:**
- **The diagrams would break.** Right now the class diagram matches the code exactly. A GUI adds new classes, and the diagrams, report and sequence diagram would all need redoing to match.
- **The stage tags would need redoing.** Each stage has to be separately tagged and runnable. Changing the interface means changing all three stages or explaining why only Stage 3 differs.
- **New bugs.** The current game is tested and every rule checks out. A rushed GUI is the most likely thing to break that.
- **Nobody in your group could easily explain it on video.** The current UI is one small class, `ConsoleUI`.

The only gain would be a nicer-looking video, and the brief says that's not scored either. It asks for *"readable screen capture and audible explanation"*, which a big-font command window already gives you.

So record with what you have. If you want the video to look cleaner, use a bigger font and maximise the window.

---

### Prompt 20 (11 Nov 2026, 01:36)

*[Pasted game output through Turn 4, where the Waiter task was entered as `1` (seat) for C2 after the Host had already seated C2, producing "! Not allowed: That customer is not in the waiting queue. Try again."]*

> the scropt u gave me is not follwing this no

### Reply 20

The script is right. You typed `1` for the Waiter in Turn 4 instead of `2`. In Turn 4 the **Host** gets `1` then `2`, and the **Waiter** gets `2` then `2`. Since the Host had already seated C2, asking the Waiter to seat her again was rejected.

Nothing is broken, and the game is waiting for you to choose again. Type:

- `2` (Waiter: take order)
- `2` (customer C2)

Then carry on with the script from *"She orders a Burger..."*

**If you're recording right now, keep going and turn the mistake into a feature.** The brief actually wants this shown. It says an invalid choice must explain why it failed and let the manager choose again. Before typing `2`, say:

> "If I try to seat C2 again, the game refuses, because the Host already seated her. It explains why and lets me choose again without using up the Waiter's task. So instead, the Waiter takes her order."

That's a bonus point for segment 2, not a mistake to cut.

To make Turn 4 easy to read off, here it is again with labels:

| Prompt on screen | Type |
|---|---|
| Switch on Happy Hour now? | `1` |
| Choose **Host** task | `1` |
| Customer number to seat: C | `2` |
| Choose **Waiter** task | `2` ← not 1 |
| Customer number to take order from: C | `2` |
| Choose **Chef** task | `1` |
| Order number to cook | `2` |

---

### Prompt 21 (11 Nov 2026, 01:37)

> give me proper script

### Reply 21

Here's the script as one row per prompt. Wait for the **exact words** in the left column to appear, type what's in the middle, and then say the right column. Press Enter after every number.

**Before recording:** type `cls` and press Enter. Then type `java -cp out Main` *without* pressing Enter, and start recording (Win + Shift + R, mic on, Start).

**SAY first:** "Hi, I'm Zaahid. I'll walk through the gameplay: the manager's choices, a dish going from ready to served to paid, a VIP customer, and the Stage 3 Happy Hour."

Then press **Enter** to launch the game.

---

### TURN 1

| When the screen shows | Type | Then say |
|---|---|---|
| `Switch on Happy Hour now?` | `0` | "Customer C1 has arrived. I'll save Happy Hour for later." |
| `Choose Host task:` | `1` | "Each staff member gets one task per turn. If the Waiter seated C1, nobody could take her order this turn, so the Host seats her instead." |
| `Customer number to seat: C` | `1` | — |
| `Choose Waiter task:` | `2` | "That frees the Waiter to take her order right away." |
| `Customer number to take order from: C` | `1` | "She chooses her own food: a Salad. The manager never picks the dish." |
| `Choose Chef task:` | `1` | — |
| `Order number to cook:` | `1` | "Salad takes one unit, so it's READY. But it says served zero, because the Waiter acts before the Chef. It can only be served next turn." |

### TURN 2

| When the screen shows | Type | Then say |
|---|---|---|
| `Switch on Happy Hour now?` | `0` | "C1's satisfaction dropped from 100 to 92. Regular customers lose 8 every turn they wait." |
| `Choose Host task:` | `0` | — |
| `Choose Waiter task:` | `3` | "Her Salad is READY, so the Waiter serves it." |
| `Order number to serve:` | `1` | "It's now SERVED, and she stops losing satisfaction." |
| `Choose Chef task:` | `0` | — |

### TURN 3

| When the screen shows | Type | Then say |
|---|---|---|
| `Switch on Happy Hour now?` | `0` | "C1 is eating this turn, so all staff wait." |
| `Choose Host task:` | `0` | — |
| `Choose Waiter task:` | `0` | — |
| `Choose Chef task:` | `0` | — |

### TURN 4

| When the screen shows | Type | Then say |
|---|---|---|
| `C2 (VIP) arrives` … `Switch on Happy Hour now?` | `1` | "A VIP has arrived, so I'll switch on Happy Hour. It lasts this turn and the next; you can see two turns left at the top. C1 has finished eating and is ready to pay." |
| `Choose Host task:` | `1` | "The Host seats the VIP." |
| `Customer number to seat: C` | `2` | — |
| `Choose Waiter task:` | **`2`** | "The Waiter takes her order." |
| `Customer number to take order from: C` | `2` | "She orders a Burger, normally 15 dollars. VIPs get 10 percent off, making 13.50, then Happy Hour takes 20 percent off that, so she pays 10.80. The price is locked in now." |
| `Choose Chef task:` | `1` | — |
| `Order number to cook:` | `2` | "And the Cashier automatically collected 9 dollars from C1. I didn't press anything. So C1 went READY, SERVED, and paid. Revenue is 9 dollars and her satisfaction of 92 is recorded." |

### TURN 5 (no Happy Hour question this turn)

| When the screen shows | Type | Then say |
|---|---|---|
| `Choose Host task:` | `0` | "Happy Hour is on its last turn." |
| `Choose Waiter task:` | `3` | "The VIP's Burger is READY, so the Waiter serves it." |
| `Order number to serve:` | `2` | — |
| `Choose Chef task:` | `0` | "Happy Hour also restored her satisfaction to 100 while she waited. That's the gameplay. Zeon will now show the full victory and defeat runs." |

**Stop recording.**

---

If you see `! Not allowed`, you typed the wrong number. Look at the left column again, type the right one, and keep going. You can even say: *"The game rejects invalid choices and lets me pick again without wasting the task."*
