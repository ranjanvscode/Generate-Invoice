package com.invoice.helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class UtilityMethods {

        private UtilityMethods() {
            // Prevent instantiation
        }

        static BufferedImage generateQRCodeImage(String text, int width, int height) throws WriterException {
            
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            return MatrixToImageWriter.toBufferedImage(bitMatrix);
        }

        public static String generateTransactionId() {
        // Current date & time
        LocalDateTime now = LocalDateTime.now();
        
        // Format: yyyyMMdd-HHmmss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = now.format(formatter);
        
        // 4-digit random number
        String randomPart = String.format("%04d", new Random().nextInt(10000));
        
        // Final transaction ID
        return "TXN-" + timestamp + "-" + randomPart;
    }
}