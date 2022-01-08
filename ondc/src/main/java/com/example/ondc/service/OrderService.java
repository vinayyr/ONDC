package com.example.ondc.service;

import com.example.ondc.model.OrderDetails;
import com.example.ondc.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    public void insertOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }
}
