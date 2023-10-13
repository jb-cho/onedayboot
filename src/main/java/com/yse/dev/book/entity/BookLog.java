package com.yse.dev.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookLogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "TEXT")
    private String comment;

    private Integer page;

    @CreationTimestamp
    private LocalDateTime insertDateTime;
}
