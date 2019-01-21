package com.myurlshortener.business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.myurlshortener.exceptions.IllegalURLFormatException;

class UrlShortenerTest {
	
	private static final String REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
	
	@Test
	void testIncompleteURL() {
		String incorrectUrl = "http://myUrl."; //There is nothing after last '.'
		URLShortener shortener = new URLShortener();
		assertThrows(IllegalURLFormatException.class, ()-> shortener.createShorterURL(incorrectUrl));
	}
	
	@Test
	void testWithRegularString() throws Exception {
		String incorrectUrl = "WeirdString";
		URLShortener shortener = new URLShortener();
		assertThrows(IllegalURLFormatException.class, ()-> shortener.createShorterURL(incorrectUrl));
	}
	
	@Test
	void testReturnValidShortUrl() throws Exception {
		String originalURL = "https://techcrunch.com/";
		URLShortener shortener = new URLShortener();
		String shorterUrl = shortener.createShorterURL(originalURL);
		Pattern urlPattern = Pattern.compile(REGEX);
		Matcher patternMatcher = urlPattern.matcher(shorterUrl);
		assertTrue(patternMatcher.matches());
	}
	
	@Test
	void testReturnSameShorterUrlIfCalledMoreThanOnce() throws Exception {
		String firstUrl = "https://techcrunch.com/";
		URLShortener shortener = new URLShortener();
		String firstShorterURL = shortener.createShorterURL(firstUrl);
		
		String secondURL = "https://techcrunch.com/";
		assertTrue(firstShorterURL.equals(shortener.createShorterURL(secondURL)));
	}
}