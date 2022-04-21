package com.profeco.consumer.repositories;

import com.profeco.consumer.entities.Complain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainRepository extends JpaRepository<Complain, Long> {
}
