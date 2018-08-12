package com.crud.library.controller;

import com.crud.library.domainDTO.UserDTO;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    DbUserService dbUserService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> getUsers() {

        return userMapper.mapToUsersDTO(dbUserService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDTO getUserById(@PathVariable("id") Long idUser)  {

        return userMapper.mapToUserDTO(dbUserService.getUser(idUser));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public void addNewUser(@RequestBody UserDTO userDTO)  {

        dbUserService.saveUser(userMapper.mapToUser(userDTO));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public UserDTO updataUser(@RequestBody UserDTO userDTO) {

        return userMapper.mapToUserDTO(dbUserService.saveUser(userMapper.mapToUser(userDTO)));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable("id") Long idUser) {

        dbUserService.delete(idUser);
    }

}
