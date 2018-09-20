package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;

import java.util.HashMap;
import java.util.Map;

public class ContactStorage {
    private static final Map<Integer, Contact> CONTACTS = new HashMap<>();

    public static void add(Contact contact) {
        CONTACTS.put(contact.getId(), contact);
    }
}
