# AI history: Zaahid Akbar Tinwala — Reflection, video timestamps and AI history (12–13 Nov 2026)

Same Claude conversation as the earlier files. Prompts and replies verbatim and in order; tool use shown as bracketed notes.

---

### Prompt 22 (13 Nov 2026, 16:00)

*[Attached: the group's final video `SC2002_1080p.mp4` and screenshots of Gowtham's messages asking Zaahid to do the README timestamps and the AI disclosure on GitHub.]*

> can u help me esp with hte timestamps

### Reply 22

I'll go through the video to find where each of the 7 required segments starts, then write the README timestamps section.

*[Claude checked the video length (15:01), extracted frames at regular intervals, ran scene-change detection to find speaker cuts, and zoomed in on the victory and defeat end screens and the gameplay demo.]*

I watched the whole video frame by frame and found where every speaker and segment starts. Here's the section to paste into the README, replacing `_To be filled in._` under **Video timestamps**:

```markdown
## Video timestamps

Video length: 15:01

### Required segments (brief Section 6.2)

| # | Segment | Timestamp | Presenter |
|---|---|---|---|
| 1 | The game launching from the submitted project (clone, compile, run) | 0:15 | Gowtham |
| 2 | Meaningful manager choices, including a decision where staff are constrained (Host seats C1 so the Waiter can take her order the same turn) | 2:02 | Zaahid |
| 3 | A dish going READY → SERVED → payment (Salad READY, served on turn 2, C1 pays $9.00 on turn 4) | 2:40 / 3:15 / 4:00 | Zaahid |
| 4 | A VIP behaviour (VIP Burger: 10% VIP discount) | 3:45 | Zaahid |
| 5 | A Stage 3 feature (Happy Hour switched on; VIP Burger costs $10.80; Host seating) | 3:30 | Zaahid |
| 6 | One victory and one defeat (reproducible runs from `tests/`) | 5:00 (victory) / 5:16 (defeat) | Zeon |
| 7 | Stage tag comparisons and the diagrams | 5:33 (stage comparisons) / 6:15 (GitHub compare) / 8:47 (class diagrams) / 10:19 (sequence diagram) | Austin, Yong Han |

### Full outline

| Time | Section | Presenter |
|---|---|---|
| 0:00 | Game overview, the three stages, build and run, code walkthrough | Gowtham |
| 2:02 | Gameplay demonstration | Zaahid |
| 4:35 | Victory and defeat runs | Zeon |
| 5:33 | Stage comparisons and class diagrams | Austin |
| 9:47 | Use case and sequence diagram | Yong Han |
| 12:37 | Individual contributions and key takeaways | All |
| 12:39 | Gowtham | |
| 13:06 | Yong Han | |
| 13:28 | Zeon | |
| 14:06 | Zaahid | |
| 14:28 | Austin | |
```

The speaker changes (2:02, 4:35, 5:33, 9:47, 12:37 onwards) come from exact scene cuts, so they're accurate to the second. The moments *inside* segments (like the Salad turning READY at 2:40) I read from frames a few seconds apart, so they could be off by up to ~5 seconds. Click through each one in the video and adjust any that land slightly early or late.

## How to add it to GitHub

You still need to accept your repo invite first, since without it you can't edit. Then:

1. Open the repo on GitHub and click **`README.md`**.
2. Click the **pencil icon** (top right of the file) to edit it in your browser. No terminal needed.
3. Scroll to the bottom, find `## Video timestamps` and `_To be filled in._`, and replace both with the block above.
4. Click **Commit changes…**, write a message like `Add video timestamps`, keep **"Commit directly to the main branch"** selected, and click **Commit changes**.

Since this edit goes on `main`, the `stage-3` tag stays pointing at the old commit, which is fine. The tags mark the *code* versions, and the README timestamps don't change any code.

After that, the other thing Gowtham asked you to do is the AI disclosure. That's your `ai-history` branch, and I can build those files whenever you're ready.

---

### Prompt 23 (13 Nov 2026, 16:46)

> doneok so how do i do the ai-history branch so ppl can add in

### Reply 23

The briefing wants each member's AI chats on **their own branch**, so the GitHub history shows each person uploaded their own. You can set up all five branches in about two minutes in the browser, then each person adds their own files. No terminal needed.

## Step 1: You create a branch for everyone

A **branch** is a separate copy of the repo where someone can add files without touching `main`, the copy the code lives on.

On the repo page:
1. Click the dropdown at the top left that says **`main`**.
2. Type `zaahid` in the box, then click **"Create branch zaahid from main"**.
3. Repeat for `gowtham`, `zeon`, `austin` and `yonghan`.

## Step 2: Each person adds their own chats (send this to the group)

Each member must do this **while logged into their own GitHub**, because the commit records who uploaded it. That's the evidence the graders look for, so don't upload for anyone else.

> **AI history: please do this by tomorrow**
>
> 1. Go to the repo and switch the branch dropdown (top left) from `main` to **your name**.
> 2. Click **Add file → Create new file**.
> 3. In the name box, type `ai-history/stage-1/chat-01.md`. Typing the `/` automatically creates the folders.
> 4. Paste the whole AI conversation into the big box, in order, with prompts and replies. The brief says a summary isn't enough. You can remove non-project personal messages, but mark where with `[non-project content removed]`.
> 5. Scroll down and click **Commit changes**. Check it says it's committing to **your branch**, not `main`.
> 6. Repeat with `stage-2/chat-01.md`, `stage-3/chat-01.md`, or `chat-02.md` for a second chat, to match whichever stage each chat was for.
>
> If you didn't use AI for a stage, create `ai-history/NO_AI_USED.md` saying which stages, so the branch isn't empty.

## Step 3: Add links to the README (on `main`)

The briefing says the README must link to every member's AI history. Edit `README.md` on **`main`** the same way you did the timestamps, and add this section:

```markdown
## AI usage history

Each member's AI conversations are on their own branch, under `ai-history/`.

| Member | AI history |
|---|---|
| Zaahid Akbar Tinwala | https://github.com/gowthamsidharthh/BACF2_Grp7/tree/zaahid/ai-history |
| Kannan Sinduja Gowtham Sidharth | https://github.com/gowthamsidharthh/BACF2_Grp7/tree/gowtham/ai-history |
| Zeon Teo | https://github.com/gowthamsidharthh/BACF2_Grp7/tree/zeon/ai-history |
| Yew Yiheng Austin | https://github.com/gowthamsidharthh/BACF2_Grp7/tree/austin/ai-history |
| Tan Yong Han | https://github.com/gowthamsidharthh/BACF2_Grp7/tree/yonghan/ai-history |
```

The links only work once each person has committed their first file. Before submitting, click all five to check none show "404".

## Your own files

Yours go on the `zaahid` branch. 

---

### Prompt 24 (13 Nov 2026, 23:34)

> turn our real chats into the ai-history file

### Reply 24

*[Claude built these `ai-history` markdown files from the conversation: prompts and replies copied verbatim and in order, tool use shown as bracketed notes, and the few non-project personal messages removed and marked.]*
