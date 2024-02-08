package com.jsf.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import com.jsf.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public void create(User user) {
		em.persist(user);
	}

	public User merge(User user) {
		return em.merge(user);
	}

	public void remove(User user) {
		em.remove(em.merge(user));
	}

	public User find(Object id) {
		return em.find(User.class, id);
	}

	public List<User> getFullList() {
		List<User> list = null;

		Query query = em.createQuery("select u from User u");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	 public String getPermission(String login) {
	        String sql = "SELECT u.permission FROM User u WHERE u.login = :login";
	        Query query = em.createQuery(sql);
	        query.setParameter("login", login);
	        
	        try {
	            return (String) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null; // handle situation when no result is found for given login
	        }
	    }
	    
	    public Integer getId(String login) {
	        String sql = "SELECT u.id FROM User u WHERE u.login = :login";
	        Query query = em.createQuery(sql);
	        query.setParameter("login", login);
	        
	        try {
	            return (Integer) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null; // handle situation when no result is found for given login
	        }
	    }
	
	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		// 1. Build query string with parameters
		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "";

		// search for surid
		String id = (String) searchParams.get("id");
		if (id != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.id like :id ";
		}
		
		// ... other parameters ... 

		// 2. Create query object
		Query query = em.createQuery(select + from + where + orderby);

		// 3. Set configured parameters
		if (id != null) {
			query.setParameter("id", id+"%");
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
	
	 public User findUserByLogin(String login) {
	        try {
	            Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login");
	            query.setParameter("login", login);
	            return (User) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
	  public User getUserFromDatabase(String login, String pass) {

	        User u = null;

	        if (login.equals("user") && pass.equals("user")) {
	            u = new User();
	            u.setLogin(login);
	            u.setPassword(pass);
	        }

	        if (login.equals("manager") && pass.equals("manager")) {
	            u = new User();
	            u.setLogin(login);
	            u.setPassword(pass);
	        }

	        if (login.equals("admin") && pass.equals("admin")) {
	            u = new User();
	            u.setLogin(login);
	            u.setPassword(pass);
	        }

	        return u;
	    }

	    // simulate retrieving roles of a User from DB
	    public List<String> getUserRolesFromDatabase(User user) {

	        ArrayList<String> roles = new ArrayList<String>();

	        if (user.getLogin().equals("user")) {
	            roles.add("user");
	        }
	        if (user.getLogin().equals("manager")) {
	            roles.add("manager");
	        }
	        if (user.getLogin().equals("admin")) {
	            roles.add("admin");
	        }

	        return roles;
	    }
	}


