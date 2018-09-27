package com.crud.library.mapper;

import com.crud.library.domain.dao.User;
import com.crud.library.domainDTO.CreateUserDto;
import com.crud.library.domainDTO.UpdateUserDto;
import com.crud.library.domainDTO.UserResponseDto;

import java.util.List;

public interface UserMapper {

    User mapToUser(final CreateUserDto createUserDto);

    List<UserResponseDto> mapToUsersResponseDto(final List<User> users);

    List<User> mapToUsers(final List<UserResponseDto> users);

    User mapToUser(final UserResponseDto userResponseDto);

    UserResponseDto mapToUserResponseDto(final User user);

    User mapToUser(final UpdateUserDto updateUserDto);
}
