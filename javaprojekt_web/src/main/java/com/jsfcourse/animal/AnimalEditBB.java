package com.jsfcourse.animal;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.AnimalDAO;
import com.jsf.entities.Animal;

@Named
@ViewScoped
public class AnimalEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ANIMAL_EDIT = "animalList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Animal animal = new Animal();
	private Animal loaded = null;

	@EJB
	AnimalDAO animalDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Animal getAnimal() {
		return animal;
	}

	public void onLoad() throws IOException {
		// 1. load person passed through session
		// HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		// loaded = (Person) session.getAttribute("person");

		// 2. load person passed through flash
		loaded = (Animal) flash.get("animal");

		// cleaning: attribute received => delete it from session
		if (loaded != null) {
			animal = loaded;
			// session.removeAttribute("person");
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			// if (!context.isPostback()) { //possible redirect
			// context.getExternalContext().redirect("personList.xhtml");
			// context.responseComplete();
			// }
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

		return PAGE_ANIMAL_EDIT;
	}
}
