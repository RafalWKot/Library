package com.crud.library.repository;

import com.crud.library.domain.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByPesel(String pesel);

    List<User> findByFirstnameLikeAndLastnameLikeAndPeselLike(String firstname, String lastname, String pesel);

    Optional<User> findByUsername(String username);

    @SuppressWarnings("ALL")
    @Override
    User save(User user);

    @Override
    void delete(Long userId);

    @Override
    boolean exists(Long aLong);

}
