package com.alaa.orderservice.api.common;

import com.alaa.orderservice.api.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponse {

    private Order order ;
    private  double amount ;


    private  String tansactionId ;
    private  String message ;
}
