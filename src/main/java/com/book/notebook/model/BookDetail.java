package com.book.notebook.model;

import com.book.notebook.entity.*;
import com.book.notebook.enumeration.StatusOfReading;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model for BookDetail
 */
@Getter
@Setter
@NoArgsConstructor
public class BookDetail {

    // General
    private String title;
    private String authorName;
    private byte[] cover;
    private String synopsis;
    private List<String> genres;
    private List<String> tropes;
    private int pageNumber;
    // Saga
    private int numberOfTome;
    // Reading infos
    private String status;
    private String typeOfReading;
    private LocalDateTime starting;
    private LocalDateTime finished;
    private List<String> quotations;
    private int currentPage;

    public BookDetail(Reading reading,
                      Book book,
                      Author author,
                      List<Genre> genres,
                      List<Trope> tropes,
                      List<Quotation> quotations) {

        // General
        this.title = book.getTitle();
        this.authorName = author.getFullname();
        this.cover = book.getCover();
        this.synopsis = book.getSynopsis();
        this.genres = genres.stream().map(Genre::getTitle).toList();
        this.tropes = tropes.stream().map(Trope::getTitle).toList();
        this.pageNumber = reading.getPageNumber();
        // Saga
        this.numberOfTome = book.getNumberOfTome();
        // Reading information
        this.status = reading.getStatusOfReading().getStatus();
        this.typeOfReading = reading.getTypeOfReading().getType();
        if (status != StatusOfReading.IN_PROGRESS.getStatus()) {
            this.starting = reading.getStarting();
            this.finished = reading.getFinished();
        } else {
            this.starting = reading.getStarting();
            this.currentPage = reading.getCurrentPage();
        }
        this.quotations = quotations.stream().map(Quotation::getQuoteText).toList();
    }

}
