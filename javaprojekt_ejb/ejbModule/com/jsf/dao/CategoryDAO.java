package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Category;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class CategoryDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
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

		// 1. Build query string with parameters
		String select = "select c ";
		String from = "from Category c ";
		String where = "";
		String orderby = "order by c.category_id";

		// search for surid
		String category_id = (String) searchParams.get("category_id");
		if (category_id != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "c.category_id like :category_id ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (category_id != null) {
			query.setParameter("category_id", category_id+"%");
		}

		// ... other parameters ... 

		// 4. Execute query and retrieve list of Person objects
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	

}

