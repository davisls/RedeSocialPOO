package redesocial;

import exceptions.InvalidFormatPasswordException;

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

	public User(String name, String login, String password) throws InvalidFormatPasswordException {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws InvalidFormatPasswordException {
		this.passwordValidator(password);
		this.password = password;
	}

	public void menu() {
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
		
		if (option.equals("P")) {
			post();
		} else if (option.equals("T")) {
			timeline();
		} else if (option.equals("S")) {
			RedeSocial.main(null);
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
	
	public void post() {
		System.out.println("\nDigite o texto do seu post: ");
		String text = sc.nextLine();

		try {
			Post post = new Post(text);
			this.posts.add(post);
		} catch (Exception e) {
			System.out.println("\nOops... algo deu errado :( Tente novamente mais tarde!");
			menu();
		}

		menu();
	}
	
	public void timeline() {
		for(Post post : posts) {
			System.out.printf("\n%s às %s - %s\n", post.getDate(), post.getHours(), post.getText());
		}
		menu();
	}
}
