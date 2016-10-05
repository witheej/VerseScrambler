package main.java.org.esv.bible.service;

import java.net.URL;
import java.net.URLEncoder;

import java.io.InputStream;

public class ESVService {

	  public String getPassage(String book, String chapter) throws Exception {
		    StringBuilder urlStringBuilder = new StringBuilder();
		    urlStringBuilder.append("http://www.esvapi.org/v2/rest/passageQuery");
		    urlStringBuilder.append("?key=IP");
		    urlStringBuilder.append("&passage="
		        + URLEncoder.encode(book + " " + chapter, "ISO-8859-1"));

//		    urlStringBuilder.append("&include-headings=true");
		    urlStringBuilder.append("&output-format=plain-text&include-headings=false&include-footnotes=false&include-passage-references=false&include-subheadings=false&include-passage-horizontal-lines=false&include-heading-horizontal-lines=false&line-length=0");
//		    http://www.esvapi.org/v2/rest/passageQuery?key=IP&passage=John+1&output-format=plain-text&include-headings=false&include-footnotes=false&include-passage-references=false&include-subheadings=false&include-passage-horizontal-lines=false&include-heading-horizontal-lines=false&line-length=0
		    
		    URL esvURL = new URL(urlStringBuilder.toString());
		    InputStream esvStream = esvURL.openStream();

		    StringBuilder outStringBuilder = new StringBuilder();
		    int nextChar;

		    while ((nextChar = esvStream.read()) != -1) {
		      outStringBuilder.append((char) nextChar);
		    }

		    esvStream.close();

		    return outStringBuilder.toString();
		  }
	
}
