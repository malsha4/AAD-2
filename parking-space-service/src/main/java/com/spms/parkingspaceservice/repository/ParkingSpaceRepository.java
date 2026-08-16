package com.spms.parkingspaceservice.repository;

import com.spms.parkingspaceservice.model.ParkingSpace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {
    List<ParkingSpace> findByStatus(String status);
    List<ParkingSpace> findByCity(String city);
    List<ParkingSpace> findByZone(String zone);
    List<ParkingSpace> findByOwnerId(Long ownerId);
    List<ParkingSpace> findByCityAndStatus(String city, String status);
}
