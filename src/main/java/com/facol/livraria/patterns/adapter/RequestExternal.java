package com.facol.livraria.patterns.adapter;

import com.facol.livraria.dtos.ExternalAddressDto;

public interface RequestExternal {
    ExternalAddressDto getByParam(String cep);
}
