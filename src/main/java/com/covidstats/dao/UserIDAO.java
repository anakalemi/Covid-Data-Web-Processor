package com.covidstats.dao;

import com.covidstats.model.User;

public interface UserIDAO extends IDAO<User>{

    User authenticateUser(String email, String password);

}
