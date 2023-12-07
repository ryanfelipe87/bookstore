package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PaymentServiceUtils {

    private Logger logger = LoggerSingleton.getLogger();

    public PaymentServiceUtils(Logger logger){
        this.logger = logger;
    }

    public boolean verifyPayment(ClientDto clientDto, BookDto bookDto){
        double priceBook = calculatorPriceBook(bookDto);
        double valuePay = valuePayForClient(clientDto);

        if(valuePay >= priceBook){
            logger.info("Payment accept. Purchased book by: " + clientDto.getName());
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
