# AI usage - Austin - Stage 3

For Stage 3 I used an AI assistant to prepare the final class diagram, grouped by package, and a sequence diagram for one use case, then verified each against the actual Stage 3 source rather than against an earlier version.

## Session

**Prompt 1:**
The final class diagram needs to match Stage 3 exactly, and I want it grouped by responsibility so each part reads on its own, with one overview tying them together. Can you generate that and confirm I won't end up with invented relationships?

**Reply 1:**
Done, and it is grouped into domain, staff, control and support with an overview tying them together. I verified every edge against the Stage 3 source rather than an earlier sketch, so there are no invented relationships. The composition and aggregation on the restaurant are worth calling out: it composes the things it owns outright, its Tables and its Happy Hour policy, and merely aggregates the Customers and Orders which are created elsewhere and registered with it. The diagram states that difference with a filled versus an open diamond, and it maps to the fields the class really has.

**Prompt 2:**
I also drew a sequence diagram for a single use case, a customer going from arrival through to payment, including a Happy Hour discount. Does the flow capture the discounts in the order the brief gives?

**Reply 2:**
It does, and the order is the point. The diagram applies the customer's own discount first, then the Happy Hour discount, and locks the price in when the order is taken, which is exactly the sequence the brief specifies. The guards and the loop are in place too, the condition that only a customer who became ready to pay on an earlier turn can pay now, and the turn loop the kitchen sits inside. So the single use case reads top to bottom as arrival, order, cook, serve, eat, pay, and the decision points are all visible on the fragment.
