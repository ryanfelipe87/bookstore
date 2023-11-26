package com.facol.bookstore.patterns.adapter.opencep;

import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.patterns.adapter.RequestExternal;
import org.springframework.stereotype.Component;

@Component
public class OpenCepAdapter implements RequestExternal {

    private final OpenCep openCep;

    public OpenCepAdapter(OpenCep openCep){
        this.openCep = openCep;
    }

    @Override
    public ExternalAddressDto getByParam(String cep) {
        return openCep.getAddressOpenCep(cep);
    }
}
