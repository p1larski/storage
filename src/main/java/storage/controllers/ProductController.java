package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import storage.ModelDTOs.ProductDto;
import storage.excel.JExcelHelper;
import storage.models.Product;
import storage.services.ProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product/new")
    public String addNewProduct(@RequestBody ProductDto productDto) throws Exception{
        productService.addNewProduct(productDto);
            return "successfully added product";

    }
    @GetMapping("/products")
    public List<ProductDto> showAllProducts(){
        return productService.showAllProducts();
    }

    @GetMapping("/products/{article}")
    public List<ProductDto> showAllProductsByArticleName(@PathVariable String article) {
        return productService.findAllByArticleName(article);
    }

    @GetMapping("/products/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ProductDto> listProducts = productService.showAllProducts();

            JExcelHelper excelExporter = new JExcelHelper(listProducts);

            excelExporter.export(response);
            }
}
