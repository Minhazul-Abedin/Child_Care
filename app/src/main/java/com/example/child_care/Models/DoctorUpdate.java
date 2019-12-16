package com.example.child_care.Models;

public class DoctorUpdate {
    private String name,age,bloodGroup,healthCondition,bmi;

    public DoctorUpdate() {
    }

    public DoctorUpdate(String name, String age, String bloodGroup, String healthCondition, String bmi) {
        this.name = name;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.healthCondition = healthCondition;
        this.bmi = bmi;
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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void setHealthCondition(String healthCondition) {
        this.healthCondition = healthCondition;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }
}
