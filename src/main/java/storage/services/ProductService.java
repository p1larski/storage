package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.models.Article;
import storage.models.Product;
import storage.repositories.ArticleRepository;
import storage.repositories.ProductRepository;

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

    public List<Product> showAllProducts (){
        List<Product> allProducts = (List<Product>) productRepository.findAll();
        return allProducts;
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

    public List<Product> findAllByArticleName(String name){
        Optional<Article> article = articleRepository.getArticleByName(name);
        return productRepository.findAllByArticle(article);
    }
}
