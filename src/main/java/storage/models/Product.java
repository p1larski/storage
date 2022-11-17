package storage.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfDelivery;
    private LocalDate dateOfRelease;
    @ManyToOne
    private Storage storage;
    @ManyToOne(fetch = FetchType.EAGER)
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
    }
}
