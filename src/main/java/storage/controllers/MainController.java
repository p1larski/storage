package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import storage.models.Article;
import storage.services.*;

@Controller
public class MainController {

    private ProductService productService;
    private ArticleService articleService;
    private DeliveryService deliveryService;
    private EmployeeService employeeService;
    private ReleaseService releaseService;

    @Autowired
    public MainController(ProductService productService,
                          ArticleService articleService,
                          DeliveryService deliveryService,
                          EmployeeService employeeService,
                          ReleaseService releaseService) {
        this.productService = productService;
        this.articleService = articleService;
        this.deliveryService = deliveryService;
        this.employeeService = employeeService;
        this.releaseService = releaseService;
    }
    @GetMapping
    @RequestMapping(value = "/product/all")
    public String allProducts(Model model){
        model.addAttribute("products", productService.showAllProducts());
        return "product";
    }
    @GetMapping
    @RequestMapping(value = "/article/all")
    public String allArticles(Model model){
        model.addAttribute("articles", articleService.showAllArticles());
        return "article";
    }
    @GetMapping
    @RequestMapping(value = "/article/new")
    public String addArticle(Model model){
        model.addAttribute("article", new Article());
        return "newArticle";
    }
    @PostMapping
    @RequestMapping(value = "/article/save")
    public String saveArticle(@ModelAttribute Article article){
        articleService.addNewArticleToBase(article);
        return "newArticle";
    }
}
