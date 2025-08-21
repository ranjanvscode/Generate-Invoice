package com.invoice.service.impl;

import org.springframework.stereotype.Service;

import com.invoice.entity.Vehicle;
import com.invoice.exception.ResourceNotFound;
import com.invoice.repository.VehicleRepository;
import com.invoice.service.VehicleService;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }
    
    @Override
    public Vehicle saveVehicle(Vehicle vehicle) {

        return vehicleRepository.save(vehicle); 
    }

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Vehicle not found with id: " + id));
    }

}
