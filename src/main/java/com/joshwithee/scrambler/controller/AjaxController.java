package main.java.com.joshwithee.scrambler.controller;

import main.java.com.joshwithee.scrambler.model.VerseLimit;
import main.java.org.esv.bible.service.ESVService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

	@Autowired
	private ESVService esvService = new ESVService();

	@RequestMapping(value = "/versesInChapter")
	public @ResponseBody
	VerseLimit getChapterLength(@RequestParam("b") String b, @RequestParam("c") String c) {
		System.out.println("In AjaxController.getChapterLength().");
		System.out.println("book: " + b);
		System.out.println("chapter: " + c);
		VerseLimit verseLimit = new VerseLimit();
		String v = esvService.getChapterVerses(b, c);
		System.out.println("verses: " + v);
		verseLimit.setLimit(v);
		return verseLimit;
	}

}
