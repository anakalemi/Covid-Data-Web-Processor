package com.covidstats.controller;

import com.covidstats.dao.DAOFactory;
import com.covidstats.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserController implements IController<User> {

    private UserController() {
    }

    private static class LazyHolder {
        static final UserController INSTANCE = new UserController();
    }

    public static UserController getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public ArrayList<User> index() {
        return convertToArrayList(DAOFactory.getUserDAO().index());

    }

    @Override
    public User show(Object key) {
        if (!(key instanceof String)) {
            throw new IllegalArgumentException("Wrong key type");
        }
        return DAOFactory.getUserDAO().show(key);
    }

    @Override
    public void store(User user) {
        DAOFactory.getUserDAO().store(user);
    }

    @Override
    public void update(User user) {
        DAOFactory.getUserDAO().update(user);
    }

    @Override
    public void destroy(User user) {
        DAOFactory.getUserDAO().destroy(user);
    }

    public User authenticateUser(String email, String password) {
        return DAOFactory.getUserDAO().authenticateUser(email, password);
    }

    public <T> ArrayList<User> convertToArrayList(List<T> users) {
        return users.stream()
                .filter(object -> object instanceof User)
                .map(object -> (User) object)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
