package com.facol.bookstore.patterns.builders;

import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Client;

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
    private String ibge;
    private Client client;


    public AddressBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public AddressBuilder zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public AddressBuilder publicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
        return this;
    }

    public AddressBuilder complement(String complement) {
        this.complement = complement;
        return this;
    }

    public AddressBuilder neighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        return this;
    }

    public AddressBuilder locality(String locality) {
        this.locality = locality;
        return this;
    }

    public AddressBuilder uf(String uf) {
        this.uf = uf;
        return this;
    }

    public AddressBuilder gia(String gia) {
        this.gia = gia;
        return this;
    }

    public AddressBuilder ddd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public AddressBuilder siafi(String siafi) {
        this.siafi = siafi;
        return this;
    }

    public AddressBuilder ibge(String ibge){
        this.ibge = ibge;
        return this;
    }

    public AddressBuilder client(Client client){
        this.client = client;
        return this;
    }

    public Address build() {
        return new Address(id, zipCode, publicPlace, complement, neighborhood, locality, uf, gia, ddd, siafi, ibge, client);

    }
}
