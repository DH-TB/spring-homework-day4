package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;

public interface ContactRepository {
    void addContact(Integer userId, Contact contact);

    void updateContact(Integer userId,Integer contactId, Contact contact);

    void deleteByUserIdAndContactId(Integer userId, Integer contactId);

}
