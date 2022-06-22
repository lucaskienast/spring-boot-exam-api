package com.kienast.examapplication.controller;

import com.kienast.examapplication.model.User;
import com.kienast.examapplication.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        LOG.info("AuthController: signup with user -> " + user);
        String response = this.authService.signup(user);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User givenUser) {
        LOG.info("AuthController: login with user -> " + givenUser);
        User user = this.authService.login(givenUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
