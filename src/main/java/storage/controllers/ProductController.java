package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import storage.models.Product;
import storage.services.ProductService;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/new")
    public String addNewProduct(@RequestBody Product product){
        productService.addNewProduct(product);
        return "successfully added product";
    }

    @GetMapping("/products")
    public List<Product> showAllProducts(){
        return productService.showAllProducts();
    }
}
