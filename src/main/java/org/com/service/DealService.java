package org.com.service;

import lombok.AllArgsConstructor;
import org.com.exception.DuplicateDealException;
import org.com.model.Deal;
import org.com.repository.DealRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DealService {

    private DealRepository dealRepository;

    public Deal saveDeal(Deal deal) {
        if (deal != null && !dealRepository.existsByDealUniqueId(deal.getDealUniqueId())) {
            return dealRepository.save(deal);
        } else {
            throw new DuplicateDealException("Duplicate deal found with unique ID: " + deal.getDealUniqueId());
        }
    }
}
