const baseUrl = "http://localhost:8081"; // Update if backend uses a different port

// ================= USER REGISTRATION =================
document
  .getElementById("sign-up-form")
  ?.addEventListener("submit", function (event) {
    event.preventDefault();

    const userData = {
      username: document.getElementById("username").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
    };

    fetch(`${baseUrl}/api/users/register`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(userData),
    })
      .then((res) => res.json())
      .then(() => {
        alert("User registered successfully!");
        window.location.href = "index.html";
      })
      .catch((err) => alert("Registration failed."));
  });

// ================= CREATE EVENT =================
document
  .getElementById("create-event-form")
  ?.addEventListener("submit", function (event) {
    event.preventDefault();

    const eventData = {
      title: document.getElementById("event-title").value,
      dateTime: document.getElementById("event-date-time").value,
      location: document.getElementById("event-location").value,
      description: document.getElementById("event-description").value,
    };

    fetch(`${baseUrl}/api/events`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(eventData),
    })
      .then((res) => res.json())
      .then(() => {
        alert("Event created!");
        window.location.href = "events.html";
      })
      .catch((err) => alert("Failed to create event."));
  });

// ================= UPDATE EVENT =================
document
  .getElementById("edit-event-form")
  ?.addEventListener("submit", function (event) {
    event.preventDefault();

    const eventId = document.getElementById("edit-event-id").value;

    const eventData = {
      title: document.getElementById("edit-event-title").value,
      dateTime: document.getElementById("edit-event-date-time").value,
      location: document.getElementById("edit-event-location").value,
      description: document.getElementById("edit-event-description").value,
    };

    fetch(`${baseUrl}/api/events/${eventId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(eventData),
    })
      .then((res) => res.json())
      .then(() => {
        alert("Event updated!");
        window.location.href = "events.html";
      })
      .catch((err) => alert("Failed to update event."));
  });

// ================= DELETE EVENT =================
function deleteEvent(eventId) {
  fetch(`${baseUrl}/api/events/${eventId}`, {
    method: "DELETE",
  })
    .then(() => {
      alert("Event deleted!");
      window.location.reload();
    })
    .catch((err) => alert("Failed to delete event."));
}

// ================= LOAD EVENTS =================
window.addEventListener("DOMContentLoaded", () => {
  const container = document.getElementById("event-list");
  if (!container) return;

  fetch(`${baseUrl}/api/events`)
    .then((res) => res.json())
    .then((events) => {
      container.innerHTML = events
        .map(
          (event) => `
                <div class="event">
                    <h3>${event.title}</h3>
                    <p>${event.dateTime}</p>
                    <p>${event.location}</p>
                    <p>${event.description}</p>
                    <button onclick="deleteEvent(${event.id})">Delete</button>
                    <a href="edit-event.html?eventId=${event.id}">Edit</a>
                </div>
            `
        )
        .join("");
    })
    .catch(() => (container.innerHTML = "<p>Failed to load events.</p>"));
});

// ================= SEARCH EVENTS =================
document
  .getElementById("search-form")
  ?.addEventListener("submit", function (event) {
    event.preventDefault();

    const title = document.getElementById("search-title").value;
    const location = document.getElementById("search-location").value;
    const start = document.getElementById("search-start").value;
    const end = document.getElementById("search-end").value;

    let url = `${baseUrl}/api/events/search?`;

    if (title) url += `title=${encodeURIComponent(title)}&`;
    if (location) url += `location=${encodeURIComponent(location)}&`;
    if (start) url += `start=${encodeURIComponent(start)}&`;
    if (end) url += `end=${encodeURIComponent(end)}&`;

    fetch(url)
      .then((res) => res.json())
      .then((events) => {
        const container = document.getElementById("event-list");
        container.innerHTML = events
          .map(
            (event) => `
                <div class="event">
                    <h3>${event.title}</h3>
                    <p>${event.dateTime}</p>
                    <p>${event.location}</p>
                    <p>${event.description}</p>
                </div>
            `
          )
          .join("");
      })
      .catch((err) => alert("Search failed."));
  });

// ================= SEND INVITATION =================
document
  .getElementById("invitation-form")
  ?.addEventListener("submit", function (event) {
    event.preventDefault();

    const eventId = document.getElementById("invite-event-id").value;
    const userId = document.getElementById("invite-user-id").value;

    fetch(`${baseUrl}/api/invitations/${eventId}/invite/${userId}`, {
      method: "POST",
    })
      .then((res) => res.json())
      .then(() => alert("Invitation sent!"))
      .catch((err) => alert("Failed to send invitation."));
  });

// ================= ACCEPT INVITATION =================
function acceptInvitation(invitationId) {
  fetch(`${baseUrl}/api/invitations/accept/${invitationId}`, {
    method: "PUT",
  })
    .then((res) => res.json())
    .then(() => alert("Invitation accepted!"))
    .catch((err) => alert("Failed to accept invitation."));
}

// ================= DECLINE INVITATION =================
function declineInvitation(invitationId) {
  fetch(`${baseUrl}/api/invitations/decline/${invitationId}`, {
    method: "PUT",
  })
    .then((res) => res.json())
    .then(() => alert("Invitation declined!"))
    .catch((err) => alert("Failed to decline invitation."));
}

// ================= GET USER INVITATIONS =================
function loadInvitations(userId) {
  fetch(`${baseUrl}/api/invitations/user/${userId}`)
    .then((res) => res.json())
    .then((invitations) => {
      const container = document.getElementById("invitations");
      container.innerHTML = invitations
        .map(
          (invite) => `
                <div>
                    <p>Event: ${invite.event.title}</p>
                    <p>Status: ${invite.status}</p>
                    <button onclick="acceptInvitation(${invite.id})">Accept</button>
                    <button onclick="declineInvitation(${invite.id})">Decline</button>
                </div>
            `
        )
        .join("");
    })
    .catch((err) => alert("Failed to load invitations."));
}
// ================= AI-GENERATED DESCRIPTION =================
async function generateDescription() {
    const title = document.getElementById("event-title").value;
  
    if (!title) {
      alert("Please enter a title first.");
      return;
    }
  
    const prompt = `Write a professional and engaging event description for an event titled "${title}"`;
  
    try {
      const response = await fetch("https://api.openai.com/v1/completions", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer YOUR_OPENAI_API_KEY" // Replace this
        },
        body: JSON.stringify({
          model: "text-davinci-003",
          prompt: prompt,
          max_tokens: 100
        })
      });
  
      const data = await response.json();
      const text = data.choices?.[0]?.text?.trim();
  
      if (text) {
        document.getElementById("event-description").value = text;
      } else {
        alert("Could not generate description.");
      }
    } catch (error) {
      console.error("AI Error:", error);
      alert("Failed to generate description.");
    }
  }
  