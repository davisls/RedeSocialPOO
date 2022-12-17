package br.com.davi.socialnetwork.socialnetwork;

import br.com.davi.socialnetwork.exceptions.InvalidFormatPasswordException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
	private String name;
	private final String login;
	private String password;
	private ArrayList<Post> posts = new ArrayList<>();

	private Scanner sc = new Scanner(System.in);

	public User(String name, String login, String password, SocialNetwork socialNetwork) throws InvalidFormatPasswordException {
		this.passwordValidator(password);
		this.name = name;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) throws InvalidFormatPasswordException {
		this.passwordValidator(password);
		this.password = password;
	}

	public void menu(SocialNetwork socialNetwork) {
		System.out.println("\n========================================");
		System.out.println("Bem vindo " + this.getName() + "!");
		System.out.println("========================================");
		System.out.println("|                                      |");
		System.out.println("|         Digite P para postar         |");
		System.out.println("|  Digite T para acessar sua timeline  |");
		System.out.println("|          Digite S para sair          |");
		System.out.println("|                                      |");
		System.out.println("========================================\n");
		String option = sc.nextLine();

		if (option.equalsIgnoreCase("P")) {
			post(socialNetwork);
		} else if (option.equalsIgnoreCase("T")) {
			timeline(socialNetwork);
		} else if (option.equalsIgnoreCase("S")) {
			logout(socialNetwork);
		} else {
			System.out.println("Comando não identificado! Tente novamente");
			menu(socialNetwork);
		}
	}

	public void passwordValidator(String password) throws InvalidFormatPasswordException{
	    final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
	    final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = PATTERN.matcher(password);

        if(!matcher.matches()) {
        	throw new InvalidFormatPasswordException("\nSua senha deve conter entre 8 e 20 caracteres, sendo um númerico, um maiúsculo e um minúsculo!");
        }
	}

	public boolean checkIfPasswordIsCorrect(String password) {
		return password.equals(this.password);
	}
	
	public void post(SocialNetwork socialNetwork) {
		System.out.println("\nDigite o texto do seu post: ");
		String text = sc.nextLine();

		try {
			Post post = new Post(text);
			this.posts.add(post);
		} catch (Exception e) {
			System.out.println("\nOops... algo deu errado :( Tente novamente mais tarde!");
			menu(socialNetwork);
		}

		menu(socialNetwork);
	}
	
	public void timeline(SocialNetwork socialNetwork) {
		for(Post post : posts) {
			System.out.printf("\n%s às %s - %s\n", post.getDate(), post.getHours(), post.getText());
		}
		menu(socialNetwork);
	}

	public void logout(SocialNetwork socialNetwork) {
		socialNetwork.menu();
	}

}
