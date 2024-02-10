package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Category;

@Stateless
public class CategoryDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Category category) {
		em.persist(category);
	}

	public Category merge(Category category) {
		return em.merge(category);
	}

	public void remove(Category category) {
		em.remove(em.merge(category));
	}

	public Category find(Object category_id) {
		return em.find(Category.class, category_id);
	}

	public List<Category> getFullList() {
		List<Category> list = null;

		Query query = em.createQuery("select c from Category c");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Category> getList(Map<String, Object> searchParams) {
		List<Category> list = null;

		String select = "select c ";
		String from = "from Category c ";
		String where = "";
		String orderby = "";

		String category_name = (String) searchParams.get("category_name");
		if (category_name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.category_name like :category_name ";
		}

		Query query = em.createQuery(select + from + where + orderby);

		if (category_name != null) {
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
