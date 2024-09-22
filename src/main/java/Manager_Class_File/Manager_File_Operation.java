/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager_Class_File;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Manager_File_Operation {
    
    public List<Manager_BookingRecord> readBookingRecords() {
        List<Manager_BookingRecord> bookingRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Booking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(","); 
                if (data.length == 7) {
                    String bookingId = data[0].trim();
                    String customerId = data[1].trim();
                    String startTime = data[2].trim();
                    String endTime = data[3].trim();
                    String hallId = data[4].trim();
                    String reservationPeople = data[5].trim();
                    String remark = data[6].trim();
                    bookingRecords.add(new Manager_BookingRecord(bookingId, customerId, startTime, endTime, hallId, reservationPeople, remark, null));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookingRecords;
    }

    // Method to read payment records
    public List<Manager_PaymentRecord> readPaymentRecords() {
        List<Manager_PaymentRecord> paymentRecords = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Payment.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the line format is: PaymentID,CustID,BookingID,PaymentTime,TotalAmount
                String[] data = line.split(","); 
                if (data.length == 5) {
                    String paymentId = data[0].trim();
                    String custId = data[1].trim();
                    String bookingId = data[2].trim();
                    String paymentTime = data[3].trim();
                    String totalAmount = data[4].trim();
                    paymentRecords.add(new Manager_PaymentRecord(paymentId, custId, bookingId, paymentTime, totalAmount));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return paymentRecords;
    }
    
    // Method to fetch booking records based on selected year and hall type
    public List<Manager_BookingRecord> fetchBookingRecords(String selectedYear, String searchDate) {
        List<Manager_BookingRecord> bookingRecords = readBookingRecords();
        List<Manager_PaymentRecord> paymentRecords = readPaymentRecords();
        List<Manager_BookingRecord> filteredRecords = new ArrayList<>();

        for (Manager_BookingRecord record : bookingRecords) {
            String bookingDate = record.getStartTime().split(" ")[0]; // Extract booking date
            String bookingYear = bookingDate.split("-")[0];
            if (bookingYear.equals(selectedYear)) {
                // Optionally filter by search date if provided
                if (searchDate.isEmpty() || bookingDate.equals(searchDate)) {
                    // Find total amount from payment records
                    for (Manager_PaymentRecord payment : paymentRecords) {
                        if (payment.getBookingId().equals(record.getBookingId())) {
                            record = new Manager_BookingRecord(
                                record.getBookingId(),
                                record.getCustomerId(),
                                record.getStartTime(),
                                record.getEndTime(),
                                record.getHallId(),
                                record.getReservationPeople(),
                                record.getRemark(),
                                payment.getTotalAmount()
                            );
                            filteredRecords.add(record);
              
                        }
                    }
                }
            }
        }
        return filteredRecords;
    }
}
