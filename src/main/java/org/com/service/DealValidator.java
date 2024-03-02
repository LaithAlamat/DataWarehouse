package org.com.service;

import lombok.AllArgsConstructor;
import org.com.dto.DealDTO;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
@AllArgsConstructor
public class DealValidator {
    public void isValidCurrencyISOCode(DealDTO dealDTO) {
        Currency.getInstance(dealDTO.getFromCurrencyISOCode());
        Currency.getInstance(dealDTO.getToCurrencyISOCode());
    }
}
