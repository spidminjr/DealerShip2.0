package model;

public class leaseContract extends Contract {
    private static final double Expected_Ending_Value = 0.5;
    private static final double Lease_Fee = 0.07;
    private static final double InterestRate = 0.04;

    public leaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        calculateTotalPrice();
        calculateMonthlyPayment();
    }

    @Override
    public void calculateTotalPrice()
    {
        double basePrice = getVehicle().getPrice();
        double leaseFee = basePrice * Lease_Fee;
        setTotalPrice(basePrice + leaseFee);
    }

    @Override
    public void calculateMonthlyPayment()
    {
        double basePrice = getVehicle().getPrice();
        double EndValue = basePrice * Expected_Ending_Value;
        int LeaseTerm = 36;
        double monthlyInterestRate = InterestRate / 12;
        double monthlyPayment = (basePrice - EndValue) / LeaseTerm + (basePrice + EndValue) * monthlyInterestRate;

        setMonthlyPayment(monthlyPayment);
    }
}
