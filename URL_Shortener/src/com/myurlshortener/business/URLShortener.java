package com.myurlshortener.business;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myurlshortener.exceptions.IllegalUrlFormatException;

@Table(name="urls")
@Entity
public class URLShortener {
	private static final String REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String originalUrl;
	private String shorterUrl;
	
	public URLShortener(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShorterUrl() throws IllegalUrlFormatException {
		Matcher matcher = getRegexMatcher();
		if(!matcher.matches()) {
			throw new IllegalUrlFormatException();
		} else {
			return "https://techcrunch.com/";
		}
	}

	private Matcher getRegexMatcher() {
		Pattern urlPattern = Pattern.compile(REGEX);
		Matcher matcher = urlPattern.matcher(getOriginalUrl());
		return matcher;
	}

	public Long getId() {
		return id;
	}
	
	public String getOriginalUrl() {
		return this.originalUrl;
	}
}
