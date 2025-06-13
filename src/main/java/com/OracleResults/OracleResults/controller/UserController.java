package com.OracleResults.OracleResults.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OracleResults.OracleResults.dto.User;
import com.OracleResults.OracleResults.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/mantenimiento/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        System.out.println("Obteniendo la lista de todos los usuarios");
        return ResponseEntity.ok(users);
    }

}
