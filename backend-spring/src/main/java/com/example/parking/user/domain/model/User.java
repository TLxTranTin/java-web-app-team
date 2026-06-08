package com.example.parking.user.domain.model;

import com.example.parking.auth.domain.model.UserRole;

public class User {
    private Long id;
    private String username;
    private String passwordHash;
    private UserRole role;
    private String fullName;
    private String email;
    private String phone;

    public User(Long id, String username, String passwordHash, UserRole role,
                String fullName, String email, String phone) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public Long getId()            { return id; }
    public String getUsername()    { return username; }
    public String getPasswordHash(){ return passwordHash; }
    public UserRole getRole()      { return role; }
    public String getFullName()    { return fullName; }
    public String getEmail()       { return email; }
    public String getPhone()       { return phone; }

    public User withPasswordHash(String newHash) {
        return new User(id, username, newHash, role, fullName, email, phone);
    }

    public User withRole(UserRole newRole) {
        return new User(id, username, passwordHash, newRole, fullName, email, phone);
    }
}