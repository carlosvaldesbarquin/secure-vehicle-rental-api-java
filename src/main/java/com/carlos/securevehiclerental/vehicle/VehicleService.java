package com.carlos.securevehiclerental.vehicle;

import com.carlos.securevehiclerental.exception.DuplicateResourceException;
import com.carlos.securevehiclerental.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    public VehicleService(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Transactional(readOnly = true)
    public List<VehicleResponse> findAll() {
        return vehicleRepository.findAll()
                .stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VehicleResponse> findAvailable() {
        return vehicleRepository.findByStatus(VehicleStatus.AVAILABLE)
                .stream()
                .map(vehicleMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public VehicleResponse findById(Long id) {
        Vehicle vehicle = getVehicleByIdOrThrow(id);
        return vehicleMapper.toResponse(vehicle);
    }

    public VehicleResponse create(VehicleRequest request) {
        if (vehicleRepository.existsByLicensePlate(request.licensePlate())) {
            throw new DuplicateResourceException(
                    "Vehicle with license plate " + request.licensePlate() + " already exists"
            );
        }

        Vehicle vehicle = vehicleMapper.toEntity(request);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(savedVehicle);
    }

    public VehicleResponse update(Long id, VehicleRequest request) {
        Vehicle vehicle = getVehicleByIdOrThrow(id);

        boolean licensePlateChanged = !vehicle.getLicensePlate().equalsIgnoreCase(request.licensePlate());

        if (licensePlateChanged && vehicleRepository.existsByLicensePlate(request.licensePlate())) {
            throw new DuplicateResourceException(
                    "Vehicle with license plate " + request.licensePlate() + " already exists"
            );
        }

        vehicleMapper.updateEntity(vehicle, request);

        Vehicle updatedVehicle = vehicleRepository.save(vehicle);

        return vehicleMapper.toResponse(updatedVehicle);
    }

    public void delete(Long id) {
        Vehicle vehicle = getVehicleByIdOrThrow(id);
        vehicleRepository.delete(vehicle);
    }

    private Vehicle getVehicleByIdOrThrow(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Vehicle with id " + id + " not found"
                ));
    }
}