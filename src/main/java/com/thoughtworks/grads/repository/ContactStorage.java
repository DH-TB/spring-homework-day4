package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactStorage {
    private static final Map<Integer, Contact> CONTACTS = new HashMap<>();

    static void put(Contact contact) {
        CONTACTS.put(contact.getId(), contact);
    }

    static void putAll(List<Contact> contacts) {
        contacts.forEach(contact -> CONTACTS.put(contact.getId(), contact));
    }

    public static int getSize() {
        return CONTACTS.size();
    }


    public static void addContact(Integer userId, Contact contact) {
        contact.setUserId(userId);
    }

    static List<Contact> findByUserId(Integer id) {
        Collection<Contact> values = CONTACTS.values();
        Stream<Contact> contactStream = values.stream().filter(value -> value.getUserId().equals(id));
        return contactStream.collect(Collectors.toList());
    }

    public static void updateContact(Integer userId, Contact contact) {
        Contact getContact = CONTACTS.get(contact.getId());
        getContact.setUserId(userId);
        getContact.setAge(contact.getAge());
        getContact.setName(contact.getName());
    }

    public static void deleteByUserIdAndContactId(Integer userId, Integer contactId) {
        CONTACTS.remove(contactId);
    }
}
