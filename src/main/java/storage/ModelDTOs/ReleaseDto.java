package storage.ModelDTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReleaseDto {

    private LocalDate dateOfRelease;
    private String responsibleEmployee;
    private List<ProductDto> productsReleased;
}
