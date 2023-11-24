package com.facol.livraria.patterns.adapter.viacep;

import com.facol.livraria.dtos.ExternalAddressDto;
import com.facol.livraria.patterns.adapter.RequestExternal;
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
