package com.br.glm.mylibrary_literalura;

import com.br.glm.mylibrary_literalura.models.Book;
import com.br.glm.mylibrary_literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class MylibraryLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(MylibraryLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.print("Escolha uma opção: ");
			System.out.println();
			System.out.println("1. Buscar livro por título");
			System.out.println("2. Listar todos os livros");
			System.out.println("3. Listar livros por idioma");
			System.out.println("0. Sair");
			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					System.out.print("Digite o título do livro: ");
					System.out.println();
					String title = scanner.nextLine();
					Book book = bookService.getBook(title);
					System.out.println("Livro encontrado: " + book);
					break;
				case 2:
					List<Book> allBooks = bookService.listAllBooks();
					System.out.println("Todos os livros: ");
					allBooks.forEach(System.out::println);
					break;
				case 3:
					System.out.print("Digite o idioma: ");
					System.out.println();
					String language = scanner.nextLine();
					List<Book> booksByLanguage = bookService.listBooksByLanguage(language);
					System.out.println("Livros no idioma " + language + ":");
					booksByLanguage.forEach(System.out::println);
					break;
				case 0:
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Opção inválida.");
			}
		}
	}
}
