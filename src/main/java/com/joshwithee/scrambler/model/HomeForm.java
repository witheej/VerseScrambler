package main.java.com.joshwithee.scrambler.model;

import org.hibernate.validator.constraints.Range;


public class HomeForm {

	private String book;
	@Range(min=1, max=150)
	private int chapter;
	@Range(min=1, max=176)
	private int verse;

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public int getVerse() {
		return verse;
	}

	public void setVerse(int verse) {
		this.verse = verse;
	}
}
