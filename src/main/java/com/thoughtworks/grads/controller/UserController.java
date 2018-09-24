package com.thoughtworks.grads.controller;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    private UserRepository userRepository = new UserRepositoryImpl();

    @GetMapping("/users/{userId}/contacts")
    public ResponseEntity queryContactsByUserId(@PathVariable Integer userId) {
        User user = userRepository.findByUserId(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(user.getContacts(), HttpStatus.OK);
    }

    @GetMapping("users/{userName}/contacts/{contactName}")
    public ResponseEntity queryContactsByName(@PathVariable String userName, @PathVariable String contactName) {
        Contact contact = userRepository.findByName(userName, contactName);
        if(contact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
