package com.spms.parkingspaceservice.service;

import com.spms.parkingspaceservice.model.ParkingSpace;
import com.spms.parkingspaceservice.repository.ParkingSpaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpaceService {

    private final ParkingSpaceRepository parkingSpaceRepository;

    public ParkingSpaceService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    public ParkingSpace addParkingSpace(ParkingSpace space) {
        return parkingSpaceRepository.save(space);
    }

    public Optional<ParkingSpace> getSpaceById(Long id) {
        return parkingSpaceRepository.findById(id);
    }

    public List<ParkingSpace> getAllSpaces() {
        return parkingSpaceRepository.findAll();
    }

    public Optional<ParkingSpace> updateSpace(Long id, ParkingSpace details) {
        return parkingSpaceRepository.findById(id).map(space -> {
            if(details.getSpaceNumber() != null) space.setSpaceNumber(details.getSpaceNumber());
            if(details.getLocation() != null) space.setLocation(details.getLocation());
            if(details.getZone() != null) space.setZone(details.getZone());
            if(details.getCity() != null) space.setCity(details.getCity());
            if(details.getPricePerHour() > 0) space.setPricePerHour(details.getPricePerHour());
            if(details.getOwnerId() != null) space.setOwnerId(details.getOwnerId());
            return parkingSpaceRepository.save(space);
        });
    }

    public boolean deleteSpace(Long id) {
        if(parkingSpaceRepository.existsById(id)) {
            parkingSpaceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<ParkingSpace> reserveSpace(Long id) {
        return parkingSpaceRepository.findById(id).map(space -> {
            if("AVAILABLE".equals(space.getStatus())) {
                space.setStatus("RESERVED");
                return parkingSpaceRepository.save(space);
            }
            throw new IllegalStateException("Space is not available");
        });
    }

    public Optional<ParkingSpace> releaseSpace(Long id) {
        return parkingSpaceRepository.findById(id).map(space -> {
            space.setStatus("AVAILABLE");
            return parkingSpaceRepository.save(space);
        });
    }

    public List<ParkingSpace> getAvailableSpaces() {
        return parkingSpaceRepository.findByStatus("AVAILABLE");
    }

    public List<ParkingSpace> getSpacesByCity(String city) {
        return parkingSpaceRepository.findByCity(city);
    }

    public List<ParkingSpace> getSpacesByZone(String zone) {
        return parkingSpaceRepository.findByZone(zone);
    }

    public List<ParkingSpace> getSpacesByOwner(Long ownerId) {
        return parkingSpaceRepository.findByOwnerId(ownerId);
    }

    public Optional<ParkingSpace> updateStatus(Long id, String status) {
        if(!"AVAILABLE".equals(status) && !"OCCUPIED".equals(status) && !"RESERVED".equals(status) && !"MAINTENANCE".equals(status)) {
            throw new IllegalArgumentException("Invalid status");
        }
        return parkingSpaceRepository.findById(id).map(space -> {
            space.setStatus(status);
            return parkingSpaceRepository.save(space);
        });
    }
}
