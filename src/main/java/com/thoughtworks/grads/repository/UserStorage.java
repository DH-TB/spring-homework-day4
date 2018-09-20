package com.thoughtworks.grads.repository;

import com.thoughtworks.grads.domain.Contact;
import com.thoughtworks.grads.domain.User;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static final Map<Integer, User> USERS = new HashMap<>();

    public static void put(User user) {
        USERS.put(user.getId(), user);
    }

    public static User getUserById(Integer userId) {
        return USERS.get(userId);
    }

    public static User addUserContact(Integer id, Contact contact) {
        User user = UserStorage.getUserById(id);
        user.setContactList(contact);
        UserStorage.put(user);
        ContactStorage.add(contact);

        return user;
    }


    public static void clear() {
        USERS.clear();
    }
}
