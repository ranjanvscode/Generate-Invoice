package com.invoice.service;

import com.invoice.entity.Vehicle;

public interface VehicleService {

    Vehicle saveVehicle(Vehicle vehicle);

    Vehicle getVehicleById(String id);

}
