package com.book.notebook.repository;

import com.book.notebook.entity.TropeList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * TropeList Repository
 */
public interface TropeListRepository extends JpaRepository<TropeList, Long> {
}
