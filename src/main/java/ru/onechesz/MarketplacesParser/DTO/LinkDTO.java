package ru.onechesz.MarketplacesParser.DTO;

import lombok.Getter;
import lombok.Setter;

public class LinkDTO {
    @Getter
    @Setter
    private String url;
    @Getter
    @Setter
    private PricesDTO pricesDTO;
}
