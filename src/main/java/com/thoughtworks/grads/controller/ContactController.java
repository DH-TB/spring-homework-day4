package com.thoughtworks.grads.controller;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.ContactRepository;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.impl.ContactRepositoryImpl;
import com.thoughtworks.grads.repository.impl.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContactController {
    private ContactRepository contactRepository = new ContactRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();

    @PostMapping("/contracts/{userId}")
    public ResponseEntity saveUserContact(@PathVariable Integer userId, @RequestBody Contact contact) {
        contactRepository.addContact(userId, contact);
        userRepository.addUserContact(userId, contact);

        User user = userRepository.findByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/contracts/{userId}")
    public ResponseEntity updateUserContact(@PathVariable Integer userId, @RequestBody Contact contact) {
        contactRepository.updateContact(userId, contact);
        User user = userRepository.findByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{userId}/contacts/{contactId}")
    public ResponseEntity deleteUserContact(@PathVariable Integer userId, @PathVariable Integer contactId) {
        contactRepository.deleteByUserIdAndContactId(userId, contactId);
        User user = userRepository.findByUserId(userId);
        return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }
}
