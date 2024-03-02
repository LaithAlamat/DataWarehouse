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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/v1/deals")
@AllArgsConstructor
public class DealController {

    private static final Logger logger = LogManager.getLogger(DealController.class);

    private final DealService dealService;
    private final DealValidator dealValidator;
    private final DealsAdapter dealsAdapter;

    @PostMapping("/add")
    public ResponseEntity<String> addDeal(@RequestBody DealDTO dealDTO) {
        try {
            dealValidator.isValidCurrencyISOCode(dealDTO);
            Deal deal = dealsAdapter.map(dealDTO);
            dealService.saveDeal(deal);
            logger.info("Deal saved successfully");
            return ResponseEntity.ok("Deal saved successfully");
        } catch (DuplicateDealException e) {
            String errorMessage = "Failed to save deal: " + e.getMessage();
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        } catch (Exception e) {
            String errorMessage = "Unexpected error occurred: " + e.getMessage();
            logger.error(errorMessage, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }
}