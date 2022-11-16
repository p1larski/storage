package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.models.Product;
import storage.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    private ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> showAllProducts (){
        List<Product> allProducts = (List<Product>) productRepository.findAll();
        return allProducts;
    }

    public String addNewProduct(Product product){
        productRepository.save(product);
        return "successfuly added product";
    }
}
