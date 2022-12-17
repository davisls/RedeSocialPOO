package br.com.davi.socialnetwork.redesocial;

import br.com.davi.socialnetwork.exceptions.InvalidFormatPasswordException;
import br.com.davi.socialnetwork.exceptions.InvalidPasswordException;
import br.com.davi.socialnetwork.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class SocialNetwork {
	private static ArrayList<User> users = new ArrayList<>();

	private static Scanner sc = new Scanner(System.in);

	public void menu() {
		System.out.println("\n========================================");
		System.out.println("|             Menu Inicial:            |");
		System.out.println("|                                      |");
		System.out.println("|     Digite C para cadastrar-se       |");
		System.out.println("|       Digite L para fazer login      |");
		System.out.println("|    Digite F para fechar o programa   |");
		System.out.println("|                                      |");
		System.out.println("========================================\n");

		String option = sc.next();

		if (option.equalsIgnoreCase("C")) {
			register();
		} else if (option.equalsIgnoreCase("L")) {
			try {
				login();
			} catch (UserNotFoundException | InvalidPasswordException e) {
				System.out.println(e.getMessage());
				menu();
			}
		} else if (option.equalsIgnoreCase("F")) {
			System.out.println("\nPrograma encerrando...");
		} else {
			System.out.println("\nComando não identificado!");
			menu();
		}

		sc.close();
		System.out.println("Programa encerrado!");
		System.exit(0);
	}

	public void register() {
		sc.nextLine();

		System.out.print("\nDigite o seu nome: ");
		String name = sc.nextLine().trim();
		
		System.out.print("Digite o seu login: ");
		String login = sc.nextLine().trim();

		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine().trim();
		
		System.out.print("Confirme sua senha: ");
		String confirmPassword = sc.nextLine().trim();
		
		if (!password.equals(confirmPassword)) {
			System.out.println("\nO campo senha e confirmar senha devem ser iguais!");
			menu();
		}

		for (User user : users) {
			if (user.getLogin().equalsIgnoreCase(login)) {
				System.out.println("\nJá existe um usuário cadastrado com este login!");
				menu();
			}
		}
		
		if (name.length() == 0) {
			System.out.println("\nO campo 'Nome' não pode ser vazio!");
			menu();
		} else if (login.length() == 0) {
			System.out.println("\nO campo 'Login' não pode ser vazio!");
			menu();
		} else if (password.length() == 0) {
			System.out.println("\nO campo 'Senha' não pode ser vazio!");
			menu();
		}
		
		try {
			User user = new User(name, login, password, this);
			users.add(user);
		} catch (InvalidFormatPasswordException e) {
			System.out.println(e.getMessage());
			menu();
		}

		menu();
	}
	
	public void login() throws UserNotFoundException, InvalidPasswordException {
		sc.nextLine();

		System.out.print("\nDigite o seu login: ");
		String login = sc.nextLine();
		
		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();
		
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				if (user.checkIfPasswordIsCorrect(password)) {
					user.menu();
				} else {
					throw new InvalidPasswordException("\nSenha incorreta");
				}
			}
		}
		
		throw new UserNotFoundException("\nUsuário não encontrado!");
	}

}
 