package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.ModelDTOs.ProductDto;
import storage.models.Article;
import storage.models.Delivery;
import storage.models.Product;
import storage.repositories.ArticleRepository;
import storage.repositories.DeliveryRepository;
import storage.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ArticleRepository articleRepository;
    private DeliveryRepository deliveryRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, ArticleRepository articleRepository, DeliveryRepository deliveryRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.deliveryRepository = deliveryRepository;
    }

    public ProductDto mapProductToProductDto(Product product){
        if (product.getRelease() != null) {
            ProductDto productDto = new ProductDto(
                    product.getId(),
                    product.getArticle().getName(),
                    product.getArticle().getAmountOfArticlesInStorage(),
                    product.getDelivery().getId(),
                    product.getDelivery().getDateOfDelivery(),
                    product.getDelivery().getResponsibleEmployee().getUsername(),
                    product.getRelease().getDateOfRelease(),
                    product.getRelease().getResponsibleEmployee().getUsername());
            return productDto;
        } else {
            ProductDto productDto = new ProductDto(
                    product.getId(),
                    product.getArticle().getName(),
                    product.getArticle().getAmountOfArticlesInStorage(),
                    product.getDelivery().getId(),
                    product.getDelivery().getDateOfDelivery(),
                    product.getDelivery().getResponsibleEmployee().getUsername(),
                    null,
                    null);
            return productDto;
        }
    }

    public List<ProductDto> showAllProducts (){
        List<ProductDto> listOfProductsDto = new ArrayList<>();
        List<Product> allProductsList = (List<Product>) productRepository.findAll();
        allProductsList.stream().forEach(product -> listOfProductsDto.add(mapProductToProductDto(product)));
        return listOfProductsDto;
    }

    public Product addNewProduct(ProductDto productDto){
        Optional<Article> articleOptional = articleRepository.getArticleByName(productDto.getArticleName());
        if(articleOptional.isPresent()) {
            Article article = articleOptional.stream().findFirst().orElse(null);
            Long amountOfArticles = article.getAmountOfArticlesInStorage();
            article.setAmountOfArticlesInStorage(amountOfArticles + 1L);
            Product product = new Product();
            product.setArticle(article);
            productRepository.save(product);
            return product;
        }
        return null;
    }

    public List<ProductDto> findAllByArticleName(String name){
        Optional<Article> article = articleRepository.getArticleByName(name);
        if (article.isPresent()) {
            List<ProductDto> productDtoListByArticle = new ArrayList<>();
            productRepository.findAllByArticle(article).stream().
                    forEach(product -> productDtoListByArticle.add(mapProductToProductDto(product)));
            return productDtoListByArticle;
        } else {
            return null;
        }
    }
}
