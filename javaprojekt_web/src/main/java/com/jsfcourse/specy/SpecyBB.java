package com.jsfcourse.specy;

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

import com.jsf.dao.SpecyDAO;
import com.jsf.entities.Specy;

@Named
@RequestScoped
public class SpecyBB {
	private static final String PAGE_SPECY_EDIT = "/pages/edit/specyEdit?faces-redirect=true";
	private static final String PAGE_SPECY_DETAILS = "/pages/edit/specyDetails?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
	private List<Specy> list = null;

	@Inject
	ExternalContext extcontext;

	@Inject
	Flash flash;

	@EJB
	SpecyDAO specyDAO;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Specy> getFullList() {
		return specyDAO.getFullList();
	}

	public List<Specy> getList() {

		Map<String, Object> searchParams = new HashMap<String, Object>();

		list = specyDAO.getList(searchParams);
		return list;
	}

	public String newSpecy() {
		Specy specy = new Specy();

		flash.put("specy", specy);

		return PAGE_SPECY_EDIT;
	}

	public String editSpecy(Specy specy) {

		flash.put("specy", specy);

		return PAGE_SPECY_EDIT;
	}

	public String showSpecy(Specy specy) {

		flash.put("specy", specy);

		return PAGE_SPECY_DETAILS;
	}

	public String deleteSpecy(Specy specy) {
		specyDAO.remove(specy);
		return PAGE_STAY_AT_THE_SAME;
	}

}
