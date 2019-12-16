package com.example.child_care;

public class babyinfo {

    public String name,age,birthday,blood;

    public babyinfo()
    {


    }

    public babyinfo(String name, String age, String birthday, String blood) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.blood = blood;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }
}


