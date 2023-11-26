package com.facol.bookstore.patterns.builders;

import com.facol.bookstore.entities.Address;

public class AddressBuilder {

    private Long id;
    private String zipCode;
    private String publicPlace;
    private String complement;
    private String neighborhood;
    private String locality;
    private String uf;
    private String gia;
    private String ddd;
    private String siafi;


    public void id(Long id) {
        this.id = id;
    }

    public void zipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public void publicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public void complement(String complement) {
        this.complement = complement;
    }

    public void neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void locality(String locality) {
        this.locality = locality;
    }

    public void uf(String uf) {
        this.uf = uf;
    }

    public void gia(String gia) {
        this.gia = gia;
    }

    public void ddd(String ddd) {
        this.ddd = ddd;
    }

    public void siafi(String siafi) {
        this.siafi = siafi;
    }

    public Address build() {
        return new Address(id, zipCode, publicPlace, complement, neighborhood, locality, uf, gia, ddd, siafi);

    }
}
