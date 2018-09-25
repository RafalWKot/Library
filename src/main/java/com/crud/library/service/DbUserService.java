package com.crud.library.service;

import com.crud.library.domain.User;

import java.util.List;

public interface DbUserService {

    List<User> getUsers();

    User getUser(Long userId);

    User getUserByPesel(String pesel);

    List<User> getSearchedUser(User user);

    User saveUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);
}
