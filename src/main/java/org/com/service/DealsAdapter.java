package org.com.service;

import lombok.AllArgsConstructor;
import org.com.dto.DealDTO;
import org.com.model.Deal;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DealsAdapter {

    public Deal map(DealDTO dealDTO) {
        Deal deal = new Deal();
        deal.setDealUniqueId(dealDTO.getDealUniqueId());
        deal.setToCurrencyISOCode(dealDTO.getToCurrencyISOCode());
        deal.setFromCurrencyISOCode(dealDTO.getFromCurrencyISOCode());
        deal.setDealTimestamp(dealDTO.getDealTimestamp());
        deal.setDealAmount(dealDTO.getDealAmount());
        return deal;
    }
}
