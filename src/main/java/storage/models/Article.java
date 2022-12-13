package storage.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long amountOfArticlesInStorage;

    public Article() {
    }

    public Article(String name) {
        this.name = name;
    }
}
