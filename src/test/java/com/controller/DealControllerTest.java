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
    private DealsAdapter dealsAdapter;
    @Mock
    private DealValidator dealValidator;
    @InjectMocks
    private DealController dealController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    public void testAddDeal_Success() {
//        DealDTO dealDTO = new DealDTO();
//        ArgumentCaptor<Deal> dealCaptor = ArgumentCaptor.forClass(Deal.class);
//        ResponseEntity<String> response = dealController.addDeal(dealDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Deal saved successfully", response.getBody());
//        verify(dealValidator, times(1)).isValidCurrencyISOCode(dealDTO);
//        verify(dealsAdapter, times(1)).map(dealDTO);
//        verify(dealService, times(1)).saveDeal(dealCaptor.capture());
//        assertNotNull(dealCaptor.getValue());
//    }

    @Test
    public void testAddDeal_DuplicateDealException() {
        DealDTO dealDTO = new DealDTO();
        doThrow(new DuplicateDealException("Duplicate deal")).when(dealValidator).isValidCurrencyISOCode(dealDTO);
        ResponseEntity<String> response = dealController.addDeal(dealDTO);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Duplicate deal", response.getBody());
        verify(dealValidator, times(1)).isValidCurrencyISOCode(dealDTO);
        verify(dealsAdapter, never()).map(any());
        verify(dealService, never()).saveDeal(any());
    }
}
