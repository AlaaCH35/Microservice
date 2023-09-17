package com.alaa.orderservice.api.service;


import com.alaa.orderservice.api.common.Payment;
import com.alaa.orderservice.api.common.TransactionRequest;
import com.alaa.orderservice.api.common.TransactionResponse;
import com.alaa.orderservice.api.entity.Order;
import com.alaa.orderservice.api.repository.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class OrderService {
    Logger logger = LoggerFactory.getLogger(OrderService.class);
    @Autowired
   private OrderRepo orderRepo ;

    @Autowired
    @Lazy
    private RestTemplate template ;


    public TransactionResponse saveOrder(TransactionRequest transactionRequest) throws JsonProcessingException {
  String response = "";
  Order order = transactionRequest.getOrder();
        Payment payment = transactionRequest.getPayment();
        payment.setOrderID(order.getId());
        payment.setAmount(order.getPrice());
        // rest call (to map the order ID)
        logger.info("Order-service Request :" + new ObjectMapper().writeValueAsString(transactionRequest));
Payment paymentResponse = template.postForObject("http://localhost:8080/payment/doPayment",payment, Payment.class);

        assert paymentResponse != null;
        response =  paymentResponse.getPaymentStatus().equals("success")? "payment successfuly":"there is a failur in the cart" ;
        logger.info("Order Service getting Response from Payment-Service : "+new ObjectMapper().writeValueAsString(response));
        orderRepo.save(order);

        return  new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),response);

    }



}
