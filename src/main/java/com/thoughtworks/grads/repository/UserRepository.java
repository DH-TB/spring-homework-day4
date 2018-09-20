package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;

public interface UserRepository {
    User addUserContact(Integer id, Contact contact);

    User findByUserId(Integer id);
}
