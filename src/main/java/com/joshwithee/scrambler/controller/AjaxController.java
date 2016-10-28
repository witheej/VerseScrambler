package main.java.com.joshwithee.scrambler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import main.java.com.joshwithee.scrambler.model.BibleInfo;
import main.java.com.joshwithee.scrambler.model.VerseLimit;
import main.java.org.esv.bible.service.ESVService;

@Controller
public class AjaxController {

	@Autowired
	private ESVService esvService = new ESVService();
	
	@Autowired
	private BibleInfo bibleInfo = new BibleInfo();

//	@RequestMapping(value = "/versesInChapter")
//	public @ResponseBody
//	VerseLimit getChapterLength(@RequestParam("b") String b, @RequestParam("c") String c) {
//		System.out.println("In AjaxController.getChapterLength().");
//		System.out.println("book: " + b);
//		System.out.println("chapter: " + c);
//		VerseLimit verseLimit = new VerseLimit();
//		String v = esvService.getChapterVerses(b, c);
//		System.out.println("verses: " + v);
//		verseLimit.setLimit(v);
//		return verseLimit;
//	}
	
	@RequestMapping(value = "/versesInChapter")
	public @ResponseBody
	VerseLimit getChapterLength(@RequestParam("b") String b, @RequestParam("c") String c) {
		System.out.println("In AjaxController.getChapterLength().");
		System.out.println("book: " + b);
		System.out.println("chapter: " + c);
		VerseLimit verseLimit = new VerseLimit();
		int v = bibleInfo.getChapterVerses(b, c);
		System.out.println("verses: " + v);
		verseLimit.setLimit(v);
		return verseLimit;
	}

}
