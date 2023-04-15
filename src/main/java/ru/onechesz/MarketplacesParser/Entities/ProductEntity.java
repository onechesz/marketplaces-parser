package ru.onechesz.MarketplacesParser.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.onechesz.MarketplacesParser.DTO.LinkDTO;
import ru.onechesz.MarketplacesParser.DTO.ProductDTO;

import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Getter
    @Setter
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Getter
    @Setter
    @Column(name = "title")
    private String title;
    @Getter
    @Setter
    @OneToMany(mappedBy = "productEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<LinkEntity> linkEntities;

    public ProductDTO convertToProductDTO(List<LinkDTO> linkDTOS) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(id);
        productDTO.setTitle(title);
        productDTO.setLinkDTOS(linkDTOS);

        return productDTO;
    }
}
