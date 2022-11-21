package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Article;
import storage.models.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByArticle(Optional<Article> article);
    Product findByIdAndDateOfDelivery(Long id, LocalDate localDate);
}
