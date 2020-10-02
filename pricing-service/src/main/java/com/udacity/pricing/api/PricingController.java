package com.udacity.pricing.api;

import com.udacity.pricing.domain.price.entity.Price;
import com.udacity.pricing.service.PriceException;
import com.udacity.pricing.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Implements a REST-based controller for the pricing service.
 */
@RestController
@RequestMapping("/services/price")
public class PricingController {

    private PricingService pricingService;

    @Autowired
    public void setPricingService(PricingService pricingService){
        this.pricingService = pricingService;
    }

    /**
     * Gets the price for a requested vehicle.
     * @param vehicleId ID number of the vehicle for which the price is requested
     * @return price of the vehicle, or error that it was not found.
     */
    @GetMapping
    public ResponseEntity<Price> get(@RequestParam Long vehicleId) {
        try {
            //return PricingService.getPrice(vehicleId);
            return new ResponseEntity<Price>(this.pricingService.getPrice(vehicleId), HttpStatus.OK);
        } catch (PriceException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Price Not Found", ex);
        }

    }
    @GetMapping("/all")
    public ResponseEntity<List<Price>> getAllPrices() {
        List<Price> list = pricingService.retrievePrice();
        return new ResponseEntity<List<Price>>(list, HttpStatus.OK);

    }
}
