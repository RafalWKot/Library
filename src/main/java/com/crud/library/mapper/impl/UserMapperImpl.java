package com.crud.library.mapper.impl;

import com.crud.library.domain.LocalDateTimeConverter;
import com.crud.library.domain.entities.User;
import com.crud.library.domainDto.UserDto;
import com.crud.library.mapper.UserMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {


    private final LocalDateTimeConverter localDateTimeConverter;

    public UserMapperImpl(LocalDateTimeConverter localDateTimeConverter) {
        this.localDateTimeConverter = localDateTimeConverter;
    }

    @Override
    public List<UserDto> mapToUsersDto(List<User> users) {
        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstname(),
                        user.getLastname(),
                        user.getPesel(),
                        localDateTimeConverter.convertToDatabaseColumn(user.getRegistrationDate())))
                .collect(Collectors.toList()
                );
    }

    @Override
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastname(),
                userDto.getPesel(),
                userDto.getUsername(),
                userDto.getPassword(),
                localDateTimeConverter.convertToEntityAttribute(userDto.getRegistrationDate()));
    }

    @Override
    public UserDto mapToUserDto(final User user) {  //service.getValue()
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getPesel(),
                //(kwota)service.getValue()   //kwota zaległości jaka wynika z późnego oddania książek
                localDateTimeConverter.convertToDatabaseColumn(user.getRegistrationDate()));
    }
}
