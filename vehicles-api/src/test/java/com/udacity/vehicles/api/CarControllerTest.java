package com.udacity.vehicles.api;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.udacity.vehicles.client.maps.MapsClient;
import com.udacity.vehicles.client.prices.PriceClient;
import com.udacity.vehicles.domain.Condition;
import com.udacity.vehicles.domain.Location;
import com.udacity.vehicles.domain.car.Car;
import com.udacity.vehicles.domain.car.Details;
import com.udacity.vehicles.domain.manufacturer.Manufacturer;
import com.udacity.vehicles.service.CarService;
import java.net.URI;
import java.util.Collections;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;


/**
 * Implements testing of the CarController class.
 */

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Car> json;

    @MockBean
    private CarService carService;

    @MockBean
    private PriceClient priceClient;

    @MockBean
    private MapsClient mapsClient;

    private static final String _TAG = "CarControllerTest";
    /**
     * Creates pre-requisites for testing, such as an example car.
     */
    @Before
    public void setup() {
        Car car = getCar();
        car.setId(1L);
        given(carService.save(any())).willReturn(car);
        given(carService.findById(any())).willReturn(car);
        given(carService.list()).willReturn(Collections.singletonList(car));
    }

    /**
     * Tests for successful creation of new car in the system
     * @throws Exception when car creation fails in the system
     */
    @Test
    public void createCar() throws Exception {
        Car car = getCar();
        mvc.perform(
                post(new URI("/cars"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.details.body", is(car.getDetails().getBody())))
                .andExpect(jsonPath("$.details.model", is(car.getDetails().getModel())))
                .andExpect(jsonPath("$.details.manufacturer.code", is(car.getDetails().getManufacturer().getCode())))
                .andExpect(jsonPath("$.details.manufacturer.name", is(car.getDetails().getManufacturer().getName())))
                .andExpect(jsonPath("$.details.numberOfDoors", is(car.getDetails().getNumberOfDoors())))
                .andExpect(jsonPath("$.details.fuelType", is(car.getDetails().getFuelType())))
                .andExpect(jsonPath("$.details.mileage", is(car.getDetails().getMileage())))
                .andExpect(jsonPath("$.details.modelYear", is(car.getDetails().getModelYear())))
                .andExpect(jsonPath("$.details.productionYear", is(car.getDetails().getProductionYear())))
                .andExpect(jsonPath("$.details.externalColor", is(car.getDetails().getExternalColor())))
                .andExpect(jsonPath("$.details.engine", is(car.getDetails().getEngine())))
                .andExpect(jsonPath("$.id", is(1)));
        verify(carService, times(1)).save(any(Car.class));
    }

    /**
     * Tests if the read operation appropriately returns a list of vehicles.
     * @throws Exception if the read operation of the vehicle list fails
     */
    @Test
    public void listCars() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   the whole list of vehicles. This should utilize the car from `getCar()`
         *   below (the vehicle will be the first in the list).
         */
        Car car = getCar();
        String root = "$._embedded.carList[0]";
        mvc.perform(
                get(new URI("/cars"))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath(root + ".details.body", is(car.getDetails().getBody())))
                .andExpect(jsonPath(root + ".details.model", is(car.getDetails().getModel())))
                .andExpect(jsonPath(root + ".details.manufacturer.code", is(car.getDetails().getManufacturer().getCode())))
                .andExpect(jsonPath(root + ".details.manufacturer.name", is(car.getDetails().getManufacturer().getName())))
                .andExpect(jsonPath(root + ".details.numberOfDoors", is(car.getDetails().getNumberOfDoors())))
                .andExpect(jsonPath(root + ".details.fuelType", is(car.getDetails().getFuelType())))
                .andExpect(jsonPath(root + ".details.mileage", is(car.getDetails().getMileage())))
                .andExpect(jsonPath(root + ".details.modelYear", is(car.getDetails().getModelYear())))
                .andExpect(jsonPath(root + ".details.productionYear", is(car.getDetails().getProductionYear())))
                .andExpect(jsonPath(root + ".details.externalColor", is(car.getDetails().getExternalColor())))
                .andExpect(jsonPath(root + ".details.engine", is(car.getDetails().getEngine())))
                .andExpect(jsonPath(root + ".id", is(1)));
        verify(carService, times(1)).list();
    }

    /**
     * Tests the read operation for a single car by ID.
     * @throws Exception if the read operation for a single car fails
     */
    @Test
    public void findCar() throws Exception {
        /**
         * TODO: Add a test to check that the `get` method works by calling
         *   a vehicle by ID. This should utilize the car from `getCar()` below.
         */
        Car car = getCar();
        car.setId((long) 1);
        String root = "$";
        mvc.perform(
                get(new URI("/cars/1"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath(root + ".details.body", is(car.getDetails().getBody())))
                .andExpect(jsonPath(root + ".details.model", is(car.getDetails().getModel())))
                .andExpect(jsonPath(root + ".details.manufacturer.code", is(car.getDetails().getManufacturer().getCode())))
                .andExpect(jsonPath(root + ".details.manufacturer.name", is(car.getDetails().getManufacturer().getName())))
                .andExpect(jsonPath(root + ".details.numberOfDoors", is(car.getDetails().getNumberOfDoors())))
                .andExpect(jsonPath(root + ".details.fuelType", is(car.getDetails().getFuelType())))
                .andExpect(jsonPath(root + ".details.mileage", is(car.getDetails().getMileage())))
                .andExpect(jsonPath(root + ".details.modelYear", is(car.getDetails().getModelYear())))
                .andExpect(jsonPath(root + ".details.productionYear", is(car.getDetails().getProductionYear())))
                .andExpect(jsonPath(root + ".details.externalColor", is(car.getDetails().getExternalColor())))
                .andExpect(jsonPath(root + ".details.engine", is(car.getDetails().getEngine())))
                .andExpect(jsonPath(root + ".id", is(1)));

        verify(carService, times(1)).findById((long)1);
    }
    /**
     * Tests for successful update an existing car in the system
     * @throws Exception when car creation fails in the system
     */
    @Test
    public void updateCar() throws Exception {
        Car car = getCar();
        Details details = car.getDetails();

        //there is a error
        car.setDetails(details);
        List<Car> list = carService.list();
        car.setId((list.get(0).getId()));
        log.debug("id: " + car.getId());
        given(carService.save(any())).willReturn(car);
        details.setBody("new body");
        mvc.perform(
                put(new URI("/cars/1"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.details.body", is(car.getDetails().getBody())))
                .andExpect(jsonPath("$.details.model", is(car.getDetails().getModel())))
                .andExpect(jsonPath("$.details.manufacturer.code", is(car.getDetails().getManufacturer().getCode())))
                .andExpect(jsonPath("$.details.manufacturer.name", is(car.getDetails().getManufacturer().getName())))
                .andExpect(jsonPath("$.details.numberOfDoors", is(car.getDetails().getNumberOfDoors())))
                .andExpect(jsonPath("$.details.fuelType", is(car.getDetails().getFuelType())))
                .andExpect(jsonPath("$.details.mileage", is(car.getDetails().getMileage())))
                .andExpect(jsonPath("$.details.modelYear", is(car.getDetails().getModelYear())))
                .andExpect(jsonPath("$.details.productionYear", is(car.getDetails().getProductionYear())))
                .andExpect(jsonPath("$.details.externalColor", is(car.getDetails().getExternalColor())))
                .andExpect(jsonPath("$.details.engine", is(car.getDetails().getEngine())))
                .andExpect(jsonPath("$.id", is(1)));
        verify(carService, times(1)).save(any(Car.class));
    }

    /**
     * Tests the deletion of a single car by ID.
     * @throws Exception if the delete operation of a vehicle fails
     */
    @Test
    public void deleteCar() throws Exception {
        /**
         * TODO: Add a test to check whether a vehicle is appropriately deleted
         *   when the `delete` method is called from the Car Controller. This
         *   should utilize the car from `getCar()` below.
         */
        Car car = getCar();
        String root = "$";
        mvc.perform(
                delete(new URI("/cars/1"))
                        .content(json.write(car).getJson())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
        verify(carService, times(1)).delete((long)1);
    }

    /**
     * Creates an example Car object for use in testing.
     * @return an example Car object
     */
    private Car getCar() {
        Car car = new Car();
        car.setLocation(new Location(40.730610, -73.935242));
        Details details = new Details();
        Manufacturer manufacturer = new Manufacturer(101, "Chevrolet");
        details.setManufacturer(manufacturer);
        details.setModel("Impala");
        details.setMileage(32280);
        details.setExternalColor("white");
        details.setBody("sedan");
        details.setEngine("3.6L V6");
        details.setFuelType("Gasoline");
        details.setModelYear(2018);
        details.setProductionYear(2018);
        details.setNumberOfDoors(4);
        car.setDetails(details);
        car.setCondition(Condition.USED);
        return car;
    }
}