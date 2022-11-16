package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private LocalDate dateOfEmployment;
    @OneToMany
    @JoinColumn(name = "delivery_id")
    private List<Delivery> finishedDelivers;
}
