package com.example.stady.service;

import com.example.stady.entity.UserEntity;
import com.example.stady.models.user_model.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsersService {

    UserEntity newUser(UserEntity entity);
    List<UserEntity> getAllUsers();
    UserEntity getByUser(String login);
    UserEntity getByUser(Long id);

    public String getAuthorizedToken(UserModel model);
    ResponseEntity<Object> activationUser(String activation);
    public UserEntity getCurrentUser();


    UserEntity convert (UserModel model);
    UserModel convert (UserEntity entity);
    List<UserEntity> convertListToEntity(List<UserModel> models);
    List<UserModel> convertListToModel(List<UserEntity> models);

}
