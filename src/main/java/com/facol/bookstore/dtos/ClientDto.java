package com.facol.bookstore.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto {
    private Long id;
    private String name;
    private String cpf;
    private String cnpj;
    private String cellPhone;
    @Setter
    private List<AddressDto> address;
    private String cep;
    @Setter
    private ExternalAddressDto externalAddressDto;
    @Setter
    private Set<BookDto> booksPurchased;
}
