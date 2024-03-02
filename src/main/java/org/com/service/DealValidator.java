package org.com.service;

import lombok.AllArgsConstructor;
import org.com.dto.DealDTO;
import org.springframework.stereotype.Component;

import java.util.Currency;

@Component
@AllArgsConstructor
public class DealValidator {
    public boolean isValidCurrencyISOCode(DealDTO dealDTO) {
        Currency.getInstance(dealDTO.getFromCurrencyISOCode());
        Currency.getInstance(dealDTO.getToCurrencyISOCode());
        return true;
    }
}
