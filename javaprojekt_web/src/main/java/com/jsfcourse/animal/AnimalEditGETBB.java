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
import com.jsf.entities.Animal;

@Named
@ViewScoped
public class AnimalEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ANIMAL_PAGE = "animalPage?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Animal animal = new Animal();
	private Animal loaded = null;

	@Inject
	FacesContext context;

	@EJB
	AnimalDAO animalDAO;

	public Animal getAnimal() {
		return animal;
	}
	
	public void setAnimal(Animal animal) {
		this.animal=animal;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && animal.getAnimalId() != null) {
				loaded = animalDAO.find(animal.getAnimalId());
			}
			if (loaded != null) {
				animal = loaded;
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
			if (animal.getAnimalId() == null) {
				// new record
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
