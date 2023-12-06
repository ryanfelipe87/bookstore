package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceUtils {

    private final PaymentServiceUtils paymentServiceUtils;

    public PaymentServiceUtils(PaymentServiceUtils paymentServiceUtils){
        this.paymentServiceUtils = paymentServiceUtils;
    }

    public boolean verifyPayment(ClientDto clientDto, BookDto bookDto){
        double priceBook = calculatorPriceBook(bookDto);
        double valuePay = valuePayForClient(clientDto);

        if(valuePay >= priceBook){
            System.out.println("Payment accept. Purchased book by: " + clientDto.getName());
            return true;
        }else{
            throw new GenericNotFoundException("Insufficient payment.");
        }
    }

    private double calculatorPriceBook(BookDto bookDto){
        return bookDto.getPrice();
    }

    private double valuePayForClient(ClientDto clientDto){
        return clientDto.getAmountMoney();
    }
}
