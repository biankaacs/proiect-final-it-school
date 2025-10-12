# Home Library Application

## Description

The Home Library Application is designed to help users manage their personal book collections in an organized and user-friendly way. With this application, users can:

* Add new books to the library (title, author, year, genre, description)
* Quickly search for books by title, author, or category
* Mark books as "read", "currently reading", or "to read"
* Update or delete book information when a book is sold or donated
* Generate simple statistics (total number of books, how many are read or unread)
* Keep a digital record of the personal library, avoiding loss or duplication of volumes

The ultimate goal is to provide users with a convenient way to manage and explore their book collection directly from their computer.

---

## Additional Features

### Personal Evaluation

Users can add personal notes or comments to books, such as:

* "Worth rereading"
* "Favorite book"
* "Favorite character"

### Series Management

For books that are part of a series (e.g., trilogies, sagas), the application keeps track of:

* Volumes already owned
* Volumes yet to be published
  This allows users to know exactly which books are missing from their collection.

### Wishlist Functionality

* Users can add titles they want to purchase or read in the future.
* Each wishlist entry can include: title, author, and status (e.g., "to buy", "to borrow", "awaiting release").
* Users can move a book from the wishlist to the main library once it has been acquired.

This feature transforms the application from a simple digital library into a **personalized reading experience**, allowing users to organize and track their literary journey interactively.

---

## Project Structure

* `src/main/java` - contains the source code
* `src/main/resources` - configuration files and resources
* `pom.xml` - Maven dependency management

## Requirements

* Java JDK 11 or newer
* Maven 3.6 or newer

## How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/biankaacs/proiect-final-it-school.git
   ```
2. Navigate to the project directory:

   ```bash
   cd proiect-final-it-school/HomeLibrary
   ```
3. Build and run the project with Maven:

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
4. The application will start and be accessible at `http://localhost:8080` (for Spring Boot web applications).

---

## Contributions

Contributions are welcome! Please create a **pull request** or open an **issue** if you find bugs or have suggestions.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
