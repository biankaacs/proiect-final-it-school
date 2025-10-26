# Home Library — REST API for Personal Book Management

## Description
Home Library is a Spring Boot REST API designed to help users manage their personal book collections efficiently. It provides CRUD operations for books, series management, and wishlist tracking, enabling users to organize their literary journey and keep digital records of their collections. The project showcases core Java and Spring concepts such as controllers, services, repositories, DTO mapping, transactions, and unit/integration testing with JUnit and Mockito.

## Key Features
- Books Management
    - Add, update, delete books in the personal library.
    - Search books by title, author, or genre.
    - Track reading status: TO_READ, READING, READ.
    - Personal notes & ratings for each book.
    - Series management: track volumes already owned and volumes yet to be published.

- Wishlist Functionality
    - Add books to wishlist with title, author, and status (TO_BUY, TO_BORROW, TO_BE_RELEASED).
    - Move books from wishlist to library once acquired.
    - Track your reading intentions interactively.

- Additional Features
    - Statistics for total books, read/unread count.
    - Avoids duplication by keeping accurate digital records.
    - OpenAPI/Swagger documentation for easy API exploration.

## Project Structure
- `src/main/java` — Application source code
- `src/main/resources` — Configuration files
- `pom.xml` — Maven dependencies

  Components:
    - `controller/` — REST endpoints for books, series, and wishlist
    - `service/` — Business logic
    - `repo/` — Spring Data JPA repositories
    - `model/entities/` — JPA entities
    - `model/dto/` — DTOs for requests/responses
    - `model/mapper/` — Mapping between entities and DTOs
    - `model/enums/` — Enums for reading/wishlist statuses

## Requirements
- Java JDK 11 or newer
- Maven 3.6 or newer

## Running the Application

1. Clone the repository:
   ```
   git clone https://github.com/biankaacs/proiect-final-it-school.git
   cd proiect-final-it-school/HomeLibrary
   ```

2. Build and run with Maven:
   ```
   mvn clean install
   mvn spring-boot:run 
   ```

3. Access the API at: [http://localhost:8080](http://localhost:8080)

4. Explore API endpoints using Swagger UI:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Example Endpoints

### Books Endpoints:
- GET /api/books — list all books
- POST /api/books — add a new book
- PUT /api/books/{id} — update a book
- DELETE /api/books/{id} — remove a book

### Wishlist Endpoints:
- GET /api/wishlist — list all wishlist items
- POST /api/wishlist — add a book to wishlist
- POST /api/wishlist/{id}/move-to-library — move wishlist book to library
- PUT /api/wishlist/{id}— update wishlist item
- DELETE /api/wishlist/{id}— delete wishlist item

## Testing

### Technologies & Dependencies Used:
- Java 11
- Spring Boot 3.x
- Spring Web
- Spring Data JPA + Hibernate  
- H2 (development/testing)
- Lombok 
- OpenAPI / Swagger UI 
- JUnit 5, Mockito

### Testing Methods:
#### Unit Tests:
JUnit 5 + Mockito (services tested in isolation)

#### Integration Tests:
Spring Boot context tests with MockMvc + H2 in-memory DB

Run all tests using:
```
mvn test  
```

## Contributing

Contributions are welcome! Please fork the repository and submit pull requests. For bug reports or feature requests, open an issue.

## License

This project is licensed under the MIT License. See the LICENSE file for details.
