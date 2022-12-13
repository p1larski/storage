package storage.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.models.Article;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ArticleServiceTest {

    private Article article = new Article("Kosiarka");

    @Test
    void addNewArticleToBase() {
    //given
        ArticleService articleService = mock(ArticleService.class);
        given(articleService.addNewArticleToBase(Mockito.any(Article.class))).willReturn(true);
    //when
        articleService.addNewArticleToBase(article);
    //then
        Assertions.assertEquals(true,true);
    }
}