package storage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storage.ModelDTOs.ProductDto;
import storage.ModelDTOs.ReleaseDto;
import storage.models.Product;
import storage.models.Release;
import storage.repositories.EmployeeRepository;
import storage.repositories.ProductRepository;
import storage.repositories.ReleaseRepository;
import storage.security.SecurityUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReleaseService {

    private ReleaseRepository releaseRepository;
    private ProductRepository productRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public ReleaseService(ReleaseRepository releaseRepository, ProductRepository productRepository, EmployeeRepository employeeRepository) {
        this.releaseRepository = releaseRepository;
        this.productRepository = productRepository;
        this.employeeRepository = employeeRepository;
    }

    public Release newRelease(ReleaseDto releaseDto){
        List<Product> listOfProductsInRelease = new ArrayList<>();
        Release newRelease = new Release();
        newRelease.setDateOfRelease(LocalDate.now());
        newRelease.setResponsibleEmployee(employeeRepository.findEmployeeByUsername(SecurityUtils.getUserName()));
        newRelease.setProductsReleased(listOfProductsInRelease);
        releaseRepository.save(newRelease);
        for (ProductDto productDto : releaseDto.getProductsReleased()) {
            Product productById =  productRepository.findByIdAndDateOfDelivery(productDto.getId(),productDto.getDateOfDelivery());
            productById.setRelease(newRelease);
            listOfProductsInRelease.add(productRepository.findByIdAndDateOfDelivery(productDto.getId(),productDto.getDateOfDelivery()));
            productById.getArticle().setAmountOfArticlesInStorage(productById.getArticle().getAmountOfArticlesInStorage()-1);
            productRepository.save(productById);
        }

        return newRelease;
    }
}
