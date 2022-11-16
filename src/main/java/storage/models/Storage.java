package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "storage")
    private List<Product> productsInStorage;
    @OneToMany(mappedBy = "storage")
    private List<Product> productsReleased;
}
