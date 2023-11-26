package com.facol.bookstore.patterns.adapter.viacep;

import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.patterns.adapter.RequestExternal;
import org.springframework.stereotype.Component;

@Component
public class ViaCepAdapter implements RequestExternal {

    private final ViaCep viaCep;

    public ViaCepAdapter(ViaCep viaCep){
        this.viaCep = viaCep;
    }

    @Override
    public ExternalAddressDto getByParam(String cep) {
        return viaCep.getAddressViaCep(cep);
    }
}
