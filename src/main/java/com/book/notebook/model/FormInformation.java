package com.book.notebook.model;

import com.book.notebook.entity.Author;
import com.book.notebook.entity.Book;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FormInformation {

    private List<String> genres;
    private List<String> tropes;
    private List<ExistentBook> books;
    private List<ExistentAuthor> authors;

    public FormInformation(List<Book> books,
                           List<Author> authors,
                           List<String> genres,
                           List<String> tropes) {
        this.genres = genres;
        this.tropes = tropes;
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();

        this.setExistentBooksFromBookList(books);
        this.setExistentAuthorsFromAuthorList(authors);
    }

    public void setExistentBooksFromBookList(List<Book> books) {
        books.forEach(book -> this.books.add(new ExistentBook(book.getId(), book.getTitle())));
    }

    public void setExistentAuthorsFromAuthorList(List<Author> authors) {
        authors.forEach(author -> this.authors.add(new ExistentAuthor(author.getId(), author.getFullname())));
    }
}
