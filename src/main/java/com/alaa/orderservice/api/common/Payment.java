package com.alaa.orderservice.api.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {
    private  int id ;
    private String paymentStatus ;

    private String transactionId;
    private int orderID ;
    private double amount ;


}
