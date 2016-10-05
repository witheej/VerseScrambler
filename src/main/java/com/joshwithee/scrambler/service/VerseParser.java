package main.java.com.joshwithee.scrambler.service;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class VerseParser {

	public String cleanVerse(String verse) {
		return verse.replaceAll("\\[\\d+(:\\d+)?\\]", "")
				.replaceAll("[[:space:]]*\\(ESV\\)", "")
				.replaceAll("^\\s+", "");
	}

	public String[] arrayifySingleVerse(String verse) {
		String result = cleanVerse(verse);
		String[] items = result.split(" ");
		return items;
	}

	public String[] scrambleItems(String[] items) {
		String[] result = new String[items.length];
		Random rnd = ThreadLocalRandom.current();
		String temp;
		int j;
		for (int i = 0; i < items.length; i++) {
			result[i] = items[i];
		}
		for (int i = 0; i < items.length; i++) {
			temp = result[i];
			j = rnd.nextInt(items.length - 1);
			result[i] = result[j];
			result[j] = temp;
		}
		return result;
	}

}