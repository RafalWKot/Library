package com.crud.library.mapper.impl;

import com.crud.library.domain.LocalDateTimeConverter;
import com.crud.library.domain.entities.User;
import com.crud.library.domainDto.UserDto;
import com.crud.library.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    LocalDateTimeConverter localDateTimeConverter;

    @Override
    public List<UserDto> mapToUsersDto(List<User> users) {
        return users.stream()
                .map(t -> new UserDto(
                        t.getId(),
                        t.getFirstname(),
                        t.getLastname(),
                        t.getPesel(),
                        localDateTimeConverter.convertToDatabaseColumn(t.getRegistrationDate())))
                .collect(Collectors.toList()
                );
    }

    @Override
    public List<User> mapToUsers(List<UserDto> users) {
        return users.stream()
                .map(t -> new User(
                        t.getId(),
                        t.getFirstname(),
                        t.getLastname(),
                        t.getPesel(),
                        localDateTimeConverter.convertToEntityAttribute(t.getRegistrationDate())))
                .collect(Collectors.toList());
    }

    @Override
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getPesel(),
                localDateTimeConverter.convertToEntityAttribute(userDto.getRegistrationDate()));
    }

    @Override
    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                localDateTimeConverter.convertToDatabaseColumn(user.getRegistrationDate()));
    }
}
