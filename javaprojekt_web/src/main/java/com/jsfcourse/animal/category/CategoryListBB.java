package com.jsfcourse.animal.category;

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

import com.jsf.dao.CategoryDAO;
import com.jsf.entities.Category;

@Named
@RequestScoped
public class CategoryListBB {
	private static final String PAGE_CATEGORY_EDIT = "/pages/edit/categoryEdit?faces-redirect=true";
	private static final String PAGE_CATEGORY_DETAILS = "/pages/edit/categoryDetails?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String title;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	CategoryDAO categoryDAO;
	
		
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Category> getFullList(){
		return categoryDAO.getFullList();
	}

	public List<Category> getList(){
		List<Category> list = null;
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
//		if (title != null && title.length() > 0){
//			searchParams.put("title", title);
//		}
		
		//2. Get list
		list = categoryDAO.getList(searchParams);
		
		return list;
	}

	public String newCategory(){
		Category category = new Category();
		
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash	
		flash.put("category", category);
		
		return PAGE_CATEGORY_EDIT;
	}

	public String editCategory(Category category){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("category", category);
		
		return PAGE_CATEGORY_EDIT;
	}
	
	public String showCategory(Category category){
		//1. Pass object through session
		//HttpSession session = (HttpSession) extcontext.getSession(true);
		//session.setAttribute("person", person);
		
		//2. Pass object through flash 
		flash.put("category", category);
		
		return PAGE_CATEGORY_DETAILS;
	}

	public String deleteCategory(Category category){
		categoryDAO.remove(category);
		return PAGE_STAY_AT_THE_SAME;
	}
	
	
}
