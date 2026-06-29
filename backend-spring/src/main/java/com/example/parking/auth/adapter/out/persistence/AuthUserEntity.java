package com.example.parking.auth.adapter.out.persistence;

import com.example.parking.auth.domain.model.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "auth_users")
public class AuthUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private UserRole role;

    @Column(length = 150)
    private String fullName;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

    public AuthUserEntity() {}

    public AuthUserEntity(Long id, String username, String passwordHash, UserRole role,
                          String fullName, String email, String phone) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }

    public AuthUserEntity(Long id, String username, String passwordHash, UserRole role) {
        this(id, username, passwordHash, role, null, null, null);
    }

    public Long getId()            { return id; }
    public String getUsername()    { return username; }
    public String getPasswordHash(){ return passwordHash; }
    public UserRole getRole()      { return role; }
    public String getFullName()    { return fullName; }
    public String getEmail()       { return email; }
    public String getPhone()       { return phone; }
}