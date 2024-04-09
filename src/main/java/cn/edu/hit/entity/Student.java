package cn.edu.hit.entity;

public class Student {
    private String sid;
    private String name;
    private int age;
    private String birthday;
    private String gender;
    public Student() {

    }
    public Student(String sid, String name, int age, String birthday, String gender) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
    }
    public String getSid() {
        return sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
}
