package com.cucupang.first_ship.entity;

import com.cucupang.first_ship.enu.SearchYn;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="keyword")
@EntityListeners(AuditingEntityListener.class)
public class Keyword {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long seq;
    private String keyword;
    @Enumerated(EnumType.STRING)
    private SearchYn searchYn;

    @CreatedDate
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime modTime;

    @Builder
    public Keyword(String keyword , SearchYn searchYn){
        this.keyword = keyword;
        this.searchYn = searchYn;
    }

}
