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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_author", nullable = true)
    private Long idAuthor;

    @Column(name = "title", nullable = true)
    private String title;

    @Column(name = "cover", nullable = true)
    private byte[] cover;

    @Column(name = "synopsis", nullable = true)
    private String synopsis;

    @Column(name = "saga", nullable = true)
    private boolean saga;

    @Column(name = "all_tome_published", nullable = true)
    private boolean allTomePublished;

    @Column(name = "number_of_tome", nullable = true)
    private int numberOfTome;

}
