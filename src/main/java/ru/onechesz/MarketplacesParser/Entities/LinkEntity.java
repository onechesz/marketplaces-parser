package ru.onechesz.MarketplacesParser.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.onechesz.MarketplacesParser.DTO.LinkDTO;
import ru.onechesz.MarketplacesParser.DTO.PricesDTO;

@Entity
@Table(name = "link")
public class LinkEntity {
    @Getter
    @Setter
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;
    @Getter
    @Setter
    @Column(name = "url")
    private String url;
    @Getter
    @Setter
    @OneToOne(mappedBy = "linkId", cascade = CascadeType.ALL)
    private PricesEntity pricesEntity;

    public LinkDTO convertToLinkDTO(PricesDTO pricesDTO) {
        LinkDTO linkDTO = new LinkDTO();

        linkDTO.setPricesDTO(pricesDTO);
        linkDTO.setUrl(url);

        return linkDTO;
    }
}
