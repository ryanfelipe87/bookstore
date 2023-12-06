package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.dtos.ExternalAddressDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.patterns.builders.AddressBuilder;
import com.facol.bookstore.patterns.builders.ClientBuilder;
import com.facol.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientWithCnpjMapper implements Mapper<ClientDto, Client> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Client map(ClientDto clientDto, Client client) {
        List<Book> bookDtos = mapBooksPurchased(clientDto.getBooksPurchased());
        return new ClientBuilder()
                .name(clientDto.getName())
                .cnpj(clientDto.getCnpj())
                .cellPhone(clientDto.getCellPhone())
                .amountMoney(clientDto.getAmountMoney())
                .booksPurchased(bookDtos)
                .address(getAddress(clientDto.getExternalAddressDto()))
                .build();
    }

    private List<Address> getAddress(ExternalAddressDto externalAddressDto) {
        List<Address> listOfAddress = new ArrayList<>();

        if(externalAddressDto != null) {
            Address addressEntity = new AddressBuilder()
                    .zipCode(externalAddressDto.getCep())
                    .publicPlace(externalAddressDto.getLogradouro())
                    .complement(externalAddressDto.getComplemento())
                    .neighborhood(externalAddressDto.getBairro())
                    .locality(externalAddressDto.getLocalidade())
                    .uf(externalAddressDto.getUf())
                    .gia(externalAddressDto.getGia())
                    .ddd(externalAddressDto.getDdd())
                    .siafi(externalAddressDto.getSiafi())
                    .ibge(externalAddressDto.getIbge())
                    .build();

            listOfAddress.add(addressEntity);
        }
        return listOfAddress;
    }

    private List<Book> mapBooksPurchased(List<BookDto> bookDtos){
        List<Book> books = bookRepository.findAll();
        return bookDtos.stream()
                .map(this::mapBookDtoToBook)
                .collect(Collectors.toList());
    }

    private Book mapBookDtoToBook(BookDto bookDto){
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setAmount(bookDto.getAmount());

        return book;
    }

}
