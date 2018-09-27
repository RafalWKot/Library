package com.crud.library.repository;

import com.crud.library.domain.dao.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByPesel(String pesel);

    List<User> findByFirstnameLikeAndAndLastnameLikeAndAndPeselLike(String firstname, String lastname, String pesel);

    @Override
    User save(User user);

    @Override
    void delete(Long userId);

    @Override
    boolean exists(Long aLong);
}
