package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;

public interface UserRepository {
    void addUserContact(Integer id, Contact contact);

    User findByUserId(Integer id);

    Contact findByName(String userName, String contactName);
}
