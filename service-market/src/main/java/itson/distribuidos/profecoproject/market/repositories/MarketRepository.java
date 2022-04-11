package itson.distribuidos.profecoproject.market.repositories;

import itson.distribuidos.profecoproject.market.entities.Market;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketRepository extends JpaRepository<Market, Long> {
}
