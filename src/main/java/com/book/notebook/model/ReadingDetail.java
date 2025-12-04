package com.book.notebook.model;

import com.book.notebook.entity.*;
import com.book.notebook.enumeration.StatusOfReading;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Model for ReadingDetail
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadingDetail {

    // General
    private long idBook;
    private String title;
    private String authorName;
    private long idAuthor;
    private byte[] cover;
    private String synopsis;
    private List<String> genres;
    private List<String> tropes;
    private int pageNumber;
    // Saga
    private boolean saga;
    private boolean allTomePublished;
    private int numberOfTome;
    // Reading infos
    private String status;
    private String typeOfReading;
    private LocalDateTime starting;
    private LocalDateTime finished;
    private int currentPage;
    private float rating;

    // Quotations
    private List<String> quotations;

    public ReadingDetail(Reading reading,
                         Book book,
                         Author author,
                         List<Genre> genres,
                         List<Trope> tropes,
                         List<Quotation> quotations) {

        // General
        this.title = book.getTitle();
        this.idBook = book.getId();
        this.authorName = author.getFullname();
        this.idAuthor = author.getId();
        this.cover = book.getCover();
        this.synopsis = book.getSynopsis();
        this.genres = genres.stream().map(Genre::getTitle).toList();
        this.tropes = tropes.stream().map(Trope::getTitle).toList();
        this.pageNumber = reading.getPageNumber();
        // Saga
        this.saga = book.isSaga();
        this.allTomePublished = book.isAllTomePublished();
        this.numberOfTome = book.getNumberOfTome();
        // Reading information
        this.status = reading.getStatusOfReading().getStatus();
        this.typeOfReading = reading.getTypeOfReading().getType();
        if (status != StatusOfReading.IN_PROGRESS.getStatus()) {
            this.starting = reading.getStarting();
            this.finished = reading.getFinished();
            this.rating = reading.getRating();
        } else {
            this.starting = reading.getStarting();
            this.currentPage = reading.getCurrentPage();
        }
        // Quotations
        this.quotations = quotations.stream().map(Quotation::getQuoteText).toList();
    }

}
