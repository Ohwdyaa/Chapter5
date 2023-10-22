package com.example.Chapter5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "merchant")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Merchant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long merchantCode;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "location", length = 100)
    private String location;

    @Column(name = "status")
    private Boolean open;

    @OneToMany(mappedBy = "merchantCode")
    @Cascade(CascadeType.ALL)
    private List<Product> products;
}
