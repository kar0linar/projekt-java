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

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "jsfcourse-simplePU";

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
			return null;
		}
	}

	public Integer getId(String login) {
		String sql = "SELECT u.id FROM User u WHERE u.login = :login";
		Query query = em.createQuery(sql);
		query.setParameter("login", login);

		try {
			return (Integer) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<User> getList(Map<String, Object> searchParams) {
		List<User> list = null;

		String select = "select u ";
		String from = "from User u ";
		String where = "";
		String orderby = "order by u.surname";

		String surname = (String) searchParams.get("surname");
		if (surname != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.surname like :surname ";
		}

		Query query = em.createQuery(select + from + where + orderby);

		if (surname != null) {
			query.setParameter("surname", surname + "%");
		}

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

	public User getUserFromDatabase(String login, String password) {

		try {
			Query query = em.createQuery("SELECT u FROM User u WHERE u.login = :login and u.password = :password");
			query.setParameter("login", login);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
