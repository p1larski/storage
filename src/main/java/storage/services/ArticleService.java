package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.models.Article;
import storage.repositories.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;
    }

    public String addNewArticleToBase (Article article){
        articleRepository.save(article);
        return "successfully added new possible article";
    }

    public List<Article> showAllArticles(){
        return (List<Article>) articleRepository.findAll();
    }
}
