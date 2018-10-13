package com.crud.library.service.impl;

import com.crud.library.domain.entities.User;
import com.crud.library.exception.UserDuplicateException;
import com.crud.library.exception.UserInvalidInputDataException;
import com.crud.library.exception.UserNotFoundException;
import com.crud.library.repository.UserRepository;
import com.crud.library.service.DbUserService;
import com.crud.library.validation.UserValidation;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DbUserServiceImpl implements DbUserService {

    private final UserRepository userRepository;

    private final UserValidation userValidation;

    public DbUserServiceImpl(UserRepository userRepository, UserValidation userValidation) {
        this.userRepository = userRepository;
        this.userValidation = userValidation;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getSearchedUser(User user) {
        return userRepository.findByFirstnameLikeAndLastnameLikeAndPeselLike(user.getFirstname(), user.getLastname(), user.getPesel());
    }

    @Override
    public User saveUser(User user) throws UserDuplicateException, UserInvalidInputDataException {
        if (userRepository.findByPesel(user.getPesel()).isPresent()) {
            throw new UserDuplicateException();
        }
        if (!userValidation.isValidPesel(user.getPesel())) {
            throw new UserInvalidInputDataException();
        }
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional.ofNullable(userRepository.findById(userId)).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userId);
    }

    @Override
    public void updateUser(User user) {
        Optional.ofNullable(userRepository.findById(user.getId())).orElseThrow(UserNotFoundException::new);
        userRepository.save(user);
    }
}
