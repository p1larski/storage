package storage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import storage.ModelDTOs.DeliveryDto;
import storage.ModelDTOs.ProductDto;
import storage.models.Product;
import storage.services.DeliveryService;
import storage.services.ProductService;

import java.util.List;

@RestController
public class DeliveryController {

    private DeliveryService deliveryService;
    private ProductService productService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService, ProductService productService){
        this.deliveryService = deliveryService;
        this.productService = productService;
    }

    @PostMapping("/delivery/new")
    public String newDelivery(@RequestBody List<ProductDto> listOfProductsInDelivery) throws NullPointerException{
        deliveryService.finishDelivery(listOfProductsInDelivery);
            return "Delivery completed";
    }

    @GetMapping("/delivery/all")
    public List<DeliveryDto> findAllDelivers(){
        return deliveryService.findAllDelivers();
    }

}
