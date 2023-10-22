package com.example.Chapter5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "product")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productCode;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "price", length = 225)
    private double price;

    @Column(name = "available")
    private Boolean available;

    @ManyToOne
    @JoinColumn(name = "merchant_code")
    @Cascade(CascadeType.ALL)
    private Merchant merchantCode;

}
