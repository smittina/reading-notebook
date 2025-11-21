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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "year_of_reading")
    private int yearOfReading;

    @Column(name = "month_of_reading")
    private int monthOfReading;

    @Column(name = "starting", nullable = false)
    private LocalDateTime starting;

    @Column(name = "finished")
    private LocalDateTime finished;

    @Column(name = "status_of_reading", nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusOfReading statusOfReading;

    @Column(name = "type_of_reading", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfReading typeOfReading;

    @Column(name = "page_number")
    private int pageNumber;

    @Column(name = "current_page")
    private int currentPage;

    @Column(name = "rating")
    private float rating;

}
