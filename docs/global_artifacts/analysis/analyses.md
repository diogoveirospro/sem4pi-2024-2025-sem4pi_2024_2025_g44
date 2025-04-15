
### Story Board

**Management story**

1 - The admin creates a user.

2 - The Drone Tech creates a drone model.

3 - The Drone Tech configs the drone language.

4 - The Drone Tech adds drones to inventory.

5 - The Drone Tech lists the drones in inventory.

6 - The Drone Tech can remove the drone from inventory.

7 - The Drone Tech sends the drone to maintenance if necessary.

8 - The Drone Tech receives the drone from maintenance.

9 - The Drone Tech can list the drones maintenance history.

10 - Client contacts CRM Collaborator to submit a request for a show.

11 - If the client doesn't exist, CRM Manager creates a client in the app.

12 - CRM Collaborator creates a show request into the system.

13.1 - If it's a new figure, the CRM Manager assigns the request to a Show Designer.

13.2 - If it's an existing figure. Jump to step 9.

14 - The Show Designer designs the new figure and adds it to the system.

15 - The Drone Tech generates the figure code.

16 - The figure is tested.

17 - The Drone Tech generates the show code.

18 - The show is tested.

19 - The CRM Collaborator generates the show proposal.

20 - The proposal document is validated by the system. If it's not valid, the CRM Collaborator updates the proposal and
it gets reevaluated by the system.

21 - The proposal is sent to the client.

22 - The client logins in the system and accepts/rejects the proposal.

23 - The CRM Collaborator updates the show status to "Accepted"/"Denied".

24.1 - If the show proposal is denied, the client discusses the proposal with CRM Manager.

24.2 - If the show proposal is accepted, the CRM Team schedules the show. There may be some negotiation with the client.

25 - The show is scheduled in the system with date and time.

26 - The client can consult the show schedule in the system.

### Questions to Clarify Requirements

> 1. When the client reject's the proposal, does the status of the proposal change to "denied"?

> 2. Who schedules the show, the CRM Collaborator or the CRM Manager, because in the assignment says CRM Team but in the use case says CRM Collaborator?

> 3. Does the company already has some drones and creates more as needed?

> 4. What happens if the client refuses the offer after discussing with the CRM Manager?

> 5. In the document there is a use case, Customer, but there is also a reference to the client, are they the same thing?

> 6. What do the different customer statuses mean and how are they assigned? We know that there are different statuses, but it's not clear what each one means or the criteria used to assign them.

> 7. Can a customer with "Created" status submit a Show Request, or do only customers with "Regular" or "VIP" status have this permission? If not, what are the steps required for a "Created" customer to become "Regular" or "VIP"?

> 8. In the third paragraph of section 3.1.4, it states that "the CRM Manager assigns each request to a Show Designer", but isn't the CRM Collaborator responsible for this assignment? It's not clear which of the two has this responsibility and what the specific role of the CRM Manager is in this process.

> 9. How do you want the simulation to be demonstrated?