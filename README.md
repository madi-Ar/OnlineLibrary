Description

OnlineLibrary is a learning project built with Java Spring Boot, implementing an online library system with support for users, employees, and administrators.
The project includes:

Authentication and registration via JWT tokens

Role-based access control (USER, EMPLOYEE, ADMIN)

REST API for managing books, records, cards, and users

Docker integration for easy deployment

🔹 Technologies

Java 17

Spring Boot (Web, Security, Data JPA)

Hibernate/JPA

JWT authentication

Docker / Docker Compose

PostgreSQL

🔹 Installation & Run
1. Clone the repository
bash
git clone https://github.com/madi-Ar/OnlineLibrary.git
cd OnlineLibrary
2. Run with Docker
bash
docker-compose up --build
3. Access the application
API: http://localhost:8081

Database: localhost:5432 (PostgreSQL)

🔹 Test Data
The project includes a data.sql file that loads test users automatically:

Email: user1@example.com, Password: password

Email: user2@example.com, Password: password

Email: employee@example.com, Password: password

Email: admin@example.com, Password: password

⚠️ These accounts are intended only for local development.

🔹 Main Endpoints

Endpoint	Method	Description

/login	POST	User authentication

/registration	POST	Register a new user

/books	GET	Retrieve list of books

/records	GET/POST	Manage records

/users	GET/PUT	Manage users


🔹 Known Issues

❌ After registration, a new user is saved to the repository, but login may fail.

❌ Possible cause: multiple UserDetailsService beans (employeeDetailsServiceImpl, userDetailsServerImpl). The global Authentication Manager does not use a UserDetailsService for username/password login. Consider publishing a single bean.

❌ Bug: Unable to open JDBC Connection for DDL execution (investigation required).
