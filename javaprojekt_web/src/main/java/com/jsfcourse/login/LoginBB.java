package com.jsfcourse.login;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.simplesecurity.RemoteClient;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "/public/mainPage?faces-redirect=true";
	private static final String PAGE_LOGIN = "/pages/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String login;
	private String password;

	@Inject
    ExternalContext extcontext;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    @Inject
    UserDAO userDAO;
    
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String pass) {
		this.password = pass;
	}
	
	
	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		// 1. verify login and password - get User from "database"
		User user = userDAO.getUserFromDatabase(login, password);

		// 2. if bad login or password - stay with error info
		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"niepoprawny login lub hasło!", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		// 3. if logged in: get User roles, save in RemoteClient and store it in session
		
		RemoteClient<User> client = new RemoteClient<User>(); //create new RemoteClient
		client.setDetails(user);
		
		//store RemoteClient with request info in session (needed for SecurityFilter)
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
		
		HttpSession session = (HttpSession) ctx.getExternalContext().getSession(true);
		session.setAttribute("id", user.getId());
		session.setAttribute("login", user.getLogin());
		session.setAttribute("name", user.getName());
		session.setAttribute("surname", user.getSurname());
		session.setAttribute("isAdmin", user.getIsAdmin());
		// and enter the system (now SecurityFilter will pass the request)
		return PAGE_MAIN;
	}
	
	public String doLogout(){
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
//		Invalidate session
//		 - all objects within session will be destroyed
//		 - new session will be created (with new ID)
		if(session != null){
			System.out.println((String) session.getAttribute("name"));
			session.invalidate();
		}
		return PAGE_LOGIN;
	}
	
}
