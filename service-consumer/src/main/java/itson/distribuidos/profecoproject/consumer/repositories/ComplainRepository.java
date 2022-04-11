package itson.distribuidos.profecoproject.consumer.repositories;

import itson.distribuidos.profecoproject.consumer.entities.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}
