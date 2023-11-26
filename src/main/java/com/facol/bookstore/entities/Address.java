package com.facol.bookstore.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "public_place")
    private String publicPlace;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood")
    private String neighborhood;

    @Column(name = "locality")
    private String locality;

    @Column(name = "uf")
    private String uf;

    @Column(name = "gia")
    private String gia;

    @Column(name = "ddd")
    private String ddd;

    @Column(name = "siafi")
    private String siafi;

    @Column(name = "ibge")
    private String ibge;

    @ManyToOne
    @JoinColumn(name = "client_fk")
    private Client client;

}
