/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager_Class_File;

/**
 *
 * @author User
 */
public class Manager_BookingRecord {
    private String bookingId;
    private String customerId;
    private String startTime; // yyyy-MM-dd HH:mm:ss
    private String endTime; // yyyy-MM-dd HH:mm:ss
    private String hallId;
    private String reservationPeople;
    private String remark;
    private String totalAmount;

    public Manager_BookingRecord(String bookingId, String customerId, String startTime, String endTime, String hallId, String reservationPeople, String remark, String totalAmount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.hallId = hallId;
        this.reservationPeople = reservationPeople;
        this.remark = remark; 
        this.totalAmount = totalAmount;
    }
    

    // Getters
    public String getBookingId() { return bookingId; }
    public String getCustomerId() { return customerId; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getHallId() { return hallId; }
    public String getReservationPeople() { return reservationPeople; }
    public String getRemark() { return remark; }
    public String getTotalAmount() { return totalAmount; }
}


