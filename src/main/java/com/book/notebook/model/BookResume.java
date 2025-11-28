package com.book.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookResume {

    private Long readingId;
    private Long bookId;
    private Long authorId;
    private String bookTitle;
    private String bookAuthor;
    private float rating;
}
