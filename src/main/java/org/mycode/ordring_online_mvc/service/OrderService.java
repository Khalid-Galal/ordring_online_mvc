package org.mycode.ordring_online_mvc.service;

import org.mycode.ordring_online_mvc.entity.Order;

import java.util.List;

public interface OrderService {
    public List<Order> getAllOrders();
    public void saveAll(List<Order> orders);
}
