package itson.distribuidos.profecoproject.profeco.repositories;

import itson.distribuidos.profecoproject.profeco.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
}
