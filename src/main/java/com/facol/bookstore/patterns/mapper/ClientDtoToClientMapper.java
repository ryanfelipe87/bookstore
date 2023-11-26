package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.AddressDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.patterns.builders.AddressBuilder;
import com.facol.bookstore.patterns.builders.ClientBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDtoToClientMapper implements Mapper<ClientDto, Client> {
    @Override
    public Client map(ClientDto clientDto, Client client) {
        return new ClientBuilder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .cpf(clientDto.getCpf())
                .cnpj(clientDto.getCnpj())
                .cellPhone(clientDto.getCellPhone())
                .address(client.getAddress() == null ? getAddressDto(clientDto.getAddress()) : client.getAddress())
                .build();
    }

    private List<Address> getAddressDto(List<AddressDto> addressDtoList){
        List<Address> addressList = new ArrayList<>();

        if(addressList != null && addressList.isEmpty()){
            for(AddressDto addressDto : addressDtoList){
                Address dto = new AddressBuilder()
                        .id(addressDto.getId())
                        .zipCode(addressDto.getZipCode())
                        .publicPlace(addressDto.getPublicPlace())
                        .complement(addressDto.getComplement())
                        .neighborhood(addressDto.getNeighborhood())
                        .locality(addressDto.getLocality())
                        .uf(addressDto.getUf())
                        .gia(addressDto.getGia())
                        .ddd(addressDto.getDdd())
                        .siafi(addressDto.getSiafi())
                        .ibge(addressDto.getIbge())
                        .build();

                addressList.add(dto);
            }
        }

        return addressList;
    }
}
