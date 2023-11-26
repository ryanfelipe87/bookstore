package com.facol.bookstore.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
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
}
