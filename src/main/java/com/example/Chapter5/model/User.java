package com.example.Chapter5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 225)
    private String password;

//    @OneToMany(mappedBy = "order")
//    @Cascade(org.hibernate.annotations.CascadeType.ALL)
//    private List<Order> orders;
}
