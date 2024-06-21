package com.br.glm.mylibrary_literalura;

import com.br.glm.mylibrary_literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import java.util.Scanner;

@SpringBootApplication
public class MylibraryLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(MylibraryLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		exibirMenu();
	}

	private void exibirMenu() {
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		while (continuar) {
			System.out.println("Bem-vindo ao LiterAlura!");
			System.out.println("Selecione uma opção:");
			System.out.println("1. Buscar livros por título");
			System.out.println("2. Buscar livros por autor");
			System.out.println("3. Sair");
			System.out.print("Opção: ");

			int opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1:
					buscarLivrosPeloTitulo(scanner);
					break;
				case 2:
					buscarLivrosPeloAutor(scanner);
					break;
				case 3:
					continuar = false;
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
					break;
			}
		}

		scanner.close();
	}

	private void buscarLivrosPeloTitulo(Scanner scanner) {
		System.out.print("Digite o título do livro: ");
		String titulo = scanner.nextLine();
		try {
			var response = bookService.getBooks(titulo);
			bookService.printBooks(response.getResults());
		} catch (Exception e) {
			System.out.println("Erro ao buscar livros: " + e.getMessage());
		}
	}

	private void buscarLivrosPeloAutor(Scanner scanner) {
		System.out.print("Digite o nome do autor: ");
		String autor = scanner.nextLine();
		try {
			var response = bookService.getBooks(autor);
			bookService.printBooks(response.getResults());
		} catch (Exception e) {
			System.out.println("Erro ao buscar livros: " + e.getMessage());
		}
	}
}
