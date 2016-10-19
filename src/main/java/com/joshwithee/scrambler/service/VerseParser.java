package main.java.com.joshwithee.scrambler.service;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

@Component
public class VerseParser {

	public String cleanVerse(String verse) {
		return verse.replaceAll("\\[\\d+(:\\d+)?\\]", "").replaceAll("[[:space:]]*\\(ESV\\)", "").replaceAll("^\\s+", "");
	}

	public String[] arrayifySingleVerse(String verse) {
		String result = cleanVerse(verse);
		String[] items = result.split(" ");
		return items;
	}

	public String[] arrayifySingleVerse(String verse, String mode) {

		String result = cleanVerse(verse);
		String[] parts = result.split(" ");
		System.out.println("Length of string array: " + parts.length);
		for (int i = 0; i < parts.length; i = i + 1) {
			System.out.println("==" + parts[i] + "==");
			;
		}
		if ("normal".equals(mode)) {

			ArrayList<String> listItems = new ArrayList<String>();
			for (int i = 1; i < parts.length; i = i + 2) {
				listItems.add(parts[i - 1] + " " + parts[i]);
			}
			if (parts.length % 2 > 0) {
				listItems.add(parts[parts.length - 1]);
			}
			for (int i = 0; i < listItems.size(); i++) {
				System.out.println("---" + listItems.get(i) + "---");
			}
			String[] items = listItems.toArray(new String[listItems.size()]);
			return items;
		} else {
			return parts;
		}
	}

	public String[] arrayifySingleVerse(String verse, int n) {

		String result = cleanVerse(verse);
		String[] parts = result.split(" ");
		System.out.println("Length of string array: " + parts.length);
		for (int i = 0; i < parts.length; i = i + 1) {
			System.out.println("==" + parts[i] + "==");
			;
		}

		ArrayList<String> listItems = new ArrayList<String>();
		for (int i = n - 1; i < parts.length; i = i + n) {

			String temp = "";
			for (int m = n - 1; m >= 0; m--) {
				temp += parts[i - m];
				if (m != 0) {
					temp += " ";
				}
			}

			listItems.add(temp);
		}
		int z = parts.length % n;
		if (z > 0) {
			String temp2 = "";
			for(int t=z; t > 0; t--){
				temp2 += parts[parts.length - t];
				if(t > 1){
					temp2 += " ";
				}
			}
			
			listItems.add(temp2);
		}
		for (int i = 0; i < listItems.size(); i++) {
			System.out.println("---" + listItems.get(i) + "---");
		}
		String[] items = listItems.toArray(new String[listItems.size()]);
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