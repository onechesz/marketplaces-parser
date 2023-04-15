package ru.onechesz.MarketplacesParser.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.onechesz.MarketplacesParser.DTO.LinkDTO;
import ru.onechesz.MarketplacesParser.DTO.PricesDTO;
import ru.onechesz.MarketplacesParser.DTO.ProductDTO;
import ru.onechesz.MarketplacesParser.Entities.LinkEntity;
import ru.onechesz.MarketplacesParser.Entities.ProductEntity;
import ru.onechesz.MarketplacesParser.Repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        List<ProductDTO> productDTOS = new ArrayList<>();

        for (ProductEntity productEntity : productEntities) {
            List<LinkDTO> linkDTOS = new ArrayList<>();

            for (LinkEntity linkEntity : productEntity.getLinkEntities()) {
                PricesDTO pricesDTO = linkEntity.getPricesEntity().convertToPricesDTO();
                LinkDTO linkDTO = linkEntity.convertToLinkDTO(pricesDTO);

                linkDTOS.add(linkDTO);
            }

            productDTOS.add(productEntity.convertToProductDTO(linkDTOS));
        }

        return productDTOS;
    }

    public Optional<ProductDTO> findById(int id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);

        if (productEntity.isPresent()) {
            ProductDTO productDTO = new ProductDTO();
            List<LinkDTO> linkDTOS = new ArrayList<>();

            for (LinkEntity linkEntity : productEntity.get().getLinkEntities()) {
                PricesDTO pricesDTO = linkEntity.getPricesEntity().convertToPricesDTO();
                LinkDTO linkDTO = linkEntity.convertToLinkDTO(pricesDTO);

                linkDTOS.add(linkDTO);
            }

            productDTO.setLinkDTOS(linkDTOS);

            return Optional.of(productDTO);
        }

        return Optional.empty();
    }
}
