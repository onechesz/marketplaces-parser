package ru.onechesz.MarketplacesParser.Controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.onechesz.MarketplacesParser.DTO.ProductDTO;
import ru.onechesz.MarketplacesParser.Services.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public String index(@NotNull Model model) {
        List<ProductDTO> productDTOS = productService.findAll();

        model.addAttribute("products", productDTOS);

        return "product/index";
    }

    @GetMapping("/{id}")
    public String product(@PathVariable("id") int id, @NotNull Model model) {
        Optional<ProductDTO> productDTOOptional = productService.findById(id);

        if (productDTOOptional.isEmpty())
            return "redirect:/products";

        ProductDTO productDTO = productDTOOptional.get();

        model.addAttribute("product", productDTO);

        return "product/product";
    }
}
