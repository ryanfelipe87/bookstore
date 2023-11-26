package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.AddressDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientToClientDtoMapper implements Mapper<Client, ClientDto> {
    @Override
    public ClientDto map(Client client, ClientDto clientDto) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .cnpj(client.getCnpj())
                .cellPhone(client.getCellPhone())
                .address(getAddressDto(client.getAddress()))
                .build();
    }

    private List<AddressDto> getAddressDto(List<Address> address) {
        List<AddressDto> addressDtoList = new ArrayList<>();

        for (Address addressEntity : address) {
            AddressDto addressDto = AddressDto.builder()
                    .id(addressEntity.getId())
                    .zipCode(addressEntity.getZipCode())
                    .publicPlace(addressEntity.getPublicPlace())
                    .complement(addressEntity.getComplement())
                    .neighborhood(addressEntity.getNeighborhood())
                    .locality(addressEntity.getLocality())
                    .uf(addressEntity.getUf())
                    .gia(addressEntity.getGia())
                    .ddd(addressEntity.getDdd())
                    .siafi(addressEntity.getSiafi())
                    .ibge(addressEntity.getIbge())
                    .build();

            addressDtoList.add(addressDto);
        }

        return addressDtoList;
    }
}
