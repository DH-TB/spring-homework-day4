package com.thoughtworks.grads.repository.impl;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;
import com.thoughtworks.grads.repository.UserRepository;
import com.thoughtworks.grads.repository.UserStorage;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void addUserContact(Integer id, Contact contact) {
        UserStorage.addUserContact(id, contact);
    }

    @Override
    public User findByUserId(Integer id) {
        return UserStorage.findByUserId(id);
    }

    @Override
    public Contact findByName(String userName, String contactName) {
        return UserStorage.findByName(userName, contactName);
    }
}
