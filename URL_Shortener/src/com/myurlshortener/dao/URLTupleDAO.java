package com.myurlshortener.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.myurlshortener.business.URLTuple;
import com.urlshortener.infrastructure.JPAUtil;

public class URLTupleDAO {
	private EntityManager em;
	
	public URLTupleDAO() {
		em = new JPAUtil().getEntityManager();
	}

	public void save(URLTuple shortener) {
		em.getTransaction().begin();
		this.em.persist(shortener);
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public List<URLTuple> searchTupleByShorterURL(String shorterURL) {
		Query query = em.createQuery("select new URLTuple(u.originalURL, u.shorterURL) from URLTuple u where u.shorterURL = :shorterURL");
		query.setParameter("shorterURL", shorterURL);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<URLTuple> searchTupleByOriginalURL(String originalURL) {
		Query query = em.createQuery("select new URLTuple(u.originalURL, u.shorterURL) from URLTuple u where u.originalURL = :originalURL");
		query.setParameter("originalURL", originalURL);
		return query.getResultList();
	}
	
	public Long getMaxId() {
		TypedQuery<Long> query = em.createQuery("select max(u.id) from URLTuple u", Long.class);
		Long result = query.getSingleResult();
		if(result == null) {
			return 0l;
		}
		return result;
	}
	
	public void closeDatabaseSession() {
		em.close();
	}
}
