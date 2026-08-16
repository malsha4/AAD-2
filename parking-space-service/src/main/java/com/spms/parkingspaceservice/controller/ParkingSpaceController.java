package com.spms.parkingspaceservice.controller;

import com.spms.parkingspaceservice.model.ParkingSpace;
import com.spms.parkingspaceservice.service.ParkingSpaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin(origins = "*")
public class ParkingSpaceController {

    private final ParkingSpaceService parkingSpaceService;

    public ParkingSpaceController(ParkingSpaceService parkingSpaceService) {
        this.parkingSpaceService = parkingSpaceService;
    }

    @PostMapping
    public ResponseEntity<ParkingSpace> addParkingSpace(@RequestBody ParkingSpace space) {
        return new ResponseEntity<>(parkingSpaceService.addParkingSpace(space), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpace>> getAllSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAllSpaces());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpace> getSpaceById(@PathVariable Long id) {
        return parkingSpaceService.getSpaceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpace> updateSpace(@PathVariable Long id, @RequestBody ParkingSpace details) {
        return parkingSpaceService.updateSpace(id, details)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        if(parkingSpaceService.deleteSpace(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<?> reserveSpace(@PathVariable Long id) {
        try {
            return parkingSpaceService.reserveSpace(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/release")
    public ResponseEntity<ParkingSpace> releaseSpace(@PathVariable Long id) {
        return parkingSpaceService.releaseSpace(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpace>> getAvailableSpaces() {
        return ResponseEntity.ok(parkingSpaceService.getAvailableSpaces());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByCity(@PathVariable String city) {
        return ResponseEntity.ok(parkingSpaceService.getSpacesByCity(city));
    }

    @GetMapping("/zone/{zone}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByZone(@PathVariable String zone) {
        return ResponseEntity.ok(parkingSpaceService.getSpacesByZone(zone));
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity<List<ParkingSpace>> getSpacesByOwner(@PathVariable Long ownerId) {
        return ResponseEntity.ok(parkingSpaceService.getSpacesByOwner(ownerId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestParam String status) {
        try {
            return parkingSpaceService.updateStatus(id, status)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
