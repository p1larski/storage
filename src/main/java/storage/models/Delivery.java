package storage.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfDelivery;
    private Long amountOfProductsInDelivery;
    @OneToMany(mappedBy = "delivery")
    @JsonIgnore
    private List<Product> deliveredProducts;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee responsibleEmployee;
}
