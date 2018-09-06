package com.crud.library.service.impl;


import com.crud.library.domain.User;
import com.crud.library.exception.UserDuplicateException;
import com.crud.library.exception.UserInvalidInputDataException;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbUserService;
import com.crud.library.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class DbUserServiceImpl implements DbUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserValidation userValidation;

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
    public User getUserByPesel(String pesel) {
        return userRepository.findByPesel(pesel).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getUsersByFirstnameAndLastname(String firstname, String lastname) {
        return userRepository.findByFirstnameAndLastname(firstname, lastname);
    }

    @Override
    public List<User> getUsersByFirstname(String firstname) {
        return userRepository.findByFirstname(firstname);
    }

    @Override
    public List<User> getUsersByLastname(String lastname) {
        return userRepository.findByLastname(lastname);
    }

    @Override
    public User saveUser(User user) throws UserDuplicateException, UserInvalidInputDataException {

        if(userRepository.findByPesel(user.getPesel()).isPresent()) {
            throw new UserDuplicateException();
        }
        if(!userValidation.isValidPesel(user.getPesel())) {
            throw new UserInvalidInputDataException();
        }
        user.setRegistrationDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public void delete(Long userId) {
        userRepository.delete(userId);
    }
}
