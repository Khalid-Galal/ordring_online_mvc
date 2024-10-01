package org.mycode.ordring_online_mvc.service;

import org.mycode.ordring_online_mvc.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImp implements OrderService{


    @Override
    public List<Order> getAllOrders() {

        return List.of();
    }
}
