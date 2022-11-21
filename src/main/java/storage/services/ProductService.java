package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.ModelDTOs.ProductDto;
import storage.models.Article;
import storage.models.Product;
import storage.repositories.ArticleRepository;
import storage.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private ArticleRepository articleRepository;

    @Autowired
    private ProductService(ProductRepository productRepository, ArticleRepository articleRepository){
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
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

    public boolean addNewProduct(Product product){
        if(product.getArticle()==null){
            return false;
        }
        Article article = articleRepository.getArticleById(product.getArticle().getId());
        Long amountOfArticles = article.getAmountOfArticlesInStorage();
        article.setAmountOfArticlesInStorage(amountOfArticles+1L);
        productRepository.save(product);
        return true;
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
