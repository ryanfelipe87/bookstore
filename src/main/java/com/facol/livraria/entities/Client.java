package com.facol.livraria.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "cellPhone")
    private String cellPhone;

    @OneToMany(mappedBy = "client")
    private List<Address> address;

    public Client(Long id, String name, String cpf, String cnpj, String cellPhone) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.cellPhone = cellPhone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getCellPhone() {
        return cellPhone;
    }
}
