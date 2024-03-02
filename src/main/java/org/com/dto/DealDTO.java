package org.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DealDTO {
    @NotBlank
    private String dealUniqueId;
    @NotBlank
    private String fromCurrencyISOCode;
    @NotBlank
    private String toCurrencyISOCode;
    @NotNull
    @PastOrPresent
    private LocalDateTime dealTimestamp;
    @NotNull
    @PositiveOrZero
    private BigDecimal dealAmount;
}

