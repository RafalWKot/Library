package com.crud.library.mapper.impl;

import com.crud.library.domain.dao.User;
import com.crud.library.domainDTO.CreateUserDto;
import com.crud.library.domainDTO.UpdateUserDto;
import com.crud.library.domainDTO.UserResponseDto;
import com.crud.library.mapper.DateUtils;
import com.crud.library.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToUser(CreateUserDto createUserDto) {
        return new User(
                createUserDto.getFirstname(),
                createUserDto.getLastname(),
                createUserDto.getPesel());
    }

    @Override
    public List<UserResponseDto> mapToUsersResponseDto(List<User> users) {
        return users.stream()
                .map(t -> new UserResponseDto(
                        t.getId(),
                        t.getFirstname(),
                        t.getLastname(),
                        t.getPesel(),
                        DateUtils.asDate(t.getRegistrationDate())))                   //Tutaj chyba należy użyć mapera od książek
                .collect(Collectors.toList()
                );
    }

    @Override
    public List<User> mapToUsers(List<UserResponseDto> users) {
        return users.stream()
                .map(t -> new User(
                        t.getId(),
                        t.getFirstname(),
                        t.getLastname(),
                        t.getPesel(),
                        DateUtils.asLocalDate(t.getRegistrationDate())))                  //Tutaj chyba należy użyć mapera od książek
                .collect(Collectors.toList());
    }

    @Override
    public User mapToUser(final UserResponseDto userResponseDto) {


        return new User(
                userResponseDto.getId(),
                userResponseDto.getFirstname(),
                userResponseDto.getLastname(),
                userResponseDto.getPesel(),
                DateUtils.asLocalDate(userResponseDto.getRegistrationDate()));
    }

    @Override
    public UserResponseDto mapToUserResponseDto(final User user) {
        return new UserResponseDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                DateUtils.asDate(user.getRegistrationDate()));
    }

    @Override
    public User mapToUser(UpdateUserDto updateUserDto) {
        return new User(
                updateUserDto.getId(),
                updateUserDto.getFirstname(),
                updateUserDto.getLastname(),
                updateUserDto.getPesel()
        );
    }
}
