# MediaHub Angular Frontend & Integration Plan

This plan details the implementation of a modern, responsive Angular 18 application that integrates with the MediaHub microservices backend via the API Gateway. It also lists the minor backend CORS adjustments required to allow frontend-backend communication.

## User Review Required

> [!IMPORTANT]
> **Default Seed User Passwords**:
> In the backend database, password verification is done using plain text equality comparison. Therefore, when logging in as any of the seeded users, you must input the exact raw string stored in the database. For example:
> - **Admin**: Email: `admin@mediahub.com` | Password: `$2b$12$adminHash001`
> - **Creator**: Email: `priya.menon@email.com` | Password: `$2b$12$creatorHash003`
> - **Subscriber**: Email: `arjun@email.com` | Password: `$2b$12$subscriberHash002`
>
> For any new registered users, the password will be whatever they input during registration (e.g., `SecurePass123`).

> [!WARNING]
> **Backend CORS Changes**:
> The backend gateway does not currently have CORS configured, and the Content Catalog microservice restricts CORS to specific ports. We will add a WebFlux-compatible CORS configuration to the Gateway and add the frontend domain `http://localhost:4200` to the Content Catalog microservice CORS settings.

---

## Open Questions

*None at this time. All API routes, data structures, and auth mechanisms have been fully identified and mapped.*

---

## Proposed Changes

### 1. Backend Integration (CORS Configuration)

We will modify the backend files to enable cross-origin requests from the frontend at `http://localhost:4200`.

#### [NEW] [GatewayCorsConfig.java](file:///c:/Users/hp/Downloads/mediahub-integrated-repo-main/mediahub-integrated-repo-main/MediaHub-Integrated/gateway/src/main/java/com/mediahub/gateway/config/GatewayCorsConfig.java)
- Add a reactive `CorsWebFilter` bean to handle OPTIONS preflight requests globally at the Gateway (port 8094).

#### [MODIFY] [SecurityConfig.java](file:///c:/Users/hp/Downloads/mediahub-integrated-repo-main/mediahub-integrated-repo-main/MediaHub-Integrated/contentcatalog_git_individual/src/main/java/com/mediahub/contentcatalog/config/SecurityConfig.java)
- Add `http://localhost:4200` to the list of allowed origins in the `corsConfigurationSource()` method.

---

### 2. Angular 18 Frontend Application

We will initialize and construct a complete Angular 18 application using standard Angular modules (non-standalone setup to facilitate modularity).

#### Directory Path
`c:\Users\hp\Downloads\mediahub-integrated-repo-main\mediahub-frontend`

#### Project Structure
```
mediahub-frontend/
├── src/
│   ├── app/
│   │   ├── core/                  # Core services, guards, and interceptors
│   │   │   ├── auth/
│   │   │   │   ├── auth.service.ts
│   │   │   │   └── auth.guard.ts
│   │   │   └── interceptors/
│   │   │       └── jwt.interceptor.ts
│   │   ├── models/                # Interfaces mapping backend entities & DTOs
│   │   │   ├── user.model.ts
│   │   │   ├── creator.model.ts
│   │   │   ├── content.model.ts
│   │   │   ├── tag.model.ts
│   │   │   └── audit.model.ts
│   │   ├── services/              # API Client services
│   │   │   ├── user.service.ts
│   │   │   ├── creator.service.ts
│   │   │   ├── content.service.ts
│   │   │   ├── tag.service.ts
│   │   │   └── audit.service.ts
│   │   ├── shared/                # Common components and modules
│   │   │   └── shared.module.ts
│   │   ├── components/            # Layout and view components
│   │   │   ├── login/
│   │   │   ├── register/
│   │   │   ├── dashboard/
│   │   │   ├── creator-profile/
│   │   │   ├── content-catalog/
│   │   │   │   ├── content-list/
│   │   │   │   ├── content-form/
│   │   │   │   └── content-detail/
│   │   │   ├── admin/
│   │   │   │   ├── user-list/
│   │   │   │   ├── creator-review/
│   │   │   │   └── audit-log/
│   │   │   └── layout/            # Navbar, footer, etc.
│   │   ├── app.module.ts
│   │   ├── app-routing.module.ts
│   │   └── app.component.ts
│   ├── assets/
│   ├── index.html
│   └── styles.css                 # Global CSS styles (Modern Glassmorphism & dark mode tokens)
├── angular.json
├── package.json
└── tsconfig.json
```

#### Key Implementation Details
1. **HTTP Client Integration & JWT Interceptor**:
   - `JwtInterceptor` intercepts every request to attach the `Authorization: Bearer <token>` header if a token exists in local storage.
2. **Environment Files**:
   - Store the API gateway base URL (`http://localhost:8094`) in `environment.ts` and `environment.prod.ts`.
3. **Route Guards**:
   - Protect views (e.g. Creator dashboard, Admin dashboard) using an `AuthGuard` that checks user login state and verifies role authorization.
4. **Rich CSS Aesthetics**:
   - Sleek dashboard using dark mode design tokens, smooth animations, and glassmorphic cards.
   - Loading indicators, form validation styles, dynamic success/error alert banners.

---

## Verification Plan

### Automated Tests
- Build and run the Angular application:
  ```bash
  npm run build
  ```
  Ensure it compiles and builds without any errors.

### Manual Verification
1. **Run the Backend**: Start the microservices via `.\run-all.ps1`.
2. **CORS Validation**: Verify that preflight and actual requests succeed between the Angular app (`localhost:4200`) and API Gateway (`localhost:8094`).
3. **User Flow Testing**:
   - Register a new user and log in.
   - Create a Creator Profile.
   - Upload/Create new content draft and add tags.
   - Log in as the Admin (password: `$2b$12$adminHash001`) to view the audit logs, suspend/activate users, and update content/creator statuses.
