package ru.onechesz.MarketplacesParser.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.onechesz.MarketplacesParser.DTO.PricesDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class PricesEntity {
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "link_id")
    @Id
    private LinkEntity linkId;
    @Getter
    @Setter
    @Column(name = "lowest_price")
    private BigDecimal lowestPrice;
    @Getter
    @Setter
    @Column(name = "median_price")
    private BigDecimal medianPrice;
    @Getter
    @Setter
    @Column(name = "average_price")
    private BigDecimal averagePrice;
    @Getter
    @Setter
    @Column(name = "highest_price")
    private BigDecimal highestPrice;
    @Getter
    @Setter
    @Column(name = "parsed_at")
    private LocalDateTime parsedAt;

    public PricesDTO convertToPricesDTO() {
        PricesDTO pricesDTO = new PricesDTO();

        pricesDTO.setLowestPrice(lowestPrice);
        pricesDTO.setMedianPrice(medianPrice);
        pricesDTO.setAveragePrice(averagePrice);
        pricesDTO.setHighestPrice(highestPrice);
        pricesDTO.setParsedAt(parsedAt);

        return pricesDTO;
    }
}
