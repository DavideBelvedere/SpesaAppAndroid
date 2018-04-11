package com.example.davidebelvedere.spesaapp.data;

public class User {
    private String username;
    private String name;
    private String surname;
    private String email;

    public User(String username, String name, String surname, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }



    public String getName() {
        return name;
    }


    public String getSurname() {
        return surname;
    }



    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}