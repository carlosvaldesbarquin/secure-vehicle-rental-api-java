package com.carlos.securevehiclerental.vehicle;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record VehicleRequest(

        @NotBlank(message = "Brand is required")
        @Size(max = 50, message = "Brand must have at most 50 characters")
        String brand,

        @NotBlank(message = "Model is required")
        @Size(max = 50, message = "Model must have at most 50 characters")
        String model,

        @NotBlank(message = "License plate is required")
        @Size(max = 20, message = "License plate must have at most 20 characters")
        String licensePlate,

        @NotNull(message = "Vehicle type is required")
        VehicleType vehicleType,

        @NotNull(message = "Price per day is required")
        @DecimalMin(value = "0.01", message = "Price per day must be greater than 0")
        BigDecimal pricePerDay,

        VehicleStatus status
) {
}