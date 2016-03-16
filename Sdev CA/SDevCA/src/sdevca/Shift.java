
package sdevca;

import javax.persistence.*;

/**
 *
 * @author Graham
 */
@Entity
public class Shift {
    @Id
    @Column(name="pasyslip_id")
    private int payslipID;
    @Column(name="staff_id")
    @ManyToOne
    private Staff staff;
    private double netpay;
    private double grosspay;
    private double tax;
    
    //overloaded constructor

    public Shift(int payslipID, double netpay, double grosspay, double tax) {
        this.payslipID = payslipID;
        this.netpay = netpay;
        this.grosspay = grosspay;
        this.tax = tax;
    }
    //Getters and Setters
    public int getPayslipID() {
        return payslipID;
    }

    public void setPayslipID(int payslipID) {
        this.payslipID = payslipID;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public double getNetpay() {
        return netpay;
    }

    public void setNetpay(double netpay) {
        this.netpay = netpay;
    }

    public double getGrosspay() {
        return grosspay;
    }

    public void setGrosspay(double grosspay) {
        this.grosspay = grosspay;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }
    
    
    //Calculate payslip method
    public void calculatePayslip(int weekNumber){
        
    }
    
}
