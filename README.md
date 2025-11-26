# Transportation Fleet Management System (TFMS)

This workspace now separates the **Spring Boot backend** and the **HTML/CSS frontend** into dedicated folders for clarity.

```
project-root/
├── backend/      # Spring Boot Maven project
└── frontend/     # Static dashboard + module pages
```

## Backend

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

The server runs on `http://localhost:8081`. On startup it logs a clickable link (e.g. `http://localhost:8081/index.html`) that opens the dashboard served directly from the `frontend` folder.

## Frontend (optional local server)

The backend already serves `/index.html`, but you can also load the static files manually for design work:

```powershell
cd frontend
npx serve .
```

Update the `API_BASE` constants in the HTML files if you host the backend on a different port or domain.

