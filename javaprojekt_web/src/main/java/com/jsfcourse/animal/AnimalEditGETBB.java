package com.jsfcourse.animal;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import com.jsf.dao.AnimalDAO;
import com.jsf.dao.CategoryDAO;
import com.jsf.dao.SpecyDAO;
import com.jsf.dao.UserDAO;
import com.jsf.entities.Animal;
import com.jsf.entities.Category;
import com.jsf.entities.Specy;
import com.jsf.entities.User;

@Named
@ViewScoped
public class AnimalEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ANIMAL_PAGE = "/public/animalPage?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Animal animal = new Animal();
	private Animal loaded = null;
	
	private String userId;
	private String categoryId;
	private String speciesId;
	
	@Inject
	FacesContext context;

	@EJB
	AnimalDAO animalDAO;
	
	@EJB
	private CategoryDAO categoryDAO;
	
	@EJB
	private UserDAO userDAO;
	
	@EJB
	private SpecyDAO specyDAO;

	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal animal) {
		this.animal=animal;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId=userId;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public void setCategoryId(String categoryId) {
		this.categoryId=categoryId;
	}
	
	public String getSpeciesId() {
		return speciesId;
	}
	
	public void setSpeciesId(String speciesId) {
		this.speciesId=speciesId;
	}
	

	public void onLoad() throws IOException {		
		if (!context.isPostback()) {
			
			if (animal.getAnimalId() != null) {
				
				loaded = animalDAO.find(animal.getAnimalId());
				userId=loaded.getUser().getId().toString();
				categoryId=loaded.getCategory().getCategoryId().toString();
				speciesId=loaded.getSpecy().getSpeciesId().toString();
				
			}
			if (loaded != null) {
				animal = loaded;
				
			
				userId=animal.getUser().getId().toString();
				categoryId=animal.getCategory().getCategoryId().toString();
				speciesId=animal.getSpecy().getSpeciesId().toString();
			
			} else {
//				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
//				 if (!context.isPostback()) { // possible redirect
//				 context.getExternalContext().redirect("personList.xhtml");
//				 context.responseComplete();
//				 }
			}
		}

	}

	public String saveData() {
		
		// no Person object passed
		if (animal == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		
		
		try {
			
			User user = userDAO.find(Integer.parseInt(userId));
			Category category = categoryDAO.find(Integer.parseInt(categoryId));
			Specy specy = specyDAO.find(Integer.parseInt(speciesId));
			
			animal.setUser(user);
			animal.setCategory(category);
			animal.setSpecy(specy);
			
			System.out.println("test");
			if (animal.getAnimalId() == null) {
				// new record
				System.out.println("test2");
				animalDAO.create(animal);
			} else {
				// existing record
				animalDAO.merge(animal);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ANIMAL_PAGE;
	}
}
