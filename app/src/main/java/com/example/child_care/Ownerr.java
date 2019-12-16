package com.example.child_care;

public class Ownerr {

    public String name,email,phone,type,specialist;

    public Ownerr() {
    }

    public Ownerr(String name, String email, String phone, String type, String specialist) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.specialist = specialist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }
}
