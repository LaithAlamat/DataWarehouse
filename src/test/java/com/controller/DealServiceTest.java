package com.controller;

import org.com.exception.DuplicateDealException;
import org.com.model.Deal;
import org.com.repository.DealRepository;
import org.com.service.DealService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealServiceTest {
    @Mock
    private DealRepository dealRepository;
    @InjectMocks
    private DealService dealService;

    @Test
    public void testSaveDeal_SuccessfulSave() {
        Deal deal = new Deal();
        deal.setDealUniqueId("uniqueId");
        when(dealRepository.existsByDealUniqueId("uniqueId")).thenReturn(false);
        dealService.saveDeal(deal);
        verify(dealRepository, times(1)).save(deal);
    }

    @Test
    public void testSaveDeal_DuplicateDealException() {
        Deal deal = new Deal();
        deal.setDealUniqueId("duplicateId");
        when(dealRepository.existsByDealUniqueId("duplicateId")).thenReturn(true);
        assertThrows(DuplicateDealException.class, () -> {
            dealService.saveDeal(deal);
        });

        verify(dealRepository, never()).save(deal);
    }

    @Test
    public void testSaveDeal_NullDeal() {
        assertThrows(NullPointerException.class, () -> {
            dealService.saveDeal(null);
        });

        verify(dealRepository, never()).save(any());
    }
}
