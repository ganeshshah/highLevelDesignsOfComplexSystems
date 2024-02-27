package com.design.bookmyshow.dtos;


import com.design.bookmyshow.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserResponseDto {
    private User user;
}
