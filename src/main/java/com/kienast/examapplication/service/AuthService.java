package com.kienast.examapplication.service;

import com.kienast.examapplication.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String signup(User givenUser) {
        return "User registration successful";
    }

    public String login(User givenUser) {
        return "User login successful";
    }

}
