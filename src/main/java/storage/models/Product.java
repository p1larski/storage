package storage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOfDelivery;
    private LocalDate dateOfRelease;
    @ManyToOne
    private Release release;
    @ManyToOne(fetch = FetchType.EAGER)
    private Delivery delivery;
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
    }
}
