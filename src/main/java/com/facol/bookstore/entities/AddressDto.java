
package com.facol.bookstore.entities;

import com.facol.bookstore.dtos.ClientDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
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
    private ClientDto clientDTO;
}
