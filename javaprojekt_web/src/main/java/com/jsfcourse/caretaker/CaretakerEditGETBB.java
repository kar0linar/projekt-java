package com.jsfcourse.caretaker;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.UserDAO;
import com.jsf.dao.CategoryDAO;
import com.jsf.dao.SpecyDAO;
import com.jsf.dao.UserDAO;
import com.jsf.entities.User;
import com.jsf.entities.Category;
import com.jsf.entities.Specy;
import com.jsf.entities.User;

@Named
@ViewScoped
public class CaretakerEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_CARETAKER_PAGE = "/public/caretakerPage?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private User user = new User();
	private User loaded = null;
	
	private String isAdmin;
	
//	private String userId;
//	private String categoryId;
//	private String speciesId;
	
	@Inject
	FacesContext context;

	@EJB
	UserDAO userDAO;
	
//	@EJB
//	private CategoryDAO categoryDAO;
//	
//	@EJB
//	private UserDAO userDAO;
//	
//	@EJB
//	private SpecyDAO specyDAO;

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user=user;
	}
	
	
	
//	public String getUserIsAdmin) {
//		return userId;
//	}
//	
//	public void setUserId(String userId) {
//		this.userId=userId;
//	}
//	
//	public String getCategoryId() {
//		return categoryId;
//	}
//	
//	public void setCategoryId(String categoryId) {
//		this.categoryId=categoryId;
//	}
//	
//	public String getSpeciesId() {
//		return speciesId;
//	}
//	
//	public void setSpeciesId(String speciesId) {
//		this.speciesId=speciesId;
//	}
	

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			
			if ( user.getId() != null) {
				System.out.println(user);			
				loaded = userDAO.find(user.getId());
//				userId=loaded.getUser().getId().toString();
//				categoryId=loaded.getCategory().getCategoryId().toString();
//				speciesId=loaded.getSpecy().getSpeciesId().toString();
				
			}
			if (loaded != null) {
				user = loaded;
//				userId=user.getUser().getId().toString();
//				categoryId=user.getCategory().getCategoryId().toString();
//				speciesId=user.getSpecy().getSpeciesId().toString();
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
//				 if (!context.isPostback()) { // possible redirect
//				 context.getExternalContext().redirect("personList.xhtml");
//				 context.responseComplete();
//				 }
			}
		}

	}

	public String saveData() {
		
		// no Person object passed
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		
		
		try {
			
//			User user = userDAO.find(Integer.parseInt(userId));
//			Category category = categoryDAO.find(Integer.parseInt(categoryId));
//			Specy specy = specyDAO.find(Integer.parseInt(speciesId));
//			
//			user.setUser(user);
//			user.setCategory(category);
//			user.setSpecy(specy);
			
			
			if (user.getId() == null) {
				// new record
				userDAO.create(user);
			} else {
				// existing record
				System.out.println(user.getIsAdmin());
				userDAO.merge(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_CARETAKER_PAGE;
	}
}
