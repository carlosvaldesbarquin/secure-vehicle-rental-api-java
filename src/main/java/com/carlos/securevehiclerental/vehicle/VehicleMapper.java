package com.carlos.securevehiclerental.vehicle;

import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public Vehicle toEntity(VehicleRequest request) {
        return Vehicle.builder()
                .brand(request.brand())
                .model(request.model())
                .licensePlate(request.licensePlate())
                .vehicleType(request.vehicleType())
                .pricePerDay(request.pricePerDay())
                .status(request.status() != null ? request.status() : VehicleStatus.AVAILABLE)
                .build();
    }

    public VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getBrand(),
                vehicle.getModel(),
                vehicle.getLicensePlate(),
                vehicle.getVehicleType(),
                vehicle.getPricePerDay(),
                vehicle.getStatus(),
                vehicle.getCreatedAt(),
                vehicle.getUpdatedAt()
        );
    }

    public void updateEntity(Vehicle vehicle, VehicleRequest request) {
        vehicle.setBrand(request.brand());
        vehicle.setModel(request.model());
        vehicle.setLicensePlate(request.licensePlate());
        vehicle.setVehicleType(request.vehicleType());
        vehicle.setPricePerDay(request.pricePerDay());

        if (request.status() != null) {
            vehicle.setStatus(request.status());
        }
    }
}