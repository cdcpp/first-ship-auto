package com.cucupang.first_ship.entity;

import com.cucupang.first_ship.enu.WriteYn;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="goods")
@EntityListeners(AuditingEntityListener.class)
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String category;
    private String name;
    private int price;
    @Enumerated(EnumType.STRING)
    private WriteYn writeYn;

    @CreatedDate
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime modTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_seq")
    private Keyword keyword;

}
