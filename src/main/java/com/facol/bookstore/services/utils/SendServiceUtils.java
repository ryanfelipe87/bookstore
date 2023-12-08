package com.facol.bookstore.services.utils;

import com.facol.bookstore.dtos.BookDto;
import com.facol.bookstore.dtos.ClientDto;
import com.facol.bookstore.exceptions.GenericNotFoundException;
import com.facol.bookstore.patterns.singleton.LoggerSingleton;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class SendServiceUtils {

    private Logger logger = LoggerSingleton.getLogger();

    private final PaymentServiceUtils paymentServiceUtils;
    private final StockServiceUtils stockServiceUtils;

    public SendServiceUtils(PaymentServiceUtils paymentServiceUtils, StockServiceUtils stockServiceUtils, Logger logger){
        this.paymentServiceUtils = paymentServiceUtils;
        this.stockServiceUtils = stockServiceUtils;
        this.logger = logger;
    }

    public void sendBook(ClientDto clientDto, BookDto bookDto){
        if(stockServiceUtils.verifyStock(bookDto)) {
            if (paymentServiceUtils.verifyPayment(clientDto, bookDto)) {
                logger.info("Payment confirmed. Book sent to address " + clientDto.getName() + " in " + clientDto.getAddress());
                updateStatusSend(bookDto);
                stockServiceUtils.reduceQuantityInStock(bookDto);
            } else {
                logger.info("Payment not confirmed. The book will not be sent.");
            }
        }else{
            logger.info("The book is not available in stock.");
            throw new GenericNotFoundException("The book is not available in stock.");
        }
    }

    private void updateStatusSend(BookDto bookDto){
        logger.info("The status of book was updated with ID: " + bookDto.getId());
    }
}
