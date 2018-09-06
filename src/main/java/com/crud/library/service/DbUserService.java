package com.crud.library.service;

import com.crud.library.domain.User;

import java.util.List;

public interface DbUserService {

    List<User> getUsers();

    User getUser(Long userId);

    User getUserByPesel(String pesel);

    List<User> getUsersByFirstnameAndLastname(String firstname, String lastname);

    List<User> getUsersByFirstname(String firstname);

    List<User> getUsersByLastname(String lastname);

    User saveUser(User user);

    void delete(Long userId);
}
