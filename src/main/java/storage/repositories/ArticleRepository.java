package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Article;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
}
