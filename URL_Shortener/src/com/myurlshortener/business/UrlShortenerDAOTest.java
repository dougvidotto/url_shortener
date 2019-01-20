package com.myurlshortener.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class UrlShortenerDAOTest {

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("urlshortener");
		EntityManager entityManager = factory.createEntityManager();
		
		URLShortener shortener = new URLShortener("https://techcrunch.com/");
		
		entityManager.getTransaction().begin();
		entityManager.persist(shortener);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("Funcionou!");
	}
}
