package com.crud.library.service;

import com.crud.library.domain.entities.User;

import java.util.List;

public interface DbUserService {

    List<User> getUsers();

    User getUser(Long userId);

    List<User> getSearchedUser(User user);

    User saveUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);
}
