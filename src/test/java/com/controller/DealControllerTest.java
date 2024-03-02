package com.controller;

import org.com.controller.DealController;
import org.com.dto.DealDTO;
import org.com.exception.DuplicateDealException;
import org.com.model.Deal;
import org.com.service.DealService;
import org.com.service.DealValidator;
import org.com.service.DealsAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DealControllerTest {

    @Mock
    private DealService dealService;

    @Mock
    private DealValidator dealValidator;

    @Mock
    private DealsAdapter dealsAdapter;

    @InjectMocks
    private DealController dealController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddDeal_Success() {
        DealDTO dealDTO = new DealDTO();
        Deal deal = new Deal();

        when(dealsAdapter.map(dealDTO)).thenReturn(deal);
        when(dealValidator.isValidCurrencyISOCode(dealDTO)).thenReturn(true);

        ResponseEntity<String> response = dealController.addDeal(dealDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deal saved successfully", response.getBody());
        verify(dealsAdapter, times(1)).map(dealDTO);
        verify(dealValidator, times(1)).isValidCurrencyISOCode(dealDTO);
        verify(dealService, times(1)).saveDeal(deal);
    }

    @Test
    public void testAddDeal_DuplicateDealException() {
        DealDTO dealDTO = new DealDTO();

        doThrow(new DuplicateDealException("Duplicate deal")).when(dealValidator).isValidCurrencyISOCode(dealDTO);

        ResponseEntity<String> response = dealController.addDeal(dealDTO);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Failed to save deal: Duplicate deal", response.getBody());
        verify(dealValidator, times(1)).isValidCurrencyISOCode(dealDTO);
        verifyNoInteractions(dealsAdapter);
        verifyNoInteractions(dealService);
    }

    @Test
    public void testAddDeal_InternalServerError() {
        DealDTO dealDTO = new DealDTO();

        doThrow(new RuntimeException("Some error")).when(dealValidator).isValidCurrencyISOCode(dealDTO);

        ResponseEntity<String> response = dealController.addDeal(dealDTO);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Unexpected error occurred: Some error", response.getBody());
        verify(dealValidator, times(1)).isValidCurrencyISOCode(dealDTO);
        verifyNoInteractions(dealsAdapter);
        verifyNoInteractions(dealService);
    }
}