package com.facol.bookstore.patterns.adapter;

import com.facol.bookstore.dtos.ExternalAddressDto;

public interface RequestExternal {
    ExternalAddressDto getByParam(String cep);
}
