/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager_Class_File;

/**
 *
 * @author User
 */
public class Manager_SalesRecord {
    private String period; // Week, Month, or Year
    private double totalSales;
    private int totalBookings;

    public Manager_SalesRecord(String period) {
        this.period = period;
        this.totalSales = 0;
        this.totalBookings = 0;
    }

    // Method to add sale and increment bookings
    public void addSale(double amount) {
        this.totalSales += amount;
        this.totalBookings++;
    }

    // Method to return row data for the JTable
    public Object[] getRowData() {
        return new Object[] { period, totalSales, totalBookings };
    }
}
