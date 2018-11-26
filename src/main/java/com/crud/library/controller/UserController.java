package com.crud.library.controller;

import com.crud.library.domain.entities.User;
import com.crud.library.domainDto.UserDto;
import com.crud.library.mapper.UserMapper;
import com.crud.library.service.DbUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/users")
public class UserController {

    private final DbUserService dbUserService;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(DbUserService dbUserService, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.dbUserService = dbUserService;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Value("${my.server.address}")
    private String serverAddress;

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getUsers() {
        return userMapper.mapToUsersDto(dbUserService.getUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDto getUserById(@PathVariable("id") Long idUser)  {
        return userMapper.mapToUserDto(dbUserService.getUser(idUser));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/searchedUsers")
    public List<UserDto> getUserBySearch(@RequestParam(required = false, defaultValue = "%") String firstname,
                                                 @RequestParam(required = false, defaultValue = "%") String lastname,
                                                 @RequestParam(required = false, defaultValue = "%") String pesel) {
        User searchedUser = new User(firstname, lastname, pesel);
        return userMapper.mapToUsersDto(dbUserService.getSearchedUser(searchedUser));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addUser(@RequestBody UserDto userDto)  {
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        User user = dbUserService.saveUser(userMapper.mapToUser(userDto));
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        UriComponents uriComponents = uriComponentsBuilder.path(serverAddress + "/v1/users/{id}").buildAndExpand(user.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation((uriComponents.toUri()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public void updateUser(@RequestBody UserDto userDto) {
        dbUserService.updateUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteUser(@PathVariable("id") Long idUser) {
        dbUserService.deleteUser(idUser);
    }
}
