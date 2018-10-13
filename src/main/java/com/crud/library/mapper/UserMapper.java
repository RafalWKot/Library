package com.crud.library.mapper;

import com.crud.library.domain.entities.User;
import com.crud.library.domainDto.UserDto;

import java.util.List;

public interface UserMapper {

    List<UserDto> mapToUsersDto(final List<User> users);

    UserDto mapToUserDto(final User user);

    User mapToUser(final UserDto userDto);
}
