package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long amountOfArticlesInDelivery;
    private Long amountOfArticlesInStorage;
    private LocalDate dateOfRelease;
    @ManyToOne
    private Storage storage;
    @ManyToOne
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;
}
