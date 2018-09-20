package com.thoughtworks.grads.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private List<Contact> contacts = new LinkedList<>();

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Integer id, String name, List<Contact> contacts) {
        this.id = id;
        this.name = name;
        this.contacts = contacts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact... contact) {
        List<Contact> contacts11 = Arrays.asList(contact);
        List<Contact> list = new LinkedList<>(contacts11);
        this.contacts.addAll(list);
    }
}
