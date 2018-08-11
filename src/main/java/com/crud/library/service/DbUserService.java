package com.crud.library.service;

import com.crud.library.domain.User;

import java.util.List;

public interface DbUserService {

    List<User> getUsers();

    User getUser(Long userId);

    User saveUser(User user);
}
