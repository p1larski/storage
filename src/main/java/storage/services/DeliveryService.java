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

    public List<DeliveryDto> findAllDelivers(){
        List<DeliveryDto> allDeliversFinished = new ArrayList<>();
                deliveryRepository.findAll().forEach(delivery -> allDeliversFinished.add(
                mapDeliveryToDeliveryDto(delivery)));
        return  allDeliversFinished;
    }
}
