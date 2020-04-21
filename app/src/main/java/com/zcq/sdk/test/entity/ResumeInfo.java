package com.zcq.sdk.test.entity;

public class ResumeInfo {
    //id, number, name, level, sub_level, department, job, contact_number, sex, marriage, nation, join_date,
    // birth_date, age, identify, political, birth_address, school, education, major, education_type, start_work_date,
    // resume, certificate, home_address
    private int id;
    private String number;
    private String name;
    private String age;
    private String level;
    private String sub_level;
    private String department;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSub_level() {
        return sub_level;
    }

    public void setSub_level(String sub_level) {
        this.sub_level = sub_level;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
