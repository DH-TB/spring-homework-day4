package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static void put(User user) {
        if (user.getContacts() != null) {
            ContactStorage.putAll(user.getContacts());
        }

        USERS.put(user.getId(), user);
    }

    public static User getUserById(Integer userId) {
        return USERS.get(userId);
    }

    public static User addUserContact(Integer id, Contact contact) {
        User user = UserStorage.getUserById(id);
        user.setContactList(contact);
        UserStorage.put(user);
        ContactStorage.put(contact);

        return user;
    }


    public static void clear() {
        USERS.clear();
    }

    public static User findByUserId(Integer id) {
        return USERS.get(id);
    }

    public static User updateUserContact(Integer id, Contact contact) {
        User user = UserStorage.getUserById(id);
        Contact findContact = user.getContacts().stream()
                .filter(filteredContact -> filteredContact.getId().equals(contact.getId()))
                .findFirst()
                .orElse(null);

        if (findContact == null) {
            ContactStorage.put(contact);
            user.setContactList(contact);
        } else {
            findContact.setAge(contact.getAge());
            findContact.setName(contact.getName());

            ContactStorage.update(contact);
        }
        return user;
    }

    public static int getSize() {
        return USERS.size();
    }

    public static void deleteUserContact(Integer userId, Integer contactId) {
        User user = UserStorage.getUserById(userId);
        ContactStorage.delete(contactId);
    }

    public static Contact findByName(String userName, String contactName) {
        Collection<User> values = USERS.values();
        Optional<User> user = values.stream().filter(value -> value.getName().equals(userName)).findFirst();
        if(!user.isPresent()) {
            return null;
        }
        Optional<Contact> filteredContact = user.get().getContacts().stream().filter(contact -> contact.getName().equals(contactName)).findFirst();

        return filteredContact.orElse(null);
    }
}
