package com.invoice.helper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.google.zxing.WriterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfGeneratorService {

    public static byte[] generateInvoice(
            String dealerName,
            String dealerStreet,
            String dealerCity,
            String dealerState,
            String dealerZip,
            String dealerPhone,
            String dealerEmail,
            String dealerGst,
            String vehicleMake,
            String vehicleModel,
            String registrationNumber,
            String vin,
            double vehiclePrice,
            String customerName,
            String transactionId,
            String logoPath
    ) throws DocumentException, IOException, WriterException {

        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();

        // ==== Company Logo ====
        if (logoPath != null && !logoPath.isEmpty()) {
            try {
                Image logo = Image.getInstance(logoPath);
                logo.scaleToFit(100, 100);
                logo.setAlignment(Element.ALIGN_LEFT);
                document.add(logo);
            } catch (Exception e) {
                document.add(new Paragraph("Logo not found"));
            }
        }

        // ==== Invoice Header ====
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
        Paragraph title = new Paragraph("VEHICLE INVOICE", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        document.add(new Paragraph(" "));
        document.add(new LineSeparator());

        // ==== Invoice Meta ====
        String invoiceNumber = "INV-" + System.currentTimeMillis();
        String invoiceDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

        PdfPTable metaTable = new PdfPTable(2);
        metaTable.setWidthPercentage(100);
        metaTable.setSpacingBefore(10);
        metaTable.addCell(getCell("Invoice Number: " + invoiceNumber, PdfPCell.ALIGN_LEFT));
        metaTable.addCell(getCell("Invoice Date: " + invoiceDate, PdfPCell.ALIGN_RIGHT));
        metaTable.addCell(getCell("Transaction ID: " + transactionId, PdfPCell.ALIGN_LEFT));
        metaTable.addCell(getCell(" ", PdfPCell.ALIGN_RIGHT));
        document.add(metaTable);

        document.add(new Paragraph(" "));

        // ==== Dealer Info ====
        PdfPTable dealerTable = new PdfPTable(1);
        dealerTable.setWidthPercentage(100);
        dealerTable.setSpacingBefore(10);

        dealerTable.addCell(getHeaderCell("Dealer Information"));
        dealerTable.addCell("Name: " + dealerName);
        dealerTable.addCell("Address: " + dealerStreet + ", " + dealerCity + ", " + dealerState + " - " + dealerZip);
        dealerTable.addCell("Phone: " + dealerPhone);
        dealerTable.addCell("Email: " + dealerEmail);
        dealerTable.addCell("GST No: " + dealerGst);

        document.add(dealerTable);

        document.add(new Paragraph(" "));

        // ==== Customer Info ====
        PdfPTable customerTable = new PdfPTable(1);
        customerTable.setWidthPercentage(100);
        customerTable.setSpacingBefore(10);

        customerTable.addCell(getHeaderCell("Customer Information"));
        customerTable.addCell("Name: " + customerName);

        document.add(customerTable);

        document.add(new Paragraph(" "));

        // ==== Vehicle Info ====
        PdfPTable vehicleTable = new PdfPTable(2);
        vehicleTable.setWidthPercentage(100);
        vehicleTable.setSpacingBefore(10);

        vehicleTable.addCell(getHeaderCell("Make"));
        vehicleTable.addCell(getHeaderCell("Model"));
        vehicleTable.addCell(vehicleMake);
        vehicleTable.addCell(vehicleModel);

        vehicleTable.addCell(getHeaderCell("Registration No"));
        vehicleTable.addCell(getHeaderCell("VIN"));
        vehicleTable.addCell(registrationNumber);
        vehicleTable.addCell(vin);

        document.add(vehicleTable);

        document.add(new Paragraph(" "));

        // ==== Pricing Table ====
        PdfPTable priceTable = new PdfPTable(2);
        priceTable.setWidthPercentage(100);
        priceTable.setSpacingBefore(10);

        priceTable.addCell(getHeaderCell("Description"));
        priceTable.addCell(getHeaderCell("Amount (₹)"));

        priceTable.addCell("Base Price");
        priceTable.addCell(String.format("%.2f", vehiclePrice));

        double tax = vehiclePrice * 0.10;
        priceTable.addCell("Tax (10%)");
        priceTable.addCell(String.format("%.2f", tax));

        double total = vehiclePrice + tax;
        priceTable.addCell(getBoldCell("Total"));
        priceTable.addCell(getBoldCell("₹" + String.format("%.2f", total)));

        document.add(priceTable);

        document.add(new Paragraph(" "));

        // ==== QR Code ====
        BufferedImage qrImage = UtilityMethods.generateQRCodeImage(transactionId, 100, 100);
        ByteArrayOutputStream pngOut = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", pngOut);
        Image qrCode = Image.getInstance(pngOut.toByteArray());
        qrCode.setAlignment(Element.ALIGN_CENTER);
        document.add(qrCode);

        document.add(new Paragraph(" "));
        Paragraph note = new Paragraph("Scan QR Code to verify transaction.", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10));
        note.setAlignment(Element.ALIGN_CENTER);
        document.add(note);

        document.close();
        return out.toByteArray();
    }

    // Utility methods
    private static PdfPCell getHeaderCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPadding(5);
        return cell;
    }

    private static PdfPCell getBoldCell(String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12)));
        cell.setPadding(5);
        return cell;
    }

    private static PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setPadding(5);
        cell.setHorizontalAlignment(alignment);
        return cell;
    }
}
