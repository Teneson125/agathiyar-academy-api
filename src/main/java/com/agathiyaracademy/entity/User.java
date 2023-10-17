package com.agathiyaracademy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String emailId;
    private String phoneNumber;
    @ManyToMany
    private Set<Role> roles;
    @OneToOne(mappedBy = "user")
    private Student student;
    @OneToOne(mappedBy = "user")
    private Admin admin;
}
