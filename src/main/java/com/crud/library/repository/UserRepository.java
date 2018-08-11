package com.crud.library.repository;

import com.crud.library.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByFirstnameAndLastname(String firstname, String lastname);

    @Override
    User save(User user);
}
