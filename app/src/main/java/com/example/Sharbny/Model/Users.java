package com.example.Sharbny.Model;

public class Users {

    private String firstname, username , password;
    private int age;
    public double liters_recomended;
    private double weight;
    public Records The_Records;
    public Thirsty Drink;

    public Users()
    {

    }

    public Users(String firstname, String username, String password) {
        this.firstname = firstname;
        this.username = username;
        this.password = password;
    }
    public Users(String firstname, String username, int Age,double Weight) {
        this.username = username;
        this.firstname = firstname;
        this.age = Age;
        this.weight = Weight;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setAge(int Age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }


    public void setWeight(double Weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }


    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
