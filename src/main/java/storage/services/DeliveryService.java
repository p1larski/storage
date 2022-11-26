package storage.services;

import org.springframework.stereotype.Service;
import storage.ModelDTOs.DeliveryDto;
import storage.ModelDTOs.ProductDto;
import storage.models.Delivery;
import storage.models.Product;
import storage.repositories.DeliveryRepository;
import storage.repositories.EmployeeRepository;
import storage.security.SecurityUtils;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public DeliveryDto mapDeliveryToDeliveryDto(Delivery delivery){
        DeliveryDto newDeliveryDto = new DeliveryDto();
                newDeliveryDto.setId(delivery.getId());
                newDeliveryDto.setDateOfDelivery(delivery.getDateOfDelivery());
                newDeliveryDto.setAmountOfProductsInDelivery(delivery.getDeliveredProducts().stream().count());
                List<ProductDto> productDtoListForAdd = new ArrayList<>();
                delivery.getDeliveredProducts().stream().forEach(product ->
                        productDtoListForAdd.add(productService.mapProductToProductDto(product)));
                newDeliveryDto.setProductsInDelivery(productDtoListForAdd);
                newDeliveryDto.setUsernameWhoReclaimed(delivery.getResponsibleEmployee().getUsername());
            return newDeliveryDto;
        }

    public boolean finishDelivery(List<ProductDto> productsInDelivery){
        Delivery newDelivery = new Delivery();
        newDelivery.setDateOfDelivery(LocalDate.now());
        newDelivery.setAmountOfProductsInDelivery(productsInDelivery.stream().count());
        newDelivery.setResponsibleEmployee(employeeRepository.findEmployeeByUsername(SecurityUtils.getUserName()));
        deliveryRepository.save(newDelivery);
        List<Product> productList = new ArrayList<>();
        productsInDelivery.stream().forEach(productDto -> {
            Product product = productService.addNewProduct(productDto);
            product.setDelivery(newDelivery);
            product.setDateOfDelivery(newDelivery.getDateOfDelivery());
            productList.add(product);
        });
        newDelivery.setDeliveredProducts(productList);
        deliveryRepository.save(newDelivery);
        return true;
    }

    public List<DeliveryDto> findAllDelivers(){
        List<DeliveryDto> allDeliversFinished = new ArrayList<>();
                deliveryRepository.findAll().forEach(delivery -> allDeliversFinished.add(
                mapDeliveryToDeliveryDto(delivery)));
        return  allDeliversFinished;
    }
}
