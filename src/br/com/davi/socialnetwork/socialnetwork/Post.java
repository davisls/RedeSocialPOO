package br.com.davi.socialnetwork.socialnetwork;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
	private final String text;
	private final String date;
	private final String hours;

	public Post(String text) {
		Date date = new Date();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat hoursFormatter = new SimpleDateFormat("HH:mm");

		this.text = text;
		this.date = dateFormatter.format(date);
		this.hours = hoursFormatter.format(date);
	}

	public String getText() {
		return text;
	}

	public String getDate() {
		return date;
	}

	public String getHours() {
		return hours;
	}
}
