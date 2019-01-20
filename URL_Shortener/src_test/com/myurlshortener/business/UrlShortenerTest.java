package com.myurlshortener.business;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import com.myurlshortener.exceptions.IllegalUrlFormatException;

class UrlShortenerTest {
	
	private static final String REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
	
	@Test
	void testUrlWithoutDomain() {
		String incorrectUrl = "http://myUrl."; //There is nothing after last '.'
		URLShortener shortener = new URLShortener(incorrectUrl);
		assertThrows(IllegalUrlFormatException.class, ()-> shortener.getShorterUrl());
	}
	
	@Test
	void testNotFormattedString() throws Exception {
		String incorrectUrl = "WeirdString";
		URLShortener shortener = new URLShortener(incorrectUrl);
		assertThrows(IllegalUrlFormatException.class, ()-> shortener.getShorterUrl());
	}
	
	@Test
	void testReturnValidShortUrl() throws Exception {
		String originalUrl = "https://techcrunch.com/";
		URLShortener shortener = new URLShortener(originalUrl);
		String shorterUrl = shortener.getShorterUrl();
		Pattern urlPattern = Pattern.compile(REGEX);
		Matcher patternMatcher = urlPattern.matcher(shorterUrl);
		assertTrue(patternMatcher.matches());
	}
	
	@Test
	void testReturnSameShorterUrlIfCalledMoreThanOnce() throws Exception {
		String firstUrl = "https://techcrunch.com/";
		URLShortener firstShortener = new URLShortener(firstUrl);
		String firstShorterUrl = firstShortener.getShorterUrl();
		
		String secondUrl = "https://techcrunch.com/";
		URLShortener secondShortener = new URLShortener(secondUrl);
		assertTrue(firstShorterUrl.equals(secondShortener.getShorterUrl()));
	}
}