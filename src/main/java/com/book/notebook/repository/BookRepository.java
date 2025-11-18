package com.book.notebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

    Book findById(long id);

    List<Book> findAllByIdAuthor(int idAuthor);

}
