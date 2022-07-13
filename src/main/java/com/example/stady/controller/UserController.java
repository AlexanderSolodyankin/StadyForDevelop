package com.example.stady.controller;


import com.example.stady.models.user_model.UserModel;
import com.example.stady.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UsersService service;

    @PostMapping("/")
    public UserModel registration(@RequestBody UserModel model){
        return  service.convert(service.newUser(service.convert(model)));
    }

    @PostMapping("/authorized")
    String authorized(@RequestBody UserModel model){
        System.out.println("Зашол в контроллер аунтефикации ");
        return service.getAuthorizedToken(model);
    }

    @GetMapping("/get-current")
    public UserModel getCurrent() {
        System.out.println("Зашол в гет Каррент");
        return service.convert(service.getCurrentUser());
    }

    @GetMapping("/allUsers")
    public List<UserModel> getAllUsers(){
        return service.convertListToModel(service.getAllUsers());
    }


    @GetMapping("/activation/{code}")
    public ResponseEntity<Object> acivationUser(@PathVariable String code) {
        return service.activationUser(code);
    }
}
