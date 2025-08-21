package com.invoice.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class Dealer {

  @Id
  private String dealerId;
  private String name;
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;
  private String phone;
  private String email;
  private String gstNumber; 

    @PrePersist
    public void generateDealerId() {
        String prefix = "DLR";
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        String randomPart = String.format("%04d", new Random().nextInt(10000)); 
        this.dealerId = prefix + datePart + randomPart;
    }
}
