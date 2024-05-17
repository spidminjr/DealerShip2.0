package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalesContract extends Contract {
    private static final double SALES_TAX = 0.05;
    private static final double RECORDING_FEE = 100;
    private static final double PROCESSING_FEE_UNDER_10000 = 295;
    private static final double PROCESSING_FEE_OVER_10000 = 495;
    private boolean finance;

    public SalesContract(Date date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice() {
        double basePrice = getVehicle().getPrice();
        double processingFee = basePrice < 10000 ? PROCESSING_FEE_UNDER_10000 : PROCESSING_FEE_OVER_10000;
        double salesTax = basePrice * SALES_TAX;
        setTotalPrice(basePrice + processingFee + RECORDING_FEE + salesTax);
    }

    @Override
    public void calculateMonthlyPayment() {
        if (finance) {
            double basePrice = getVehicle().getPrice();
            double interestRate = basePrice >= 10000 ? 0.0425 : 0.0525;
            int loanTerm = basePrice >= 10000 ? 48 : 24;
            double monthlyInterestRate = interestRate / 12;
            double totalPrice = getTotalPrice();
            double monthlyPayment = (totalPrice * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -loanTerm));
            setMonthlyPayment(monthlyPayment);
        } else {
            setMonthlyPayment(0);
        }
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(getDate());
        Vehicle v = getVehicle();
        double salesTax = v.getPrice() * SALES_TAX;
        double processingFee = v.getPrice() < 10000 ? PROCESSING_FEE_UNDER_10000 : PROCESSING_FEE_OVER_10000;
        return String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f|%s|%.2f",
                dateStr, getCustomerName(), getCustomerEmail(), v.getId(), v.getYear(), v.getMake(), v.getModel(), v.getType(), v.getColor(), v.getMileage(), v.getPrice(),
                salesTax, RECORDING_FEE, processingFee, getTotalPrice(), finance ? "YES" : "NO", getMonthlyPayment());
    }
}
