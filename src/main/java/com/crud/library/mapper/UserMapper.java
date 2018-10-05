package com.crud.library.mapper;

import com.crud.library.domain.entities.User;
import com.crud.library.domainDTO.UserDto;

import java.util.List;

public interface UserMapper {

    List<UserDto> mapToUsersDto(final List<User> users);

    List<User> mapToUsers(final List<UserDto> users);

    UserDto mapToUserDto(final User user);

    User mapToUser(final UserDto userDto);
}
