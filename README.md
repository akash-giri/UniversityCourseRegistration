🎓 University Course Registration System
-------------------------------------------------------
A robust, thread-safe Java implementation of a student course enrollment system. This project demonstrates SOLID principles, Concurrency Control, and the Service-Repository Pattern to handle high-traffic registration periods without data corruption.

🚀 Key Features
----------------------------------------------------------

Thread-Safe Registration: Prevents "over-enrollment" even when multiple students click "Register" at the exact same millisecond.

Search Engine: Allows searching for courses by both Course Code (e.g., CS101) and Course Name (e.g., Java Masterclass).

Decoupled Architecture: Models, Data Storage, and Business Logic are strictly separated to allow for easy future enhancements (like adding a Database or a Waitlist).

Capacity Enforcement: Validates enrollment limits before finalizing any registration.

🛠️ Design Principles Applied (SOLID)
----------------------------------------------------------------
S (Single Responsibility): The Course class only holds data. The RegistrationService only handles the logic of joining them.

O (Open/Closed): New registration rules (e.g., checking for prerequisites) can be added by extending the service without modifying the core Student or Course models.

D (Dependency Injection): The RegistrationService receives its CourseRepository via the constructor, making it easy to swap the storage layer for a SQL database later.

🧵 Concurrency & Data Consistency
----------------------------------------------------------------
To prevent Race Conditions (where two students take the last remaining seat), we implemented:

Synchronized Blocks: Ensures "Check-then-Act" atomicity.

Concurrent Collections: Uses ConcurrentHashMap to store enrollments safely across multiple threads.

Atomic Validation: The system checks currentEnrollment.size() and adds the new record within a single locked execution path.

📈 Future Scalability
---------------------------------------------------------------------
For a production-grade deployment at a global scale:

Database: Replace the HashMap with a PostgreSQL database using ACID transactions.

Distributed Locking: Use Redis if the application is deployed across multiple server instances (Load Balanced).

API Layer: Wrap the service in a Spring Boot REST API.