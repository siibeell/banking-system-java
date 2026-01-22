# Banking System (Java Swing)

A desktop-based Banking System application developed using Java and Swing, designed with an object-oriented and layered architecture approach.
This project simulates core banking operations such as account management, card applications, payments, transfers, and investment transactions.

---

Features

- User registration and login system
- Account management
  - Demand (Vadesiz) accounts
  - Time deposit (Vadeli) accounts
  - Investment accounts
- Money transfer between accounts (Havale)
- Payment operations
  - Bill payments
  - Credit payments
  - Insurance & pension payments
- Card management
  - Automatic bank card creation
  - Credit card application and tracking
- Credit system
  - Housing loans
  - Personal loans
- Investment transactions
  - Currency trading
  - Gold trading
- Transaction history tracking
- Modular and user-friendly Swing UI

---

## Project Architecture

The project follows a layered architecture design, separating responsibilities clearly across packages.

### Package Structure

- **model**
  - Core domain classes and abstractions  
  - Represents business entities such as accounts, cards, credits, and payments  
  - Implements object-oriented principles (inheritance, abstraction, polymorphism)

- **service**
  - Contains business logic and application rules  
  - Manages operations such as authentication, transactions, card management, and payments  
  - Acts as a bridge between the UI and the domain model

- **ui**
  - Java Swing–based user interface  
  - Manages all screens, panels, and user interactions  
  - Communicates with service layer to perform operations

This structure improves maintainability, readability, and scalability by enforcing a clear separation of concerns.

---

Technologies Used

- Java (JDK 11)
- Java Swing (GUI)
- Object-Oriented Programming (OOP)
- Git & GitHub

---

Learning Outcomes

Through this project, I practiced and strengthened:

- Object-oriented design principles (inheritance, abstraction, polymorphism)
- Layered software architecture
- Java Swing UI development
- Managing complex application flows
- Git version control and GitHub project management

---

How to Run

1. Clone the repository:
   git clone https://github.com/siibeell/banking-system-java.git
2. Open the project in an IDE (IntelliJ IDEA, Eclipse, or VS Code with Java support)
3. Run Main.java from the ui package

---

##Notes

This project was developed as part of an Object-Oriented Programming course and focuses on clean code structure and realistic banking scenarios rather than database integration.
