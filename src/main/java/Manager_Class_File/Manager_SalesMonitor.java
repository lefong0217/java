/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager_Class_File;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class Manager_SalesMonitor {
    // Method to retrieve sales records based on selected year and sales type
    public List<Manager_SalesRecord> getSalesRecords(String selectedYear, String selectedSalesType) {
        Manager_File_Operation fileOps = new Manager_File_Operation();
        List<Manager_PaymentRecord> paymentRecords = fileOps.readPaymentRecords(); // Read payment records

        // Filter records by the selected year
        List<Manager_PaymentRecord> filteredRecords = new ArrayList<>();
        for (Manager_PaymentRecord record : paymentRecords) {
            String paymentYear = record.getPaymentTime().split("-")[0]; // Extract year from PaymentTime
            if (paymentYear.equals(selectedYear)) {
                filteredRecords.add(record);
            }
        }

        // Now filter the sales records based on the selected sales type
        switch (salesType) {
            case "Weekly":
                return aggregateWeeklySales(filteredRecords);
            case "Monthly":
                return aggregateMonthlySales(filteredRecords);
            case "Yearly":
                return aggregateYearlySales(filteredRecords);
            default:
                throw new IllegalArgumentException("Invalid sales type selected.");
        }
    }

    // Method to aggregate weekly sales data
    private List<SalesRecord> aggregateWeeklySales(List<Manager_PaymentRecord> records) {
        // Logic to aggregate weekly sales data
        Map<Integer, Manager_SalesRecord> weeklySales = new HashMap<>();
        Calendar calendar = Calendar.getInstance();

        for (Manager_PaymentRecord record : records) {
            String[] dateTime = record.getPaymentTime().split(" ");
            try {
                Date paymentDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateTime[0]);
                calendar.setTime(paymentDate);
                int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR); // Get week number

                // Aggregate sales
                weeklySales.computeIfAbsent(weekOfYear, k -> new Manager_SalesRecord("Week " + weekOfYear))
                           .addSale(Double.parseDouble(record.getTotalAmount()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>(weeklySales.values());
    }

    // Method to aggregate monthly sales data
    private List<SalesRecord> aggregateMonthlySales(List<Manager_PaymentRecord> records) {
        Map<Integer, Manager_SalesRecord> monthlySales = new HashMap<>();

        for (Manager_PaymentRecord record : records) {
            String[] dateTime = record.getPaymentTime().split(" ");
            String[] date = dateTime[0].split("-"); // Assuming yyyy-MM-dd
            int month = Integer.parseInt(date[1]); // Extract month

            // Aggregate sales
            monthlySales.computeIfAbsent(month, k -> new Manager_SalesRecord("Month " + month))
                        .addSale(Double.parseDouble(record.getTotalAmount()));
        }

        return new ArrayList<>(monthlySales.values());
    }

    // Method to aggregate yearly sales data
    private List<SalesRecord> aggregateYearlySales(List<Manager_PaymentRecord> records) {
        double totalSales = 0;

        for (Manager_PaymentRecord record : records) {
            totalSales += Double.parseDouble(record.getTotalAmount());
        }

        Manager_SalesRecord yearlyRecord = new Manager_SalesRecord("Year " + records.get(0).getPaymentTime().split("-")[0]);
        yearlyRecord.addSale(totalSales);
        return Arrays.asList(yearlyRecord);
    }
}
