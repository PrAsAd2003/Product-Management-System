package com.example.ProductManagement;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // for to create id automatically in order
    private int id;

    @Column(length = 30)
    private String firstName;

    @Column(length = 30)
    private String lastName;

    private String Password;
    @Column(length = 30 , unique = true)
    private String email;
}
