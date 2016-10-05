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
@SessionAttributes("submittedVerse")
public class ScramblerController {
	
	
	@Autowired
	private ESVService esvService = new ESVService();

	@Autowired
	private VerseParser verseParser = new VerseParser();

	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public String startHome(Model model, HttpServletRequest request) {
		HomeForm form = new HomeForm();
		model.addAttribute("submittedVerse", form);
		return "home";
	}

	@RequestMapping(value = { "/", "home" }, method = RequestMethod.POST)
	public String returnHome(@Valid @ModelAttribute("submittedVerse") HomeForm submittedVerse, HttpServletRequest request, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Method returnHome says: Validation failed.");
			return "home";
		}else{
			System.out.println("Method returnHome says: No Errors.");
		}

		return "redirect:scrambler.html";
	}

	@RequestMapping(value = "/scrambler")
	public String getScrambler(@ModelAttribute("submittedVerse") HomeForm submittedVerse, HttpServletRequest request, ESVService esvService) {
		System.out.println("Mr. Hull has arrived in the scrambler. Scramble him!");

		String book = submittedVerse.getBook();
		int chapter = submittedVerse.getChapter();
		int verse = submittedVerse.getVerse();

		String verseText = "";
		try {
			verseText += esvService.getPassage(book, chapter + ":" + verse);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (verseText.matches("^ERROR.*")) {
			System.out.println("Method getScrambler says: Invalid reference.");
			return "home";
		} else {
			System.out.println("Method getScrambler says: Valid reference.");
		}

		String[] parsedVerse = verseParser.arrayifySingleVerse(verseText);
		String[] scrambledVerse = verseParser.scrambleItems(parsedVerse);
		request.setAttribute("parsedVerse", parsedVerse);
		request.setAttribute("scrambledVerse", scrambledVerse);

		return "scrambler";
	}

}
