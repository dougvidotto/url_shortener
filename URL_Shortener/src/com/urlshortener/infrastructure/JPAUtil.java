package com.urlshortener.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("urlshortener");
	
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
