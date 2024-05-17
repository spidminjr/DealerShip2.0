package model;

public class SalesContract extends Contract
{
    private static final double sales_tax = 0.05;
    private static final double recordingFee = 100;
    private static final double processingFee_under_10000 = 295;
    private static final double processingFee_over_10000 = 495;
    private boolean finance;


    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
        super(date, customerName, customerEmail, vehicle);
        this.finance = finance;
        calculateTotalPrice();
        calculateMonthlyPayment();
    }



    @Override
    public void calculateTotalPrice()
    {
         double basePrice = getVehicle().getPrice();
         double processFee = basePrice < 10000 ? processingFee_under_10000 : processingFee_over_10000;
         double salesTax = basePrice * sales_tax;
         setTotalPrice(basePrice + processFee + recordingFee + salesTax);
    }


    @Override
    public void calculateMonthlyPayment()
    {
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
}



