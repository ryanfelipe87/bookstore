package com.facol.bookstore.patterns.factory;

import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.patterns.adapter.RequestExternal;
import com.facol.bookstore.patterns.adapter.viacep.ViaCep;
import com.facol.bookstore.patterns.adapter.viacep.ViaCepAdapter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class PersonFactory {

    private final ClientWithCnpjMapper clientCnpj;
    private final ClientWithCpfMapper clientCpf;

    public PersonFactory(ClientWithCnpjMapper clientCnpj, ClientWithCpfMapper clientCpf){
        this.clientCnpj = clientCnpj;
        this.clientCpf = clientCpf;
    }

    public Client getPerson(ClientDto clientDto){
        Client client = new Client();
        getAddress(clientDto);

        if(!clientDto.getCnpj().equalsIgnoreCase("") && !clientDto.getCpf().equalsIgnoreCase("")){
            throw new RuntimeException("Cpf and Cnpj is not allowed at the same time.");
        }

        if(!clientDto.getCnpj().equalsIgnoreCase("")){
            client = this.clientCnpj.map(clientDto, new Client());
        }

        if(!clientDto.getCpf().equalsIgnoreCase("")){
            client = this.clientCpf.map(clientDto, new Client());
        }

        for(Address address : client.getAddress()){
            address.setClient(client);
        }

        return client;
    }

    private void getAddress(ClientDto clientDto){
        if(StringUtils.hasText(clientDto.getCep())){
            ExternalAddressDto externalAddressDto = findAddress(clientDto.getCep(), new ViaCepAdapter(new ViaCep()));
            clientDto.setExternalAddressDto(externalAddressDto);
        }
    }

    private ExternalAddressDto findAddress(String cep, RequestExternal requestExternal){
        return requestExternal.getByParam(cep);
    }
}
