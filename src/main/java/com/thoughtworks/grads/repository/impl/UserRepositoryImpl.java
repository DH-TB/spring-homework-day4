package com.thoughtworks.grads.repository.impl;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.UserStorage;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User addUserContact(Integer id, Contact contact) {
        return UserStorage.addUserContact(id, contact);
    }

    @Override
    public User findByUserId(Integer id) {
        return UserStorage.findByUserId(id);
    }

    @Override
    public User updateUserContact(Integer id, Contact contact) {
        return UserStorage.updateUserContact(id, contact);
    }

    @Override
    public void deleteUserContact(Integer userId, Integer contactId) {
        UserStorage.deleteUserContact(userId, contactId);
    }
}
