package com.carlos.securevehiclerental.vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record VehicleResponse(
        Long id,
        String brand,
        String model,
        String licensePlate,
        VehicleType vehicleType,
        BigDecimal pricePerDay,
        VehicleStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}