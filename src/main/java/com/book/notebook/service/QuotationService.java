package com.book.notebook.service;

import com.book.notebook.entity.Quotation;
import com.book.notebook.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service linked to Quotation Repository
 */
@Service
public class QuotationService {

    @Autowired
    private QuotationRepository quotationRepository;

    /**
     * Get all Quotations for a specific book
     * @param bookId book Id
     * @return list of quotations for a specific book
     */
    public List<Quotation> getAllByBookId(Long bookId) {
        return quotationRepository.findAllByBookId(bookId);
    }

    /**
     * Insert a list of new Quotation Entities in database
     * @param quotes list of quotes
     * @param idBook book Id
     * @return list of new quotations saved
     */
    public List<Quotation> createNewQuotations(List<String> quotes, Long idBook) {
        List<Quotation> newQuotations = new ArrayList<>();
        for (String quoteName : quotes) {
            Quotation quotation = new Quotation();
            quotation.setBookId(idBook);
            quotation.setQuoteText(quoteName);
            newQuotations.add(quotation);
        }
        return quotationRepository.saveAll(newQuotations);
    }
}
