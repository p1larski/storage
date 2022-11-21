package storage.ModelDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

    private Long id;
    private LocalDate dateOfDelivery;
    private Long amountOfProductsInDelivery;
    private String usernameWhoReclaimed;
    private List<ProductDto> productsInDelivery;
}
