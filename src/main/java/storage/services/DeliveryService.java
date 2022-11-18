package storage.services;

import org.springframework.stereotype.Service;
import storage.models.Delivery;
import storage.models.Product;
import storage.repositories.DeliveryRepository;
import storage.repositories.EmployeeRepository;
import storage.security.SecurityUtils;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryService {

    private DeliveryRepository deliveryRepository;
    private ProductService productService;
    private EmployeeRepository employeeRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, ProductService productService, EmployeeRepository employeeRepository) {
        this.deliveryRepository = deliveryRepository;
        this.productService = productService;
        this.employeeRepository = employeeRepository;
    }

    public boolean finishDelivery(List<Product> productsInDelivery){
        Delivery newDelivery = new Delivery();
        newDelivery.setDateOfDelivery(LocalDate.now());
        newDelivery.setDeliveredProducts(productsInDelivery);
        newDelivery.setAmountOfProductsInDelivery(productsInDelivery.stream().count());
        newDelivery.setResponsibleEmployee(employeeRepository.findEmployeeByUsername(SecurityUtils.getUserName()));
        deliveryRepository.save(newDelivery);
        productsInDelivery.stream().forEach(product -> product.setDelivery(newDelivery));
        productsInDelivery.stream().forEach(product -> product.setDateOfDelivery(newDelivery.getDateOfDelivery()));
        productsInDelivery.stream().forEach(product -> {
            try {
                productService.addNewProduct(product);
            } catch (NullPointerException e) {
                throw new NullPointerException("There is no article of this type in database");
            }
        });
        return true;
    }

    public List<Delivery> findAllDelivers(){
        return (List<Delivery>) deliveryRepository.findAll();
    }
}
