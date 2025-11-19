package com.book.notebook.model;

import com.book.notebook.entity.Author;
import com.book.notebook.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FormInformation {

    private List<String> genres;
    private List<String> tropes;
    private Map<Long, String> books;
    private Map<Long, String> authors;

    public FormInformation(List<Book> books,
                           List<Author> authors,
                           List<String> genres,
                           List<String> tropes) {
        this.genres = genres;
        this.tropes = tropes;
        this.books = new HashMap<>();
        this.authors = new HashMap<>();

        setBooks(books);
        setAuthors(authors);
    }

    public void setBooks(List<Book> books) {
        books.forEach(book -> {
           this.books.put(book.getId(), book.getTitle());
        });
    }

    public void setBooks(Map<Long, String> books) {
        this.books = books;
    }

    public void setAuthors(List<Author> authors) {
        authors.forEach(author -> {
            this.authors.put(author.getId(), author.getFullname());
        });
    }

    public void setAuthors(Map<Long, String> authors) {
        this.authors = authors;
    }
}
