package storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import storage.models.Release;

@Repository
public interface ReleaseRepository  extends CrudRepository<Release, Long> {

}
