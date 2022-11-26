package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import storage.ModelDTOs.ProductDto;
import storage.models.Article;
import storage.models.DeliveryWrapper;
import storage.services.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

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

    @GetMapping
    @RequestMapping(value = "/delivery/new")
    public String newDelivery(Model model){
        model.addAttribute("articles", articleService.showAllArticles());
        model.addAttribute("wrapper", new DeliveryWrapper());
        return "newDelivery";
    }

    @PostMapping(value = "/delivery/map/new")
    public String saveDeliveryWithMap(@ModelAttribute DeliveryWrapper wrapper) {
        HashMap<Integer, ProductDto> productsAmount = new HashMap<>();
        Integer[] arr = wrapper.treeSetOfNumbers.toArray(new Integer[wrapper.treeSetOfProducts.size()]);
        String[] arr2 = wrapper.treeSetOfProducts.toArray(new String[wrapper.treeSetOfProducts.size()]);
        for (int i = 0; i < wrapper.treeSetOfProducts.size(); i++){
            productsAmount.put(arr[i],new ProductDto(arr2[i]));
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        productsAmount.entrySet().forEach(stringProductDtoEntry -> {
            for (int a = stringProductDtoEntry.getKey(); a>0; a--){
                productDtoList.add(stringProductDtoEntry.getValue());
            }
        });
        deliveryService.finishDelivery(productDtoList);
        return "redirect:/product/all";
    }
}
