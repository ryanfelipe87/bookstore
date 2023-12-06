package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.AddressDto;
import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
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
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClientDtoToClientMapper implements Mapper<ClientDto, Client> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Client map(ClientDto clientDto, Client client) {
        List<Book> booksPurchased = mapBooksPurchased((Set<BookDto>) clientDto.getBooksPurchased());
        return new ClientBuilder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .cpf(clientDto.getCpf())
                .cnpj(clientDto.getCnpj())
                .cellPhone(clientDto.getCellPhone())
                .amountMoney(clientDto.getAmountMoney())
                .booksPurchased(booksPurchased)
                .address(client.getAddress() == null ? getAddressDto(clientDto.getAddress()) : client.getAddress())
                .build();
    }

    private List<Address> getAddressDto(List<AddressDto> addressDtoList){
        List<Address> addressList = new ArrayList<>();

        if(addressList != null && addressList.isEmpty()){
            for(AddressDto addressDto : addressDtoList){
                Address dto = new AddressBuilder()
                        .id(addressDto.getId())
                        .zipCode(addressDto.getZipCode())
                        .publicPlace(addressDto.getPublicPlace())
                        .complement(addressDto.getComplement())
                        .neighborhood(addressDto.getNeighborhood())
                        .locality(addressDto.getLocality())
                        .uf(addressDto.getUf())
                        .gia(addressDto.getGia())
                        .ddd(addressDto.getDdd())
                        .siafi(addressDto.getSiafi())
                        .ibge(addressDto.getIbge())
                        .build();

                addressList.add(dto);
            }
        }

        return addressList;
    }

    private List<Book> mapBooksPurchased(Set<BookDto> bookDtos){
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
        book.setPrice(bookDto.getPrice());
        return book;
    }
}
