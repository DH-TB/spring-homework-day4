package com.thoughtworks.grads.repository.impl;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.repository.ContactRepository;
import com.thoughtworks.grads.repository.ContactStorage;

public class ContactRepositoryImpl implements ContactRepository {

    @Override
    public void addContact(Integer userId, Contact contact) {
        ContactStorage.addContact(userId, contact);
    }

    @Override
    public void updateContact(Integer userId, Contact contact) {
        ContactStorage.updateContact(userId, contact);
    }

    @Override
    public void deleteByUserIdAndContactId(Integer userId, Integer contactId) {
        ContactStorage.deleteByUserIdAndContactId(userId, contactId);
    }
}
