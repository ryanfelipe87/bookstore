package com.facol.bookstore.patterns.adapter.opencep;

import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.RequestCommon;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

@Component
public class OpenCep {

    public ExternalAddressDto getAddressOpenCep(String cep){
        RequestCommon requestCommon = new RequestCommon();
        ExternalAddressDto externalAddressDto = new ExternalAddressDto();

        try{
            String url = "https://opencep.com/v1/" + cep;
            externalAddressDto = (ExternalAddressDto) requestCommon.get(url, HttpMethod.GET, HttpEntity.EMPTY, ExternalAddressDto.class);
        }catch(HttpClientErrorException exception){
            throw new GenericNotFoundException("Address not found with the cep: " + cep);
        }

        return externalAddressDto;
    }
}
