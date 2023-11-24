package com.facol.livraria.patterns.adapter.viacep;

import com.facol.livraria.dtos.ExternalAddressDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ViaCep {

    public ExternalAddressDto getAddressViaCep(String cep){
        RestTemplate restTemplate = new RestTemplate();
        ExternalAddressDto externalAddressDto = new ExternalAddressDto();

        try{
            String url = "https://viacep.com.br/ws/" + cep + "/json/";
            externalAddressDto = restTemplate.getForEntity(url, ExternalAddressDto.class).getBody();
        }catch(HttpClientErrorException exception){
            throw new GenericNotFoundException("Address not found with the cep:" + cep);
        }

        return externalAddressDto;
    }
}
