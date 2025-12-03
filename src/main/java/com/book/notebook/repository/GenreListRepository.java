package com.book.notebook.repository;

import com.book.notebook.entity.GenreList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * GenreList Repository
 */
public interface GenreListRepository extends JpaRepository<GenreList, Long> {

}
