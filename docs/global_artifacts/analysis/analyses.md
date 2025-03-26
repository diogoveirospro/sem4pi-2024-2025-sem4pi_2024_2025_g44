
### Story Board

**Show management story**

1 - The admin creates a user.

2 - Client contacts CRM Collaborator to submit a request for a show.

3 - If the client doesn't exist, CRM Manager creates a client in the app.

4 - CRM Collaborator creates a show request into the system.

5.1 - If it's a new figure, the CRM Manager assigns the request to a Show Designer.

5.2 - If it's an existing figure. Jump to step 9.

6 - The Show Designer designs the new figure and adds it to the system.

7 - The Drone Tech generates the figure code.

8 - The figure is tested.

9 - The Drone Tech generates the show code.

10 - The show is tested.

11 - The CRM Collaborator generates the show proposal.

12 - The proposal document is validated by the system. If it's not valid, the CRM Collaborator updates the proposal and
it gets reevaluated by the system.

13 - The proposal is sent to the client.

14 - The client logins in the system and accepts/rejects the proposal.

15 - The CRM Collaborator updates the show status to "Accepted"/"Denied".

16.1 - If the show proposal is denied, the client discusses the proposal with CRM Manager.

16.2 - If the show proposal is accepted, the CRM Team schedules the show. There may be some negotiation with the client.

17 - The show is scheduled in the system with date and time.

18 - The client can consult the show schedule in the system.

**Drone management story:**

1 - The admin creates a user for a Drone Tech.

2 - The Drone Tech creates a drone model.

3 - The Drone Tech configs the drone language.

4 - The Drone Tech adds drones to inventory.

5 - The Drone Tech lists the drones in inventory.

6 - The Drone Tech can remove the drone from inventory.

7 - The Drone Tech sends the drone to maintenance if necessary.

8 - The Drone Tech receives the drone from maintenance.

9 - The Drone Tech can list the drones maintenance history.

### Questions

1. When the client reject's the proposal, does the status of the request change to "denied"?
2. Who schedules the show, the CRM Collaborator or the CRM Manager, because in the assignment says CRM Team but in the use case it says?
3. Does the company already has some drones and buys as needed?
4. What happens if the client refuses the offer after discussing with the CRM Manager?
5. In the document there is a use case, Customer, but there is also a reference to the client, are they the same thing?