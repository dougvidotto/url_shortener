package com.myurlshortener.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.myurlshortener.exceptions.IllegalURLFormatException;

@Table(name="url_tuple")
@Entity
public class URLTuple {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String originalURL;
	private String shorterURL;
	
	public URLTuple(String originalUrl, String shorterURL) {
		this.originalURL = originalUrl;
		this.shorterURL = shorterURL;
	}

	public String getShorterUrl() throws IllegalURLFormatException {
		return this.shorterURL;
	}

	public Long getId() {
		return id;
	}
	
	public String getOriginalUrl() {
		return this.originalURL;
	}
}
