package com.carlos.securevehiclerental.vehicle;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/api/vehicles")
    public List<VehicleResponse> findAll() {
        return vehicleService.findAll();
    }

    @GetMapping("/api/vehicles/available")
    public List<VehicleResponse> findAvailable() {
        return vehicleService.findAvailable();
    }

    @GetMapping("/api/vehicles/{id}")
    public VehicleResponse findById(@PathVariable Long id) {
        return vehicleService.findById(id);
    }

    @PostMapping("/api/admin/vehicles")
    @ResponseStatus(HttpStatus.CREATED)
    public VehicleResponse create(@Valid @RequestBody VehicleRequest request) {
        return vehicleService.create(request);
    }

    @PutMapping("/api/admin/vehicles/{id}")
    public VehicleResponse update(
            @PathVariable Long id,
            @Valid @RequestBody VehicleRequest request
    ) {
        return vehicleService.update(id, request);
    }

    @DeleteMapping("/api/admin/vehicles/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}