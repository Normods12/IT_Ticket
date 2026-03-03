async function allUsers(){
let response = await fetch("/users");
if(!response.ok){
throw new Error ("No Users Found");
}
let users = await response.json();
renderUser(users);
}

function renderUser(users){
const list = document.getElementById("all_users");
list.innerHTML="";

users.forEach(user => {
const li = document.createElement("li");
li.innerHTML = "ID : "+user.id+" | user name:"+user.name+" |  role: "+user.role;
list.appendChild(li);
})

}
allUsers();

const addUserBtn = document.getElementById("addUser");

let name = document.getElementById("username").value;
let email = document.getElementById("email").value;
let role = document.getElementById("role").value;

addUserBtn.addEventListener("click", async ()=>{

let name = document.getElementById("username").value;
let email = document.getElementById("email").value;
let role = document.getElementById("role").value;
let response = await fetch("/user",{
method: "POST",
headers:{
"Content-Type" : "application/json"},
body: JSON.stringify({name:name,email:email,role:role})
})
await allUsers();
}
)


const addTicketBtn = document.getElementById("addTicket");

addTicketBtn.addEventListener("click", async ()=>{
let description = document.getElementById("description").value;
let creator_id = document.getElementById("creator_id").value;

let response = await fetch("/ticket",{
method: "POST",
headers:{
"Content-Type" : "application/json"},
body: JSON.stringify({description:description,creator_id:creator_id})
})

}
)

const userTicketBtn = document.getElementById("userTickets");
userTicketBtn.addEventListener("click",async ()=>

{
let ticketId = document.getElementById("ticketId").value;
const displayDiv = document.getElementById("ticketDisplay");
let response = await fetch(`/tickets/${ticketId}`,{
method : "GET"

}
)
let ticketArray = await response.json();
let ticket = ticketArray[0];
displayDiv.innerHTML = `
                <h4>Ticket Found:</h4>
                <p><strong>ID:</strong> ${ticket.id}</p>
                <p><strong>Status:</strong> ${ticket.status}</p>
                <p><strong>Description:</strong> ${ticket.description}</p>
            `;
})


const staffTicketBtn = document.getElementById("staffTickets");
staffTicketBtn.addEventListener("click",async ()=>

{
const displayDiv = document.getElementById("ticketDisplayStaff");
let response = await fetch(`/tickets/unassigned`,{
method : "GET"
}
)
let tickets = await response.json();
let ticketDisplayDiv = await document.getElementById("ticketDisplayStaff");
ticketDisplayDiv.innerHTML="";
tickets.forEach(ticket =>{
let li =  document.createElement("li");
li.innerHTML="ticket Id: "+ticket.id+" | Ticket Description : "+ticket.description+" | Ticket Status : "+ticket.status+" | Create By User Id: "+ticket.created_by;
ticketDisplayStaff.appendChild(li);
}
)
})

const assignTicketBtn = document.getElementById("assignTicket");
assignTicketBtn.addEventListener("click", async ()  =>{
let ticketId =  document.getElementById("assignTicketId").value;
let userId = document.getElementById("assignUserId").value;
let response = await fetch("/ticket/assign", {
method : "POST",
headers : {
"Content-Type" : "application/json"
},
body : JSON.stringify({ticketId : ticketId, userId:userId})
})
}
)


const updateTicketBtn = document.getElementById("updateTicket");
updateTicketBtn.addEventListener("click", async ()  =>{
let ticketId = document.getElementById("updateTicketId").value
let status =  document.getElementById("updateStatus").value;
let priority = document.getElementById("updatePriority").value;
let response = await fetch("/ticket/update", {
method : "POST",
headers : {
"Content-Type" : "application/json"
},
body : JSON.stringify({ticketId:ticketId,status:status,priority:priority})
})
}
)


const adminAllTickets = document.getElementById("adminAllTickets");
adminAllTickets.addEventListener("click",async ()=>

{
let response = await fetch(`/tickets`,{
method : "GET"
}
)
let tickets = await response.json();
let allTicketDiv = await document.getElementById("allTicketDiv");
allTicketDiv.innerHTML="";
tickets.forEach(ticket =>{
let li =  document.createElement("li");
li.innerHTML="ticket Id: "+ticket.id+" | Ticket Description : "+ticket.description+" | Ticket Status : "+ticket.status+" | Create By User Id: "+ticket.created_by+" | Assigned to : "+ticket.assigned_staff;
ticketDisplayStaff.appendChild(li);
}
)
})


const adminStatusTickets = document.getElementById("adminStatusTickets");
adminStatusTickets.addEventListener("click",async ()=>
{
    let status = document.getElementById("byStatus").value;
    let response = await fetch(`/tickets/filter/s?status=${status}`,{
        method : "GET"
    })

    // ONLY loop if the request was successful (Not a 400 error)
    if(response.ok) {
        let tickets = await response.json();
        let statusTicketDiv = await document.getElementById("statusTicketDiv");
        statusTicketDiv.innerHTML="";
        tickets.forEach(ticket =>{
            let li =  document.createElement("li");
            li.innerHTML="ticket Id: "+ticket.id+" | Ticket Description : "+ticket.description+" | Ticket Status : "+ticket.status+" | Create By User Id: "+ticket.created_by+" | Assigned to : "+ticket.assigned_staff;

            // Fixed the append bug here too so it shows up in the right div
            statusTicketDiv.appendChild(li);
        })
    } else {
        alert("Backend rejected the status check (400 Bad Request)");
    }
})


const adminPriorityTickets = document.getElementById("adminPriorityTickets");
adminPriorityTickets.addEventListener("click",async ()=>
{
    let priority = document.getElementById("byPriority").value;
    let response = await fetch(`/tickets/filter/p?priority=${priority}`,{ // Note: Your Java backend must have a parameter or endpoint for 'priority' for this to work!
        method : "GET"
    })

    // ONLY loop if the request was successful
    if(response.ok) {
        let tickets = await response.json();
        let priorityTicketDiv = await document.getElementById("priorityTicketDiv");
        priorityTicketDiv.innerHTML="";
        tickets.forEach(ticket =>{
            let li =  document.createElement("li");
            li.innerHTML="ticket Id: "+ticket.id+" | Ticket Description : "+ticket.description+" | Ticket Status : "+ticket.status+" | Create By User Id: "+ticket.created_by+" | Assigned to : "+ticket.assigned_staff;
            priorityTicketDiv.appendChild(li);
        })
    } else {
        alert("Backend rejected the priority check (400 Bad Request)");
    }
})