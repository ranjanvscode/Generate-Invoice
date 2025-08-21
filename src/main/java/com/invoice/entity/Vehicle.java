package com.invoice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder
@Entity 
public class Vehicle { 
  
  @Id
  private String vehicleId ;
  private String make; // e.g., Toyota, Ford
  private String model;
  private String registrationNumber;
  private String vin;// 17 characters number
  private BigDecimal price;

    @PrePersist
    public void generateVehicleId() {
        String prefix = "VEH";
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String randomPart = String.format("%04d", new Random().nextInt(10000)); 
        this.vehicleId = prefix + datePart + randomPart;
    }
}
