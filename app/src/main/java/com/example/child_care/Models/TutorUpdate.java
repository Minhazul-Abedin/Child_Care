package com.example.child_care.Models;

public class TutorUpdate {
    private String name,age,homeWork,studyTime;

    public TutorUpdate() {
    }

    public TutorUpdate(String name, String age, String homeWork, String studyTime) {
        this.name = name;
        this.age = age;
        this.homeWork = homeWork;
        this.studyTime = studyTime;
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

    public String getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(String homeWork) {
        this.homeWork = homeWork;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }
}
