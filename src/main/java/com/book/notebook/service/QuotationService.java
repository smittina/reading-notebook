package com.book.notebook.service;

import com.book.notebook.entity.Quotation;
import com.book.notebook.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    public List<Quotation> getAllByBookId(Long bookId) {
        return quotationRepository.findAllByBookId(bookId);
    }
}
