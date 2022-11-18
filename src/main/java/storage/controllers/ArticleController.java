package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import storage.models.Article;
import storage.models.Product;
import storage.services.ArticleService;

import java.util.List;

@RestController
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    private ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("/article/new")
    public String addNewArticleToBase(@RequestBody Article article){
        if (articleService.addNewArticleToBase(article)){
            return "Successfully added new article to database";
        } else {
            return "This article exist in database";
        }
    }

    @GetMapping("/articles")
    public List<Article> showAllArticles(){
        return articleService.showAllArticles();
    }
}
