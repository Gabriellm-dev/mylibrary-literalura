package com.br.glm.mylibrary_literalura.main;

import com.br.glm.mylibrary_literalura.models.Author;
import com.br.glm.mylibrary_literalura.models.Book;
import com.br.glm.mylibrary_literalura.models.DadosAuthor;
import com.br.glm.mylibrary_literalura.models.DadosBook;
import com.br.glm.mylibrary_literalura.repository.AuthorRepository;
import com.br.glm.mylibrary_literalura.repository.BookRepository;
import com.br.glm.mylibrary_literalura.service.ApiService;
import com.br.glm.mylibrary_literalura.service.ConvertDataAuthor;
import com.br.glm.mylibrary_literalura.service.ConvertData;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private ApiService apiService = new ApiService();
    private ConvertData converter = new ConvertData();
    private ConvertDataAuthor authorConverter = new ConvertDataAuthor();
    private final String BASE_URL = "https://gutendex.com/books/";

    private Scanner scanner = new Scanner(System.in);

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private List<Book> books;
    private List<Author> authors;
    private Optional<Author> searchedAuthor;

    public void displayMenu() {
        int choice = -1;
        while (choice != 0) {
            String menu = """
                    Choose an option and enter the number:
                    1- Search Book by Title
                    2- List all registered books
                    3- List all registered authors
                    4- List authors alive in a specific year
                    5- List books by language

                    0 - Exit
                    """;
            System.out.println(menu);
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    displayAllAuthors();
                    break;
                case 4:
                    displayAuthorsAliveInSpecificYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option...");
            }
        }
        System.exit(0);
    }

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private DadosBook fetchBookData(String bookName) {
        String json = apiService.fetchData(BASE_URL + "?search=" + bookName.replace(" ", "+"));
        DadosBook bookData = converter.fetchData(json, DadosBook.class);
        return bookData;
    }

    private DadosAuthor fetchAuthorData(String bookName) {
        String json = apiService.fetchData(BASE_URL + "?search=" + bookName.replace(" ", "+"));
        DadosAuthor authorData = authorConverter.fetchData(json, DadosAuthor.class);
        return authorData;
    }

    private String askForBookTitle() {
        System.out.println("Enter the book title: ");
        String bookTitle = scanner.nextLine();
        return bookTitle;
    }

    private void displayAllBooks() {
        try {
            List<Book> books = bookRepository.findAll();
            books.stream()
                    .sorted(Comparator.comparing(Book::getDownload))
                    .forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            books = new ArrayList<>();
        }
    }

    public void searchBookByTitle() {
        displayAllBooks();
        String bookTitle = askForBookTitle();

        books = books != null ? books : new ArrayList<>();

        Optional<Book> book = books.stream()
                .filter(b -> b.getTitle().toLowerCase()
                        .contains(bookTitle.toLowerCase()))
                .findFirst();

        if (book.isPresent()) {
            Book foundBook = book.get();
            System.out.println(foundBook);
            System.out.println("The book has already been loaded, try another one.");
        } else {
            try {
                DadosBook bookData = fetchBookData(bookTitle);
                System.out.println(bookData);

                if (bookData != null) {
                    DadosAuthor authorData = fetchAuthorData(bookTitle);
                    if (authorData != null) {
                        List<Author> authors = authorRepository.findAll();
                        authors = authors != null ? authors : new ArrayList<>();

                        Optional<Author> existingAuthor = authors.stream()
                                .filter(a -> authorData.name() != null &&
                                        a.getName().toLowerCase().contains(authorData.name().toLowerCase()))
                                .findFirst();

                        Author author;
                        if (existingAuthor.isPresent()) {
                            author = existingAuthor.get();
                        } else {
                            author = new Author(authorData.name(), authorData.birthYear(), authorData.deathYear());
                            authorRepository.save(author);
                        }

                        Book newBook = new Book(bookData.title(), author, bookData.language() != null ? bookData.language() : Collections.emptyList(), bookData.download());

                        books.add(newBook);
                        author.setBooks(books);

                        System.out.println(newBook);
                        bookRepository.save(newBook);

                        System.out.println("Book saved successfully.");
                    } else {
                        System.out.println("No author found for the book.");
                    }

                } else {
                    System.out.println("The book was not found.");
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }

    public void displayAllAuthors() {
        authors = authorRepository.findAll();
        authors.stream()
                .forEach(System.out::println);
    }

    public void displayAuthorsAliveInSpecificYear() {
        System.out.println("Enter a year:");
        int year = scanner.nextInt();
        authors = authorRepository.findAll();
        List<String> aliveAuthorsNames = authors.stream()
                .filter(a -> (a.getDeathYear() >= year) && (a.getBirthYear() <= year))
                .map(a -> a.getName())
                .collect(Collectors.toList());
        aliveAuthorsNames.forEach(System.out::println);
    }

    public void listBooksByLanguage() {
        books = bookRepository.findAll();
        List<String> uniqueLanguages = books.stream()
                .map(Book::getLanguage)
                .distinct()
                .collect(Collectors.toList());
        uniqueLanguages.forEach(language -> {
            switch (language) {
                case "pt":
                    System.out.println("pt - Portuguese");
                    break;
                case "en":
                    System.out.println("en - English");
                    break;
                case "es":
                    System.out.println("es - Spanish");
                    break;
            }
        });
        System.out.println("");
        System.out.println("Enter the language you want to search books for:");
        String languageToSearch = scanner.nextLine();
        List<Book> searchedBooks = books.stream()
                .filter(b -> b.getLanguage().contains(languageToSearch))
                .collect(Collectors.toList());
        searchedBooks.forEach(System.out::println);

    }

}
