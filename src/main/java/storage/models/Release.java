package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Release {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfRelease;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee responsibleEmployee;
    @OneToMany(mappedBy = "release")
    private List<Product> productsReleased;
}
