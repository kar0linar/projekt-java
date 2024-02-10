package com.jsfcourse.animal;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.AnimalDAO;
import com.jsf.entities.Animal;

@Named
@RequestScoped
public class AnimalListBB {
	private static final String PAGE_ANIMAL_EDIT = "/pages/edit/animalEdit?faces-redirect=true";
	private static final String PAGE_ANIMAL_DETAILS = "/pages/edit/animalDetails?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String animal_name;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	AnimalDAO animalDAO;

	public String getAnimalName() {
		return animal_name;
	}

	public void setAnimalName(String animl_name) {
		this.animal_name = animl_name;
	}

	public List<Animal> getFullList() {
		return animalDAO.getFullList();
	}

	public List<Animal> getList() {
		List<Animal> list = null;

		// 1. Prepare search params
		Map<String, Object> searchParams = new HashMap<String, Object>();

		if (animal_name != null && animal_name.length() > 0) {
			searchParams.put("animalName", animal_name);
		}

		// 2. Get list
		list = animalDAO.getList(searchParams);

		return list;
	}

	public String newAnimal() {
		Animal animal = new Animal();

		// 2. Pass object through flash
		flash.put("animal", animal);

		return PAGE_ANIMAL_EDIT;
	}

	public String editAnimal(Animal animal) {

		// 2. Pass object through flash
		flash.put("animal", animal);

		return PAGE_ANIMAL_EDIT;
	}

	public String showAnimal(Animal animal) {

		// 2. Pass object through flash
		flash.put("animal", animal);

		return PAGE_ANIMAL_DETAILS;
	}

	public String deleteAnimal(Animal animal) {
		animalDAO.remove(animal);
		return PAGE_STAY_AT_THE_SAME;
	}

}
