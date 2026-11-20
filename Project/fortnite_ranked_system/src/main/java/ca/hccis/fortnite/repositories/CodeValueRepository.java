package ca.hccis.fortnite.repositories;

import ca.hccis.fortnite.jpa.entity.CodeValue;
import ca.hccis.fortnite.jpa.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}