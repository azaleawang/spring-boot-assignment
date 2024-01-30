package com.azalea.cathayassignment.repositories;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findByCode(String code);

    boolean existsByCode(String code);
}
