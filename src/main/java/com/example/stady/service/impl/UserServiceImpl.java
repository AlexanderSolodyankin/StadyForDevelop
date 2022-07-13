package com.example.stady.service.impl;

import com.example.stady.entity.Role;
import com.example.stady.entity.UserEntity;
import com.example.stady.models.user_model.UserModel;
import com.example.stady.repository.RoleRepository;
import com.example.stady.repository.UserRepository;
import com.example.stady.service.MailService;
import com.example.stady.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MailService mailSender;
    @Autowired
    private RoleRepository rolRepository;

    @Override
    public UserEntity newUser(UserEntity entity) {
        UserEntity checkUser = repository.findByLogin(entity.getLogin()).orElse(null);
        if(checkUser != null){
            throw  new IllegalArgumentException("Такой пользователь существует");
        }
        String activationCode = entity.getLogin() + ":" + entity.getPassword();
        activationCode = new String(Base64.getEncoder().encode(activationCode.getBytes()));
        entity.setActevationCode(activationCode);

        entity.setPassword(encoder.encode(entity.getPassword()));
        entity.setIsActive(0L);
        entity = repository.save(entity);

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        userRole.setUserEntity(entity);
        rolRepository.save(userRole);


        String activCodeSendMail = "http://localhost:8080/activation/" + activationCode;
        mailSender.send(entity.getEmail(), entity.getLogin(), activCodeSendMail);

        return entity;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserEntity getByUser(String login) {
        return repository.findByLogin(login).orElse(null);
    }
    @Override
    public UserEntity getByUser(Long id) {
        return repository.getById(id);
    }

    @Override
    public String getAuthorizedToken(UserModel model) throws IllegalArgumentException {
        UserEntity entity = repository.findByLogin(model.getLogin()).orElseThrow(
                () -> new IllegalArgumentException("Неверный или пароль логин.")
        );
        boolean isPasswordMatches = encoder.matches(model.getPassword(), entity.getPassword());
        if(!isPasswordMatches){
            throw  new IllegalArgumentException("Неверный пароль или логин.");
        }
        String userNamePasswordPair = model.getLogin() + ":" + model.getPassword();
        System.out.println("Отправлен Токен ");
        return "Basic " + new String(Base64.getEncoder().encode(userNamePasswordPair.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public ResponseEntity<Object> activationUser(String activation) {
        UserEntity entity = repository.findByActevationCode(activation).orElseThrow(
                () -> new IllegalArgumentException("Проблемы с активацией акаунта"));

        if (entity.getActevationCode().equals(activation)) {
            entity.setIsActive(1L);
            entity.setActevationCode(null);
        }
        repository.save(entity);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("http://localhost:3000"))
                .build();

    }

    @Override
    public UserEntity getCurrentUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().toString();
        System.out.println("Возврощаю юзера по логину из Каррент Юзер: " +  userName);
        return getByUser(userName);
    }


    @Override
    public UserEntity convert(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        entity.setLogin(model.getLogin());
        entity.setPassword(model.getPassword());
        entity.setEmail(model.getEmail());
        entity.setPhone(model.getPhone());
        return entity;
    }

    @Override
    public UserModel convert(UserEntity entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setLogin(entity.getLogin());
        model.setPassword(entity.getPassword());
        model.setEmail(entity.getEmail());
        model.setPhone(entity.getPhone());
        model.setCreateDate(entity.getCreateDate());
        return model;
    }

    @Override
    public List<UserEntity> convertListToEntity(List<UserModel> models) {
        List<UserEntity> entities = new ArrayList<>();
        for(UserModel modelElement : models){
            entities.add(convert(modelElement));
        }
        return entities;
    }

    @Override
    public List<UserModel> convertListToModel(List<UserEntity> entityes) {
        List<UserModel> models = new ArrayList<>();
        for(UserEntity entity : entityes){
            models.add(convert(entity));
        }
        return models;
    }
}
