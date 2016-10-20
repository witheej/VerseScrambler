package main.java.com.joshwithee.scrambler.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import main.java.com.joshwithee.scrambler.model.HomeForm;
import main.java.com.joshwithee.scrambler.service.VerseParser;
import main.java.org.esv.bible.service.ESVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class ScramblerController {

	@Autowired
	private ESVService esvService = new ESVService();

	@Autowired
	private VerseParser verseParser = new VerseParser();

	@RequestMapping(value = "/")
	public String forwardRoot() {
		return "forward:/home";
	}

	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String startHome(Model model, HttpServletRequest request) {
		HomeForm form = new HomeForm();
		model.addAttribute("submittedVerse", form);
		return "home";
	}

	@RequestMapping(value = "home", method = RequestMethod.POST)
	public String returnHome(@ModelAttribute("submittedVerse") HomeForm submittedVerse, HttpServletRequest request) {

		return "redirect:scrambler.html";
	}

	@RequestMapping(value = "/scrambler")
	public String getScrambler(@ModelAttribute("submittedVerse") HomeForm submittedVerse, HttpServletRequest request, ESVService esvService) {

		String book = submittedVerse.getBook();
		int chapter = submittedVerse.getChapter();
		int verse = submittedVerse.getVerse();
		String mode = submittedVerse.getMode();
		int groupSize = setDifficulty(mode);

		String verseText = "";
		try {
			verseText += esvService.getPassage(book, chapter + ":" + verse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (verseText.matches("^ERROR.*")) {
			System.out.println("Method getScrambler says: Invalid reference.");
			return "redirect:home.html";
		} else {
			System.out.println("Method getScrambler says: Valid reference.");
		}

		String[] parsedVerse = verseParser.arrayifySingleVerse(verseText, groupSize);
		request.setAttribute("parsedVerse", parsedVerse);

		if (parsedVerse.length > 1) {
			String[] scrambledVerse = verseParser.scrambleItems(parsedVerse);
			request.setAttribute("scrambledVerse", scrambledVerse);
		} else {
			request.setAttribute("scrambledVerse", parsedVerse);
		}

		return "scrambler";
	}

	private int setDifficulty(String mode) {
		int result = 1;

		if (mode != null) {
			switch (mode) {
			case "easy":
				result = 3;
				break;
			case "normal":
				result = 2;
				break;
			case "hard":
				result = 1;
				break;
			default:
				result = 5;
			}
		}

		return result;
	}

}
