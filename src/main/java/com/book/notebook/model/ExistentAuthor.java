package com.book.notebook.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExistentAuthor {

    private Long id;
    private String name;
    private List<ExistentBook> books;

    public ExistentAuthor(Long id, String name) {
        this.id = id;
        this.name = name;
        books = new ArrayList<>();
    }

}
