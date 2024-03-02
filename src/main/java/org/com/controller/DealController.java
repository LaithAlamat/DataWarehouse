package org.com.controller;

import lombok.AllArgsConstructor;
import org.com.dto.DealDTO;
import org.com.exception.DuplicateDealException;
import org.com.model.Deal;
import org.com.service.DealService;
import org.com.service.DealValidator;
import org.com.service.DealsAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/deals")
@AllArgsConstructor
public class DealController {

    private DealService dealService;
    private DealValidator dealValidator;
    private DealsAdapter dealsAdapter;

    @PostMapping("/add")
    public ResponseEntity<String> addDeal(@RequestBody DealDTO dealDTO) {
        try {
            dealValidator.isValidCurrencyISOCode(dealDTO);
            Deal deal = dealsAdapter.map(dealDTO);
            dealService.saveDeal(deal);
            return ResponseEntity.ok("Deal saved successfully");
        } catch (DuplicateDealException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}