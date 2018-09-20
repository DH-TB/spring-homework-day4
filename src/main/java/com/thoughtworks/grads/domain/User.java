package com.thoughtworks.grads.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private List<Contact> contacts = new ArrayList<>();

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

    public void setContactList(Contact... contact) {
        contacts.addAll(Arrays.asList(contact));
    }
}
