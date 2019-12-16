package com.example.child_care.Models;

public class Child {
    private String name,age,blood,birthday;

    public Child() {
    }

    public Child(String name, String age, String blood, String birthday) {
        this.name = name;
        this.age = age;
        this.blood = blood;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
