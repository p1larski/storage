package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.models.Article;
import storage.repositories.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public boolean addNewArticleToBase (Article article){
        Optional<Article> articleOptional = articleRepository.getArticleByName(article.getName());
        if (!articleOptional.isPresent()) {
            article.setAmountOfArticlesInStorage(0L);
            articleRepository.save(article);
            return true;
        } else {
            return false;
        }
    }

    public List<Article> showAllArticles(){
        return (List<Article>) articleRepository.findAll();
    }
}
