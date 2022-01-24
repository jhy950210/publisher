package com.book.publisher.api;

import com.book.publisher.entity.Address;
import com.book.publisher.entity.Order;
import com.book.publisher.repository.OrderRepository;
import com.book.publisher.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity getOrders(){
        List<Order> orders = orderService.getOrders();

        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity getOrderById(@PathVariable("id") Long id){
        Order order = orderService.getOrder(id);

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping("/order")
    public ResponseEntity createOrder(@RequestParam("memberId") Long memberId,
                                      @RequestParam("bookId") Long bookId,
                                      @RequestParam("count") int count,
                                      @RequestParam(required = false, value = "address") Address address)
    {
        if(address != null){
            Order order = orderService.order(memberId, address, bookId, count);

            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        }

        Order order = orderService.order(memberId, bookId, count);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping("/order/cancel/{id}")
    public ResponseEntity cancelOrder(@PathVariable("id") Long orderId){
        orderService.cancelOrder(orderId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
