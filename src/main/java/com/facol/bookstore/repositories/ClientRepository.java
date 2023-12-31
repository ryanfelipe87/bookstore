package com.facol.bookstore.repositories;

import com.facol.bookstore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM client WHERE cpf = :cpf", nativeQuery = true)
    Client searchClientForCpf(String cpf);
}
