package com.udacity.pricing.service;

import com.udacity.pricing.domain.price.entity.Price;

import java.util.List;

/**
 * Implements the pricing service to get prices for each vehicle.
 */
//public class PricingService {

public interface PricingService {

    List<Price> retrievePrice();

    Price getPrice(Long vehicleId) throws PriceException;


//    /**
//     * Holds {ID: Price} pairings (current implementation allows for 20 vehicles)
//     */
//    private static final Map<Long, Price> PRICES = LongStream
//            .range(1, 20)
//            .mapToObj(i -> new Price("USD", randomPrice(), i))
//            .collect(Collectors.toMap(Price::getVehicleId, p -> p));
//
//    /**
//     * If a valid vehicle ID, gets the price of the vehicle from the stored array.
//     * @param vehicleId ID number of the vehicle the price is requested for.
//     * @return price of the requested vehicle
//     * @throws PriceException vehicleID was not found
//     */
//    public static Price getPrice(Long vehicleId) throws PriceException {
//
//        if (!PRICES.containsKey(vehicleId)) {
//            throw new PriceException("Cannot find price for Vehicle " + vehicleId);
//        }
//
//        return PRICES.get(vehicleId);
//    }
//
//    /**
//     * Gets a random price to fill in for a given vehicle ID.
//     * @return random price for a vehicle
//     */
//    private static BigDecimal randomPrice() {
//        return new BigDecimal(ThreadLocalRandom.current().nextDouble(1, 5))
//                .multiply(new BigDecimal(5000d)).setScale(2, RoundingMode.HALF_UP);
//    }

}
