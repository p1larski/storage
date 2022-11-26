package storage.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.TreeSet;

@Getter
@Setter
@NoArgsConstructor
public class DeliveryWrapper {
    public TreeSet<Integer> treeSetOfNumbers = new TreeSet<>();
    public TreeSet<String> treeSetOfProducts = new TreeSet<>();

}
