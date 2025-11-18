package com.book.notebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{

    Author findById(long id);

    Author findByFullname(String fullname);
}
