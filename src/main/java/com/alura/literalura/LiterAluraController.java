package com.alura.literalura;

import com.alura.literalura.model.Book;
import com.alura.literalura.model.Author;
import com.alura.literalura.service.BookService;
import com.alura.literalura.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LiterAluraController implements CommandLineRunner {


    private BookService bookService;
    private AuthorService authorService;

    // Constructor
    @Autowired
    public LiterAluraController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("=== Catálogo de Libros LiterAlura ===");
            System.out.println("1. Agregar Libro");
            System.out.println("2. Listar Libros");
            System.out.println("3. Agregar Autor");
            System.out.println("4. Listar Autores");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    addAuthor(scanner);
                    break;
                case 4:
                    listAuthors();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 5);

        scanner.close(); // Cerramos el scanner después de usarlo
    }

    private void addBook(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el idioma del libro: ");
        String language = scanner.nextLine();
        System.out.print("Ingrese el número de descargas: ");
        int downloads = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nombre del autor: ");
        String authorName = scanner.nextLine();
        Author author = new Author();
        author.setName(authorName);
        // Aquí se pueden agregar más atributos del autor si es necesario
        authorService.saveAuthor(author);

        Book book = new Book();
        book.setTitle(title);
        book.setLanguage(language);
        book.setDownloads(downloads);
        book.setAuthor(author);
        bookService.saveBook(book);

        System.out.println("Libro agregado exitosamente.");
    }

    private void listBooks() {
        List<Book> books = bookService.getAllBooks();
        System.out.println("=== Lista de Libros ===");
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    private void addAuthor(Scanner scanner) {
        System.out.print("Ingrese el nombre del autor: ");
        String name = scanner.nextLine();
        System.out.print("Ingrese el año de nacimiento: ");
        int birthYear = scanner.nextInt();
        System.out.print("Ingrese el año de fallecimiento (0 si está vivo): ");
        int deathYear = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Author author = new Author();
        author.setName(name);
        author.setBirthYear(birthYear);
        author.setDeathYear(deathYear);
        authorService.saveAuthor(author);

        System.out.println("Autor agregado exitosamente.");
    }

    private void listAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        System.out.println("=== Lista de Autores ===");
        for (Author author : authors) {
            System.out.println(author.toString());
        }
    }
}
