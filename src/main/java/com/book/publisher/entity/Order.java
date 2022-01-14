package com.book.publisher.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
public class Order extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderBook(OrderBook orderBook) {
        this.orderBooks.add(orderBook);
        orderBook.setOrder(this);
    }

    // order 생성
    public static Order createOrder(Member member, OrderBook... orderBooks){
        Order order = new Order();
        order.setOrderStatus(OrderStatus.ORDER);
        order.setMember(member);

        for (OrderBook orderBook : orderBooks) {
            order.addOrderBook(orderBook);
        }

        return order;
    }

    // order 취소
    public void cancel(){
        this.setOrderStatus(OrderStatus.CANCEL);

        for (OrderBook orderBook : orderBooks) {
            orderBook.cancel();
        }
    }

    // 총 주문 가격 조회
    public int getTotalPrice(){
        int totalPrice = 0;

        for (OrderBook orderBook : orderBooks) {
            totalPrice += orderBook.getTotalPrice();

        }

        return totalPrice;
    }
}
