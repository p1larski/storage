package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import storage.ModelDTOs.ProductDto;
import storage.models.Product;
import storage.security.SecurityUtils;
import storage.services.ProductService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/new")
    public String addNewProduct(@RequestBody Product product) throws Exception{
        if (productService.addNewProduct(product) == true){
            return "successfully added product";
        }
        return "Choose type of article and try again";
    }

    @GetMapping("/products")
    public List<ProductDto> showAllProducts(){
        return productService.showAllProducts();
    }

    @GetMapping("/products/{article}")
    public List<ProductDto> showAllProductsByArticleName(@PathVariable String article) {
        return productService.findAllByArticleName(article);
    }
}
