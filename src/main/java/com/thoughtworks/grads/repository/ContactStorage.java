package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactStorage {
    private static final Map<Integer, Contact> CONTACTS = new HashMap<>();

    public static void put(Contact contact) {
        CONTACTS.put(contact.getId(), contact);
    }

    public static void putAll(List<Contact> contacts) {
        contacts.forEach(contact -> CONTACTS.put(contact.getId(), contact));
    }

    public static void update(Contact contact) {
        Contact getContact = CONTACTS.get(contact.getId());
        getContact.setAge(contact.getAge());
        getContact.setName(contact.getName());
    }

    public static int getSize() {
        return CONTACTS.size();
    }

    public static void delete(Integer contactId) {
        CONTACTS.remove(contactId);
    }
}
