package com.design.bookmyshow.controllers;


import com.design.bookmyshow.dtos.CreateUserRequestDto;
import com.design.bookmyshow.dtos.CreateUserResponseDto;
import com.design.bookmyshow.models.User;
import com.design.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
//    @Qualifier("mango")
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        System.out.printf("USER SERVICE IS BEING CALLED");
        this.userService = userService;
    }

    public CreateUserResponseDto createUser(CreateUserRequestDto request) {
        User savedUser = userService.createUser(
                request.getEmail()
        );

        CreateUserResponseDto response = new CreateUserResponseDto();
        response.setUser(savedUser);

        return response;
    }
}
