#  Quantum Bookstore

A clean, modular Java application simulating an online bookstore. Built using **SOLID principles**, **MVC architecture**, and **polymorphic design**, it supports multiple book types, purchase logic, delivery strategies, and extensibility for future product types.

---

## 🚀 Features

- **Inventory Management**
  - Add books of various types (PaperBook, EBook, ShowcaseBook)
  - Remove outdated books based on publication year

- **Book Types**
  - `PaperBook`: Physical books with stock, delivered to an address
  - `EBook`: Digital books with file types, sent via email
  - `ShowcaseBook`: Demo books not for sale

- **Purchase Handling**
  - Buy a book via ISBN, email, quantity, and address
  - Automatically validates stock, book availability
  - Delegates delivery to appropriate service

- **Delivery Services (Strategy Pattern)**
  - `MailServiceImpl`: Sends eBooks
  - `ShippingServiceImpl`: Ships paper books

- **Test Runner**
  - `Main`: Demonstrates full use of the application

---

## 📦 Packages Structure

```bash
com.quantumbookstore
├── controller          # MVC controller for book operations
├── model               # Book types and business rules
├── repo                # BookRepository: in-memory storage
├── service             # BookstoreService: core business logic
├── services            # NotificationService interface + implementations
├── exceptions          #Custom exception 
└── Main.java
```

---

##  Design Principles Used

- ✅ **S**ingle Responsibility Principle: Each class has one clear job
- ✅ **O**pen/Closed Principle: Easily extend new Book types or services
- ✅ **L**iskov Substitution: Subtypes can be replaced without breaking logic
- ✅ **I**nterface Segregation: `NotificationService` clearly separates responsibilities
- ✅ **D**ependency Inversion: No class depends on hardcoded concrete types

---

##  Technologies

- Java 21+
- Maven (for project setup, if desired)
- JUnit 5 + Mockito (for testing)

---

##  How to Run

1. Clone the repository or copy the source files
2. Compile and run `Main.java`
3. Observe console output for add/remove/buy actions

---

## 🔧 Testing

### ✅ Dependencies
Add the following to your `pom.xml` for unit testing:

```xml
<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito Core -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-core</artifactId>
        <version>5.12.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Mockito JUnit 5 integration -->
    <dependency>
        <groupId>org.mockito</groupId>
        <artifactId>mockito-junit-jupiter</artifactId>
        <version>5.12.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

Also include this plugin:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.1.2</version>
    </plugin>
  </plugins>
</build>
```

---

##  Example Output

```
Quantum book store: Book added - Java Basics
Quantum book store: Book added - Clean Code
Quantum book store: Book added - Exclusive Preview
Quantum book store: Shipping PaperBook 'Java Basics' to 123 Elm Street
Quantum book store: Sending EBook 'Clean Code' to reader@example.com
Quantum book store: Error - Book not for sale.
Quantum book store: Removed outdated book - Java Basics
```

---

## 🗉️ Extensibility

To add a new book type:
1. Extend `Book` and override `isPurchasable()` + `deliver()`
2. Inject a suitable `NotificationService`
3. Done! No changes to core logic required

---

## 👨‍💼 Author
**Yousef El-llban** – Passionate Java/Spring developer applying clean code and design best practices.

