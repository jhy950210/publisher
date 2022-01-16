package com.book.publisher.repository;

import com.book.publisher.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

    /*@Query("select sum(ob.count) from OrderBook ob where ob.id =: orderId")
    int sumPrice(@Param("orderId") Long orderId);*/
}
