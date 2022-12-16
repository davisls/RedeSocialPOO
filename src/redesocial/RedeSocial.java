package redesocial;

import exceptions.InvalidFormatPasswordException;
import exceptions.InvalidPasswordException;
import exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

public class RedeSocial {
	private static ArrayList<User> users = new ArrayList<>();

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("\n========================================");
		System.out.println("|             Menu Inicial:            |");
		System.out.println("|                                      |");
		System.out.println("|     Digite C para cadastrar-se       |");
		System.out.println("|       Digite L para fazer login      |");
		System.out.println("|    Digite F para fechar o programa   |");
		System.out.println("|                                      |");
		System.out.println("========================================\n");

		String option = sc.next();
		
		if (option.equals("C")) {
			register();
		} else if (option.equals("L")) {
			try {
				login();
			} catch (UserNotFoundException | InvalidPasswordException e) {
				System.out.println(e.getMessage());
				main(null);
			}
		} else if (option.equals("F")) {
			System.out.println("\nPrograma encerrando...");
		} else {
			System.out.println("\nComando não identificado!");
			main(null);
		}

		sc.close();
		System.out.println("Programa encerrado!");
		System.exit(0);
	}
	
	public static void register() {
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
			main(null);
		}

		for (User user : users) {
			if (user.getLogin().equalsIgnoreCase(login)) {
				System.out.println("\nJá existe um usuário cadastrado com este login!");
				main(null);
			}
		}
		
		if (name.length() == 0) {
			System.out.println("\nO campo 'Nome' não pode ser vazio!");
			main(null);
		} else if (login.length() == 0) {
			System.out.println("\nO campo 'Login' não pode ser vazio!");
			main(null);
		} else if (password.length() == 0) {
			System.out.println("\nO campo 'Senha' não pode ser vazio!");
			main(null);
		}
		
		try {
			User user = new User(name, login, password);
			users.add(user);
		} catch (InvalidFormatPasswordException e) {
			System.out.println(e.getMessage());
			main(null);
		}

		main(null);
	}
	
	public static void login() throws UserNotFoundException, InvalidPasswordException {
		sc.nextLine();

		System.out.print("\nDigite o seu login: ");
		String login = sc.nextLine();
		
		System.out.print("Digite a sua senha: ");
		String password = sc.nextLine();
		
		for (User user : users) {
			if (user.getLogin().equals(login)) {
				if (user.getPassword().equals(password)) {
					user.menu();
				} else {
					throw new InvalidPasswordException("\nSenha incorreta");
				}
			}
		}
		
		throw new UserNotFoundException("\nUsuário não encontrado!");
	}

}
 