package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.patterns.builders.AddressBuilder;
import com.facol.bookstore.patterns.builders.ClientBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientWithCnpjMapper implements Mapper<ClientDto, Client> {
    @Override
    public Client map(ClientDto clientDto, Client client) {
        return new ClientBuilder()
                .name(clientDto.getName())
                .cnpj(clientDto.getCnpj())
                .cellPhone(clientDto.getCellPhone())
                .address(getAddress(clientDto.getExternalAddressDto()))
                .build();
    }

    private List<Address> getAddress(ExternalAddressDto externalAddressDto) {
        List<Address> listOfAddress = new ArrayList<>();

        if(externalAddressDto != null) {
            Address addressEntity = new AddressBuilder()
                    .zipCode(externalAddressDto.getCep())
                    .publicPlace(externalAddressDto.getLogradouro())
                    .complement(externalAddressDto.getComplemento())
                    .neighborhood(externalAddressDto.getBairro())
                    .locality(externalAddressDto.getLocalidade())
                    .uf(externalAddressDto.getUf())
                    .gia(externalAddressDto.getGia())
                    .ddd(externalAddressDto.getDdd())
                    .siafi(externalAddressDto.getSiafi())
                    .ibge(externalAddressDto.getIbge())
                    .build();

            listOfAddress.add(addressEntity);
        }
        return listOfAddress;
    }

}
