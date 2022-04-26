package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Inconsistency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InconsistencyRepository extends JpaRepository<Inconsistency, Long> {
}
