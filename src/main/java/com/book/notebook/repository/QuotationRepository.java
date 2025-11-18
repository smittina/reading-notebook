package com.book.notebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.notebook.entity.Quotation;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> {

}
