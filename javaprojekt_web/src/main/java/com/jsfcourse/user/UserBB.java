package com.jsfcourse.user;

import java.io.IOException;
//import java.io.Serializable;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.UserDAO;
import com.jsf.entities.User;

@Named
@RequestScoped
public class UserBB {
    private static final long serialVersionUID = 1L;

    private static final String PAGE_USER_REGISTER = "/public/register?faces-redirect=true";
    private static final String PAGE_USER_LOGIN = "/pages/login?faces-redirect=true";
    private static final String PAGE_STAY_AT_THE_SAME = null;

    private User user = new User();
    private User loaded = null;
//    private String login;
//    private String password;

    @Inject
    ExternalContext extcontext;

    @Inject
    FacesContext context;

    @Inject
    Flash flash;

    @Inject
    UserDAO userDAO;

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    
    public void onLoad() throws IOException {
    	FacesContext ctx = FacesContext.getCurrentInstance();
    	HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        Integer id = (Integer) session.getAttribute("id");
        String login = (String) session.getAttribute("login");
        String name = (String) session.getAttribute("name");
        String surname = (String) session.getAttribute("surname");
        Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
        
        
        user.setId(id);
        user.setIsAdmin(isAdmin);
        user.setName(name);
        user.setSurname(surname);
        user.setLogin(login);

       
    }

    
        
    }

