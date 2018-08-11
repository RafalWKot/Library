package com.crud.library.mapper;

import com.crud.library.domain.User;
import com.crud.library.domainDTO.UserDTO;

import java.util.List;

public interface UserMapper {

    List<UserDTO> mapToUsersDTO(final List<User> users);

    User mapToUser(final UserDTO userDTO);

    UserDTO mapToUserDTO(final User user);
}
