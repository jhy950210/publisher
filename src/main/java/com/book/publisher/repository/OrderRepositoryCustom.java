package com.book.publisher.repository;

import com.book.publisher.entity.Order;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> findAllOrders();
}
