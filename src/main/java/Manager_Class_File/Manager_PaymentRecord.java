/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Manager_Class_File;

/**
 *
 * @author User
 */
public class Manager_PaymentRecord {
    private String paymentId;
    private String custId;
    private String bookingId;
    private String paymentTime;
    private String totalAmount;

    public Manager_PaymentRecord(String paymentId, String custId, String bookingId, String paymentTime, String totalAmount) {
        this.paymentId = paymentId;
        this.custId = custId;
        this.bookingId = bookingId;
        this.paymentTime = paymentTime;
        this.totalAmount = totalAmount;
    }

    // Getters
    public String getPaymentId() {
        return paymentId;
    }

    public String getCustId() {
        return custId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public String getTotalAmount() {
        return totalAmount;
    }
}
