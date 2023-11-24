package com.facol.livraria.patterns.adapter.opencep;

import com.facol.livraria.dtos.ExternalAddressDto;
import com.facol.livraria.patterns.adapter.RequestExternal;
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
