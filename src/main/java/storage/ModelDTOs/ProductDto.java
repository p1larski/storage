package storage.ModelDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private String articleName;
    private Long numberOfProductInStorage;
    private Long deliveryIdentificationNumber;
    private LocalDate dateOfDelivery;
    private String usernameWhoReceived;
    private LocalDate dateOfRelease;
    private String usernameWhoReleased;

    public ProductDto(Long id, String articleName) {
        this.id = id;
        this.articleName = articleName;
    }

    public ProductDto(String articleName) {
        this.articleName=articleName;
    }
}
