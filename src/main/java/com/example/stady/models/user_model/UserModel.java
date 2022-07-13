package com.example.stady.models.user_model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserModel {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createDate;
}
