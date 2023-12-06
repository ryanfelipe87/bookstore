package com.facol.bookstore.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

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
    private Double amountMoney;
    @Setter
    private List<AddressDto> address;
    private String cep;
    @Setter
    private ExternalAddressDto externalAddressDto;
    @Setter
    private List<BookDto> booksPurchased;
}
