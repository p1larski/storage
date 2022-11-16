package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAllByName(String name);
}
