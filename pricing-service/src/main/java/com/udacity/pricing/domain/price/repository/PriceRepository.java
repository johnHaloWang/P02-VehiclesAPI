package com.udacity.pricing.domain.price.repository;

import com.udacity.pricing.domain.price.entity.Price;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
//public class PriceRepository {
//
//}

public interface PriceRepository extends CrudRepository<Price, Long> {

    @Query("select p from Price p where p.vehicleId=:id")
    Optional<Price> findPriceByVehicleById(Long id);
}