package com.azalea.cathayassignment.repositories;

import com.azalea.cathayassignment.domain.entities.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
}
