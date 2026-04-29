package com.miempresa.miapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.miempresa.miapi.model.User;
import com.miempresa.miapi.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    public User saveUser(User user) {
        return userRepository.save(user);
}

public List<User> getAllUsers() {
    return userRepository.findAll();
}

public User updateUser(Long id, User user) {
    
    return userRepository.findById(id)
    .map(existingUser -> {
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        return userRepository.save(existingUser);
    })
    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id));
}

public void deleteUser(Long id) {

    if (!userRepository.existsById(id)) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id " + id);
    }
    userRepository.deleteById(id);
}
}
