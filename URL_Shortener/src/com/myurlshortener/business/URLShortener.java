package com.myurlshortener.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.myurlshortener.dao.URLTupleDAO;
import com.myurlshortener.exceptions.IllegalURLFormatException;

public class URLShortener {
	
	private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
	
	private static final char URL_SHORTENER_DIGITS [] =
			{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
			 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 
			 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
			 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			 '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public String createShorterURL(String originalURL) throws IllegalURLFormatException {
		URLTupleDAO urlDAO = new URLTupleDAO();
		checkOriginalURLPattern(originalURL);
		
		List<URLTuple> urlTupleList = urlDAO.searchTupleByOriginalURL(originalURL);
		if(!urlTupleList.isEmpty()) {
			return urlTupleList.get(0).getShorterUrl();
		}
		
		String shorterURL = extractShorterURL(urlDAO);
		
		URLTuple newTuple = new URLTuple(originalURL, shorterURL);
		urlDAO.save(newTuple);
		urlDAO.closeDatabaseSession();
		return shorterURL;
	}

	private String extractShorterURL(URLTupleDAO urlDAO) {
		Long nextId = urlDAO.getMaxId() + 1;
		List<Integer> digits = new ArrayList<>();
		
		while(nextId > 0) {
			Integer remainder = Long.valueOf(nextId % 62).intValue();
			digits.add(remainder);
			nextId = nextId / 62;
		}
		
		Collections.reverse(digits);
		String shorterURL = "http://smal.ler/";
		for (Integer digit : digits) {
			shorterURL += URL_SHORTENER_DIGITS[digit];
		}
		return shorterURL;
	}
	
	public String getOriginalURL(String shorterURL) {
		URLTupleDAO urlDAO = new URLTupleDAO();
		List<URLTuple> tupleURLList = urlDAO.searchTupleByShorterURL(shorterURL);
		if(tupleURLList.isEmpty()) {
			return "";
		}
		
		return tupleURLList.get(0).getOriginalUrl();
	}
	
	private void checkOriginalURLPattern(String originalURL) throws IllegalURLFormatException {
		Pattern urlPattern = Pattern.compile(URL_REGEX);
		Matcher matcher = urlPattern.matcher(originalURL);
		if(!matcher.matches()) {
			throw new IllegalURLFormatException();
		}
	}
}
