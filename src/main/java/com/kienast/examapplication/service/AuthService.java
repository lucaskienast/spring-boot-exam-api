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
        this.userRepository = userRepository;
    }


    public String signup(User givenUser) {
        User user = new User();
        user.setUsername(givenUser.getUsername());
        user.setEmail(givenUser.getEmail());
        user.setPassword(givenUser.getPassword());
        user.setCreatedAt(new Date());

        LOG.info("AuthService: signup user: " + user);

        this.userRepository.save(user);
        LOG.info("AuthService: signup successful");

        return "success";
    }


    public User login(User givenUser) {
        User user = this.userRepository.findUserByUsername(givenUser.getUsername());

        if (user == null) {
            LOG.error("AuthService: login did not find user: " + user);
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
