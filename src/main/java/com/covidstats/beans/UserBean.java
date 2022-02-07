package com.covidstats.beans;

import com.covidstats.controller.UserController;
import com.covidstats.model.User;
import com.covidstats.utils.GrowlMessage;
import com.covidstats.utils.Routes;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
    private User currentUser;
    private String email;
    private String password;
    private String name;
    private String surname;


    public UserBean() {
        clearAttributes();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean hasCurrentUser() {
        return currentUser != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private void clearAttributes() {
        currentUser = null;
        email = "";
        password = "";
        name = "";
        surname = "";
    }

    public String login() {
        try {
            currentUser = UserController.getInstance().authenticateUser(email, password);
            new GrowlMessage().showInfo("Logged in successfully!");
            return Routes.HOME.getUrl();
        } catch (NoResultException e) {
            new GrowlMessage().showError("Invalid email or password!");
            return null;
        }
    }

    //TODO bug
    public String logout() {
//        clearAttributes();
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        new GrowlMessage().showInfo("Logged out successfully!");
        return Routes.HOME.getUrl();
    }

    public String updateProfile() {
        try {
            UserController.getInstance().update(getCurrentUser());
            new GrowlMessage().showInfo("User Profile Updated!");
            return Routes.HOME.getUrl();
        } catch (NoResultException e) {
            return null;
        }
    }

}
