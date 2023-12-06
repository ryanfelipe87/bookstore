package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SendServiceUtils {

    private final PaymentServiceUtils paymentServiceUtils;

    public SendServiceUtils(PaymentServiceUtils paymentServiceUtils){
        this.paymentServiceUtils = paymentServiceUtils;
    }

    public void sendBook(ClientDto clientDto, BookDto bookDto){
        if(paymentServiceUtils.verifyPayment(clientDto, bookDto)){
            System.out.println("Payment confirmed. Book sent to address " + clientDto.getName() + " in " + clientDto.getAddress());
            updateStatusSend(bookDto);
        }else{
            System.out.println("Payment not confirmed. The book will not be sent.");
        }
    }

    private void updateStatusSend(BookDto bookDto){
        System.out.println("The status of book was updated with ID: " + bookDto.getId());
    }
}
