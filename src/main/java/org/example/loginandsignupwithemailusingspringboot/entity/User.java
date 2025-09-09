package org.example.loginandsignupwithemailusingspringboot.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Username;

    @Column(unique = true)
    private String Email;

    private String password;


}
