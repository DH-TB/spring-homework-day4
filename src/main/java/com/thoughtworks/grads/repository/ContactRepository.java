package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;

public interface ContactRepository {
    void addContact(Integer userId, Contact contact);
}
