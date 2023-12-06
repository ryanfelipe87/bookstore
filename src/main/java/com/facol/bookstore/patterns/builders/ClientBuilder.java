package com.facol.bookstore.patterns.builders;

import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.patterns.factory.Person;

import java.util.ArrayList;
import java.util.List;

public class ClientBuilder extends Person {

    private Long id;
    private String name;
    private String cpf;
    private String cnpj;
    private String cellPhone;

    private Double amountMoney;
    private List<Address> address;

    private List<Book> booksPurchased;

    public ClientBuilder(){
        this.booksPurchased = new ArrayList<>();
    }

    public ClientBuilder id(Long id){
        this.id = id;
        return this;
    }

    public ClientBuilder name(String name){
        this.name = name;
        return this;
    }

    public ClientBuilder cpf(String cpf){
        this.cpf = cpf;
        return this;
    }

    public ClientBuilder cnpj(String cnpj){
        this.cnpj = cnpj;
        return this;
    }

    public ClientBuilder cellPhone(String cellPhone){
        this.cellPhone = cellPhone;
        return this;
    }

    public ClientBuilder amountMoney(Double amountMoney){
        this.amountMoney = amountMoney;
        return this;
    }

    public ClientBuilder address(List<Address> address){
        this.address = address;
        return this;
    }

    public ClientBuilder booksPurchased(List<Book> booksPurchased){
        this.booksPurchased = booksPurchased;
        return this;
    }

    public Client build(){
        return new Client(id, name, cpf, cnpj, cellPhone, amountMoney, address, booksPurchased);
    }
}
