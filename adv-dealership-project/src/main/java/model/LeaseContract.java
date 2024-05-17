package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaseContract extends Contract {
    private static final double LEASE_FEE = 0.07;
    private static final double EXPECTED_ENDING_VALUE_PERCENT = 0.5;
    private static final double INTEREST_RATE = 0.04;

    public LeaseContract(Date date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice() {
        double basePrice = getVehicle().getPrice();
        double leaseFee = basePrice * LEASE_FEE;
        setTotalPrice(basePrice + leaseFee);
    }

    @Override
    public void calculateMonthlyPayment() {
        double basePrice = getVehicle().getPrice();
        double endingValue = basePrice * EXPECTED_ENDING_VALUE_PERCENT;
        int leaseTerm = 36; // Assuming a 36-month lease term
        double monthlyInterestRate = INTEREST_RATE / 12;

        double monthlyPayment = (basePrice - endingValue) / leaseTerm + (basePrice + endingValue) * monthlyInterestRate;
        setMonthlyPayment(monthlyPayment);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(getDate());
        Vehicle v = getVehicle();
        double endingValue = v.getPrice() * EXPECTED_ENDING_VALUE_PERCENT;
        double leaseFee = v.getPrice() * LEASE_FEE;
        return String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f",
                dateStr, getCustomerName(), getCustomerEmail(), v.getId(), v.getYear(), v.getMake(), v.getModel(), v.getType(), v.getColor(), v.getMileage(), v.getPrice(),
                endingValue, leaseFee, getTotalPrice(), getMonthlyPayment());
    }
}
