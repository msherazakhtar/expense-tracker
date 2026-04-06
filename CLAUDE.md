# CLAUDE.md — Split House (Expense Tracker API)

## Project Overview

**Application**: Split House / Expense Tracker API  
**Framework**: Spring Boot 3.4.6, Java 21  
**Database**: PostgreSQL (Supabase in production)  
**Port**: 5555 (via `PORT` env var)  
**Production Frontend**: `https://splithouse.devbotsolutions.com`  
**Startup log**: `">>>>>>Split House Web Service Is Now Running<<<<<<"`

---

## Package Structure

```
src/main/java/com/expense/tracker/
├── Controllers/        # REST controllers
├── services/           # Service interfaces + implementations
├── models/             # JPA entities (suffixed with ORM)
├── repositories/       # Spring Data JPA repositories
├── dtos/               # Java records (immutable DTOs)
├── security/           # JWT + Spring Security
├── utilities/          # FileUtils, EmailUtility, MappingUtility, etc.
├── exceptions/         # Custom exceptions + GlobalExceptionHandler
├── wrappers/           # Composite request/response objects
└── enums/              # ResponseStatus enum
```

---

## Naming Conventions

- **Entities**: `UsersORM`, `ExpenseORM`, `GroupsORM` — always suffixed with `ORM`
- **DTOs**: Java `record` types — e.g., `ExpenseRecord`, `GroupMembersRecord`
- **Responses**: Always wrapped in `ApiResponse<T>` with `responseMessage`, `responseData`, `responseStatus`
- **Soft delete**: Entities extending `GlobalFields` have an `isDeleted` flag — never hard delete these
- **Audit fields**: `createdAt`, `createdBy`, `modifiedAt`, `modifiedBy` inherited from `GlobalFields`

---

## Controllers & Base Paths

| Controller | Base Path | Auth Required |
|---|---|---|
| `AuthController` | `/auth` | No |
| `UserController` | `/user` | Yes |
| `ExpenseController` | `/expense` | Yes |
| `GroupsController` | `/groups` | Yes |
| `GroupMembersController` | `/group-members` | Yes |
| `ExpenseCategoryController` | `/expenseCategory` | Yes |
| `ExpenseSettlementController` | `/settlement` | Yes |
| `SetupsController` | `/setups` | Yes (empty) |

Public endpoints (no JWT): `/auth/**`, `/swagger-ui/**`, `/v3/api-docs*/**`

---

## Security

- **Auth**: JWT (HS256), 10-hour expiry, claims: `userId`, `role`, subject = email
- **Filter**: `JwtAuthFilter` → `CustomUserDetailsService` → `CustomUserDetails`
- **Password**: BCryptPasswordEncoder
- **CORS allowed origins**: `http://localhost:4200`, `https://splithouse.devbotsolutions.com`
- **JWT secret is hardcoded** in `JwtUtil.java` — should be moved to env var before production

---

## Database

- **ORM**: Spring Data JPA / Hibernate, DDL auto = `none` in production
- **Schema**: Applied manually via SQL scripts in `DB files/`
- **Key relationships**:
  - `Users` → `Expenses` (1:N)
  - `Users` → `Groups` (1:N)
  - `Groups` → `GroupMembers` (1:N)
  - `Expenses` → `ExpenseDetails` (1:N)
  - `Expenses` → `ExpenseSettlements` (1:N)
  - `ExpenseDetails` → `GroupMembers` (N:1)

---

## Environment Variables

| Variable | Purpose | Local Default |
|---|---|---|
| `PORT` | Server port | `5555` |
| `DATABASE_URL` | JDBC connection string | `jdbc:postgresql://localhost:5432/splithouse` |
| `DB_USERNAME` | DB user | `postgres` |
| `DB_PASSWORD` | DB password | `test` |
| `HIBERNATE_DDL_AUTO` | Schema strategy | `none` |
| `FILE_UPLOAD_DIR` | Profile picture storage path | `src/main/resources/static/images` |

Local overrides go in `application-local.properties` (verbose SQL logging enabled there).

---

## Docker

- **Dockerfile**: Multi-stage build (JDK 21 build → JRE 21 runtime), exposes port `8080`
- **docker-compose**: Starts PostgreSQL 15 + Spring Boot API together
- Run locally: `docker-compose up --build`

---

## Key Notes for Development

- `ExpenseWrapper` is used for creating expenses — it bundles `ExpenseRecord` + `List<ExpenseDetailsRecord>`
- `SearchCriteria` drives paginated/filtered expense searches
- `ExpenseCategoryORM` supports both global categories (`isGlobal=true`) and user-specific ones
- File uploads land in `FILE_UPLOAD_DIR` — in containers this is not persistent; use a volume or object storage
- `MailConfigurationORM` stores SMTP settings in the DB, looked up by mail server string
- Swagger UI: `http://localhost:5555/swagger-ui.html`
