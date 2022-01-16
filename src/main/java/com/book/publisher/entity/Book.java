package com.book.publisher.entity;


import com.book.publisher.exception.NotEnoughStockException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@DynamicInsert
@NoArgsConstructor
public class Book extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "book_id")
    private long id;

    private String bookTitle;

    private String subTitle;

    private int price;
    private String author;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    private LocalDate publishDate;

    /*@CreatedDate
    @Column(columnDefinition = "timestamp(0)")
    private Date regDt;*/

    // 수량
    private int stockQuantity;

    @Builder
    public Book(String bookTitle, String subTitle, int price, String author, LocalDate publishDate, int stockQuantity) {
        this.bookTitle = bookTitle;
        this.subTitle = subTitle;
        this.price = price;
        this.author = author;
        this.publishDate = publishDate;
        this.stockQuantity = stockQuantity;
    }

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0){
            throw new NotEnoughStockException();
        }

        this.stockQuantity = restStock;
    }
}
