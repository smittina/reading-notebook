package com.book.notebook.entity;

import java.time.LocalDateTime;

import com.book.notebook.enumeration.StatusOfReading;
import com.book.notebook.enumeration.TypeOfReading;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Reading Entity
 */
@Entity
@Table(name = "reading")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "id_book", nullable = false)
    private Long idBook;

    @Column(name = "year_of_reading", nullable = false)
    private int yearOfReading;

    @Column(name = "month_of_reading", nullable = false)
    private int monthOfReading;

    @Column(name = "starting", nullable = false)
    private LocalDateTime starting;

    @Column(name = "finished", nullable = true)
    private LocalDateTime finished;

    @Column(name = "status_of_reading", nullable = true)
    @Enumerated(EnumType.STRING)
    private StatusOfReading statusOfReading;

    @Column(name = "type_of_reading", nullable = true)
    @Enumerated(EnumType.STRING)
    private TypeOfReading typeOfReading;

    @Column(name = "page_number", nullable = true)
    private int pageNumber;

    @Column(name = "current_page", nullable = true)
    private int currentPage;

    @Column(name = "rating")
    private float rating;

}
