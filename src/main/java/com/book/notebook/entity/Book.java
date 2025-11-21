package com.book.notebook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "cover")
    private byte[] cover;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "saga")
    private boolean saga;

    @Column(name = "all_tome_published")
    private boolean allTomePublished;

    @Column(name = "number_of_tome")
    private int numberOfTome;

}
