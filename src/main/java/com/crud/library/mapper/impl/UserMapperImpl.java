package com.crud.library.mapper.impl;

import com.crud.library.domain.User;
import com.crud.library.domainDTO.UserDTO;
import com.crud.library.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public List<UserDTO> mapToUsersDTO(List<User> users) {
        return users.stream()
                .map(t -> new UserDTO(
                        t.getId(),
                        t.getFirstname(),
                        t.getLastname(),
                        t.getRegistrationDate(),
                        t.getBookLoans()))
                .collect(Collectors.toList()
                );
    }

    @Override
    public User mapToUser(final UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getFirstname(),
                userDTO.getLastname(),
                userDTO.getRegistrationDate(),
                userDTO.getBookLoans()
        );
    }

    @Override
    public UserDTO mapToUserDTO(final User user) {
        return new UserDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getRegistrationDate(),
                user.getBookLoans()
        );
    }
}