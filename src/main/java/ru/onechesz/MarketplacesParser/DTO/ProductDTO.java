package ru.onechesz.MarketplacesParser.DTO;

import lombok.Getter;
import lombok.Setter;
import ru.onechesz.MarketplacesParser.Entities.PricesEntity;

import java.util.List;

public class ProductDTO {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private List<LinkDTO> linkDTOS;
}
