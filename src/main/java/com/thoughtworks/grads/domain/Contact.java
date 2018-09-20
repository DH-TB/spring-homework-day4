package com.thoughtworks.grads.domain;

public class Contact {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer age;
    private String phone;
    private Sex sex;

    public Contact() {
    }

    public Contact(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Contact(Integer id, Integer userId, String name, Integer age, String phone, Sex sex) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
