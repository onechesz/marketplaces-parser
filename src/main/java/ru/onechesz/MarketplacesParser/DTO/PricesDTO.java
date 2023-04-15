package ru.onechesz.MarketplacesParser.DTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PricesDTO {
    @Getter
    @Setter
    private BigDecimal lowestPrice;
    @Getter
    @Setter
    private BigDecimal medianPrice;
    @Getter
    @Setter
    private BigDecimal averagePrice;
    @Getter
    @Setter
    private BigDecimal highestPrice;
    @Getter
    @Setter
    private LocalDateTime parsedAt;
}
