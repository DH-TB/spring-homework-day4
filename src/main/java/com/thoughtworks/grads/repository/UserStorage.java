package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;

import java.util.*;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static void put(User user) {
        if (user.getContacts() != null) {
            ContactStorage.putAll(user.getContacts());
        }

        USERS.put(user.getId(), user);
    }

    public static void addUserContact(Integer id, Contact contact) {
        contact.setUserId(id);
        ContactStorage.put(contact);
    }


    public static void clear() {
        USERS.clear();
    }

    public static User findByUserId(Integer id) {
        List<Contact> contacts = ContactStorage.findByUserId(id);

        User user = USERS.get(id);
        user.setContacts(contacts);

        return user;
    }

    public static Contact findByName(String userName, String contactName) {
        Collection<User> values = USERS.values();
        Optional<User> user = values.stream().filter(value -> value.getName().equals(userName)).findFirst();
        if (!user.isPresent()) {
            return null;
        }
        Optional<Contact> filteredContact = user.get().getContacts().stream().filter(contact -> contact.getName().equals(contactName)).findFirst();

        return filteredContact.orElse(null);
    }
}
