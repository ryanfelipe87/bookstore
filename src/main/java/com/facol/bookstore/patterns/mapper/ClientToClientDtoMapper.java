package com.facol.bookstore.patterns.mapper;

import com.facol.bookstore.dtos.AddressDto;
import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.entities.Address;
import com.facol.bookstore.entities.Book;
import com.facol.bookstore.entities.Client;
import com.facol.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientToClientDtoMapper implements Mapper<Client, ClientDto> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ClientDto map(Client client, ClientDto clientDto) {
        List<BookDto> booksPurchased = mapBooksPurchased(client.getBooksPurchased());
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .cpf(client.getCpf())
                .cnpj(client.getCnpj())
                .cellPhone(client.getCellPhone())
                .booksPurchased(booksPurchased)
                .amountMoney(client.getAmountMoney())
                .address(getAddressDto(client.getAddress()))
                .build();
    }

    private List<AddressDto> getAddressDto(List<Address> address) {
        List<AddressDto> addressDtoList = new ArrayList<>();

        for (Address addressEntity : address) {
            AddressDto addressDto = AddressDto.builder()
                    .id(addressEntity.getId())
                    .zipCode(addressEntity.getZipCode())
                    .publicPlace(addressEntity.getPublicPlace())
                    .complement(addressEntity.getComplement())
                    .neighborhood(addressEntity.getNeighborhood())
                    .locality(addressEntity.getLocality())
                    .uf(addressEntity.getUf())
                    .gia(addressEntity.getGia())
                    .ddd(addressEntity.getDdd())
                    .siafi(addressEntity.getSiafi())
                    .ibge(addressEntity.getIbge())
                    .build();

            addressDtoList.add(addressDto);
        }

        return addressDtoList;
    }

    private List<BookDto> mapBooksPurchased(List<Book> booksPurchased){
        List<Book> books = bookRepository.findAll();
        return booksPurchased.stream()
                .map(this::mapBookToBookDto)
                .collect(Collectors.toList());
    }

    private BookDto mapBookToBookDto(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setAmount(book.getAmount());

        return bookDto;
    }
}
