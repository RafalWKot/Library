package com.crud.library.controller;

import com.crud.library.domain.dao.User;
import com.crud.library.domainDTO.CreateUserDto;
import com.crud.library.domainDTO.UpdateUserDto;
import com.crud.library.domainDTO.UserResponseDto;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    @Autowired
    DbUserService dbUserService;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserResponseDto> getUsers() {
        return userMapper.mapToUsersResponseDto(dbUserService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserResponseDto getUserById(@PathVariable("id") Long idUser)  {
        return userMapper.mapToUserResponseDto(dbUserService.getUser(idUser));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchedUsers")
    public List<UserResponseDto> getUserBySearch(@RequestParam(required = false, defaultValue = "%") String firstname,
                                                 @RequestParam(required = false, defaultValue = "%") String lastname,
                                                 @RequestParam(required = false, defaultValue = "%") String pesel) {
        User searchedUser = new User(firstname, lastname, pesel);
        return userMapper.mapToUsersResponseDto(dbUserService.getSearchedUser(searchedUser));
    }


    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@RequestBody CreateUserDto createUserDto, UriComponentsBuilder uriComponentsBuilder)  {
        User user = dbUserService.saveUser(userMapper.mapToUser(createUserDto));
        UriComponents uriComponents = uriComponentsBuilder.path("/v1/users/{id}").buildAndExpand(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation((uriComponents.toUri()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody UpdateUserDto updateUserDto) {
        dbUserService.updateUser(userMapper.mapToUser(updateUserDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable("id") Long idUser) {
        dbUserService.deleteUser(idUser);
    }
}
