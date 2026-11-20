package ca.hccis.fortnite.repositories;

import ca.hccis.fortnite.jpa.entity.MatchEntry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchEntryRepository extends CrudRepository<MatchEntry, Integer> {
}