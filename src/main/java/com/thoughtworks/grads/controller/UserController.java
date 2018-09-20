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


    @PostMapping("/users/{id}")
    public ResponseEntity saveUserContact(@PathVariable Integer id, @RequestBody Contact contact) {
        User user = userRepository.addUserContact(id, contact);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity queryContactsByUserId(@PathVariable Integer id) {
        User user = userRepository.findByUserId(id);
        return new ResponseEntity<>(user.getContacts(), HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUserContact(@PathVariable Integer id, @RequestBody Contact contact) {
        User user = userRepository.updateUserContact(id, contact);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    public ResponseEntity deleteUserContact(@PathVariable Integer userId, @PathVariable Integer contactId) {
        userRepository.deleteUserContact(userId, contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
