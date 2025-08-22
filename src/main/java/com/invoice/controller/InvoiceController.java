package com.invoice.controller;

import com.google.zxing.WriterException;
import com.invoice.entity.Dealer;
import com.invoice.entity.Vehicle;
import com.invoice.helper.PdfGeneratorService;
import com.invoice.helper.UtilityMethods;
import com.invoice.service.impl.DealerServiceImpl;
import com.invoice.service.impl.VehicleServiceImpl;
import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InvoiceController {

    private final DealerServiceImpl dealerServiceImpl;
    private final VehicleServiceImpl vehicleServiceImpl;

    public InvoiceController(DealerServiceImpl dealerServiceImpl, VehicleServiceImpl vehicleServiceImpl) {
        this.dealerServiceImpl = dealerServiceImpl;
        this.vehicleServiceImpl = vehicleServiceImpl;
    }

    @PostMapping("/save/dealer")
    public ResponseEntity<Dealer> saveDealer(@RequestBody Dealer dealer) {
       
        Dealer savedDealer = dealerServiceImpl.saveDealer(dealer);
        return ResponseEntity.ok(savedDealer);
    }

    @PostMapping("/save/vehicle")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle) {
       
        Vehicle savedVehicle = vehicleServiceImpl.saveVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }
  
    @GetMapping("/generate/invoice")
    public ResponseEntity<Map<String, Object>> generateInvoicePage(
    @RequestParam String dealerId,
    @RequestParam String vehicleId,
    @RequestParam String customerName) {

        Dealer dealer = dealerServiceImpl.getDealerById(dealerId);
        Vehicle vehicle = vehicleServiceImpl.getVehicleById(vehicleId);
        String name = customerName;

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dealerName", dealer);
        parameters.put("vehicleMake", vehicle);
        parameters.put("customerName", name);

        return ResponseEntity.ok(parameters);
    }

    @GetMapping("/download/invoice")
    public ResponseEntity<byte[]> downloadInvoice(
    @RequestParam String dealerId,
    @RequestParam String vehicleId,
    @RequestParam String customerName) throws DocumentException, IOException, WriterException {

        Dealer dealer = dealerServiceImpl.getDealerById(dealerId);
        Vehicle vehicle = vehicleServiceImpl.getVehicleById(vehicleId);
        String name = customerName;

        // Generate a unique transaction ID
        String transactionId = UtilityMethods.generateTransactionId();
        String logoPath = "src/main/resources/static/images/logo.png";

        byte[] pdfBytes = PdfGeneratorService.generateInvoice(
                                dealer.getName(),
                                dealer.getAddress().getStreet(),
                                dealer.getAddress().getCity(),
                                dealer.getAddress().getState(),
                                dealer.getAddress().getZipCode(),
                                dealer.getPhone(),
                                dealer.getEmail(),
                                dealer.getGstNumber(),
                                vehicle.getMake(),
                                vehicle.getModel(),
                                vehicle.getRegistrationNumber(),
                                vehicle.getVin(),
                                vehicle.getPrice().doubleValue(),    
                                name,
                                transactionId,
                                logoPath
                );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=invoice.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
