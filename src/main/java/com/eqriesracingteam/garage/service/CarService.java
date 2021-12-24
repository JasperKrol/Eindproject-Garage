package com.eqriesracingteam.garage.service;

import com.eqriesracingteam.garage.exceptions.BadRequestException;
import com.eqriesracingteam.garage.exceptions.RecordNotFoundException;
import com.eqriesracingteam.garage.model.Car;
import com.eqriesracingteam.garage.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarService {

    //Attributes
    private CarRepository carRepository;

    //Constructor
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    //Methods

    public Car addCar(Car car) {
        String licensePlate = car.getLicensePlate();
        List<Car> cars = (List<Car>) carRepository.findCarByLicensePlateIsContainingIgnoreCase(licensePlate);

        if (cars.size() > 0) {
            throw new BadRequestException("Car with identical license plate already exists");
        }
        Car newCar = carRepository.save(car);
        return newCar;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> getAllCarsByLicensePlate(String licensePlate) {
        return carRepository.findAllByLicensePlateContainingIgnoreCase(licensePlate);
    }

    public Car getOneCar(long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return optionalCar.get();
        } else {
            throw new BadRequestException("Car not found");
        }

    }

    public void deleteCar(long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Car id not found");
        }
    }

    public void updateCar(long id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car existingCar = optionalCar.get();

            car.setId(existingCar.getId());
            car.setOwner(existingCar.getOwner());
            carRepository.save(car);
        } else {
            throw new RecordNotFoundException("Car ID not found");
        }
    }

    public void partialUpdateCar(long id, Car car) {
        Optional<Car> optionalCar = carRepository.findById(id);

        //Add conditions
        if (optionalCar.isPresent()) {
            Car existingCar = carRepository.findById(id).orElse(null);

            if (car.getLicensePlate() != null && !car.getLicensePlate().isEmpty()) {
                existingCar.setLicensePlate(car.getLicensePlate());
            }
            if (car.getRegistrationPapers() != null && !car.getRegistrationPapers().isEmpty()) {
                existingCar.setRegistrationPapers(car.getRegistrationPapers());
            }
            carRepository.save(existingCar);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

}
