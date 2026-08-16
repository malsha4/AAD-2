package com.spms.vehicleservice.service;

import com.spms.vehicleservice.model.Vehicle;
import com.spms.vehicleservice.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle registerVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> getVehiclesByUser(Long userId) {
        return vehicleRepository.findByUserId(userId);
    }

    public Optional<Vehicle> updateVehicle(Long id, Vehicle details) {
        return vehicleRepository.findById(id).map(vehicle -> {
            if(details.getLicensePlate() != null) vehicle.setLicensePlate(details.getLicensePlate());
            if(details.getBrand() != null) vehicle.setBrand(details.getBrand());
            if(details.getModel() != null) vehicle.setModel(details.getModel());
            if(details.getColor() != null) vehicle.setColor(details.getColor());
            return vehicleRepository.save(vehicle);
        });
    }

    public Optional<Vehicle> simulateEntry(Long id) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setParked(true);
            vehicle.setEntryTime(LocalDateTime.now());
            vehicle.setExitTime(null);
            return vehicleRepository.save(vehicle);
        });
    }

    public Optional<Vehicle> simulateExit(Long id) {
        return vehicleRepository.findById(id).map(vehicle -> {
            vehicle.setParked(false);
            vehicle.setExitTime(LocalDateTime.now());
            return vehicleRepository.save(vehicle);
        });
    }

    public boolean deleteVehicle(Long id) {
        if(vehicleRepository.existsById(id)) {
            vehicleRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
