package com.jsf.dao;

import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.Specy;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class SpecyDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(Specy specy) {
		em.persist(specy);
	}

	public Specy merge(Specy specy) {
		return em.merge(specy);
	}

	public void remove(Specy specy) {
		em.remove(em.merge(specy));
	}

	public Specy find(Object species_id) {
		return em.find(Specy.class, species_id);
	}

	public List<Specy> getFullList() {
		List<Specy> list = null;

		Query query = em.createQuery("select s from Specy s");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Specy> getList(Map<String, Object> searchParams) {
		List<Specy> list = null;

		// 1. Build query string with parameters
		String select = "select s ";
		String from = "from Specy s ";
		String where = "";
		String orderby = "";

		// search for surid
		String species_name = (String) searchParams.get("species_name");
		if (species_name != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "s.species_name like :species_name ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (species_name != null) {
			query.setParameter("species_name", species_name+"%");
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

