package com.crud.library.service.impl;


import com.crud.library.domain.User;
import com.crud.library.exception.UserDuplicateException;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DbUserServiceImpl implements DbUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getUsers() {

        System.out.println(userRepository.findAll().size());
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User saveUser(User user) throws UserDuplicateException {

        if(userRepository.findByFirstnameAndLastname(user.getFirstname(), user.getLastname()).isPresent()) {
            throw new UserDuplicateException();
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}
