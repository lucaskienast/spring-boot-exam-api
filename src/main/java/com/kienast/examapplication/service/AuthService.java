package com.kienast.examapplication.service;

import com.kienast.examapplication.model.User;
import com.kienast.examapplication.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class AuthService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        LOG.info("AuthService: constructor called");
        this.userRepository = userRepository;
    }


    public User signup(User givenUser) {
        LOG.info("AuthService: signup givenUser -> {}", givenUser);
        User user = new User();
        user.setUsername(givenUser.getUsername());
        user.setEmail(givenUser.getEmail());
        user.setPassword(givenUser.getPassword());
        user.setCreatedAt(new Date());

        LOG.info("AuthService: unsaved user: " + user);
        this.userRepository.save(user);
        LOG.info("AuthService: saved user: " + user);

        return user;
    }


    public User login(User givenUser) {
        LOG.info("AuthService: login givenUser -> {}", givenUser);
        User user = this.userRepository.findUserByUsername(givenUser.getUsername());
        LOG.info("AuthService: found user -> {}", user);

        if (user == null) {
            LOG.error("AuthService: login did not find givenUser -> {}" + givenUser);
            return null;
        }

        LOG.info("AuthService: login found user: " + user);

        if (user.getPassword().equals(givenUser.getPassword())) {
            LOG.info("AuthService: login successful");
            return user;
        }

        LOG.error("AuthService: login failed");
        return null;
    }


}
