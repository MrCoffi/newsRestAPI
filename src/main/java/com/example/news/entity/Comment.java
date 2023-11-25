package com.example.news.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;
    @ManyToOne()
    @JoinColumn(name = "news_id", referencedColumnName = "id", nullable = false)
    private News news;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
