package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {
}
