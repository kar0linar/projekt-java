package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Animal;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class AnimalDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Animal animal) {
		em.persist(animal);
	}

	public Animal merge(Animal animal) {
		return em.merge(animal);
	}

	public void remove(Animal animal) {
		em.remove(em.merge(animal));
	}

	public Animal find(Object id) {
		return em.find(Animal.class, id);
	}

	public List<Animal> getFullList() {
		List<Animal> list = null;

		Query query = em.createQuery("select a from Animal a");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Animal> getList(Map<String, Object> searchParams) {
		List<Animal> list = null;

		// 1. Build query string with parameters
		String select = "select a ";
		String from = "from Animal a ";
		String where = "";
		String orderby = "order by a.name";

		// search for surname
		String name = (String) searchParams.get("name");
		if (name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "a.name like :name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (name != null) {
			query.setParameter("name", name+"%");
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