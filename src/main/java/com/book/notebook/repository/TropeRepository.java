package com.book.notebook.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Trope;

@Repository
public interface TropeRepository extends JpaRepository<Trope, Long> {

        List<Trope> findByBookId(Long bookId);

        List<Trope> findAllByTitle(String title);


}
