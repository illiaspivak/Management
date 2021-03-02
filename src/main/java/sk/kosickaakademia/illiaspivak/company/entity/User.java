package sk.kosickaakademia.illiaspivak.company.entity;

import sk.kosickaakademia.illiaspivak.company.helpclasses.Gender;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Gender gender;

    public User(int id, String firstName, String lastName, int age, Gender gender) {
        this(firstName, lastName, age, gender);
        this.id = id;
    }

    public User(String firstName, String lastName, int age, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }
}
