package com.facol.livraria.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String zipCode;

    private String publicPlace;

    private String complement;

    private String neighborhood;

    private String locality;

    private String uf;

    private String gia;

    private String ddd;

    private String siafi;

    @ManyToOne
    @JoinColumn(name = "client_fk", nullable=false)
    private Client client;

    public Address(Long id, String zipCode, String publicPlace, String complement, String neighborhood, String locality, String uf, String gia, String ddd, String siafi) {
        super();
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.publicPlace = publicPlace;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.locality = locality;
        this.uf = uf;
        this.gia = gia;
        this.ddd = ddd;
        this.siafi = siafi;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public String getUf() {
        return uf;
    }

    public String getGia() {
        return gia;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSiafi() {
        return siafi;
    }
}
