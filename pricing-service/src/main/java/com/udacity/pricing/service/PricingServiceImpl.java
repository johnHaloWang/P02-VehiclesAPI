package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.entity.Price;
import com.udacity.pricing.domain.price.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingServiceImpl implements PricingService{

    @Autowired
    PriceRepository priceRepository;
    @Override
    public List<Price> retrievePrice() {
        return (List<Price>) this.priceRepository.findAll();
    }

    @Override
    public Price getPrice(Long vehicleId) throws PriceException {

        Optional<Price> find = priceRepository.findById(vehicleId);
        if(find.isEmpty()){
            throw new PriceException("Cannot find price for Vehicle " + vehicleId);
        }
        return find.get();

    }
}
