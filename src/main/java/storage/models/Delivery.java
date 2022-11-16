package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfDelivery;
    private Long amountOfProductsInDelivery;
    @OneToMany(mappedBy = "delivery")
    private List<Product> deliveredProducts;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee responsibleEmployee;
}
