package model;

public class SalesContract extends Contract
{
    private static final double sales_tax = 0.05;
    private static final double recordingFee = 100;
    private static final double processingFee_under_10000 = 295;
    private static final double processingFee_over_10000 = 495;
    private boolean finance;
    private static final double financed_is_10000_orMore_for_48Months = 0.0425;
    private static final double financed_under_10000_for_24Months = 0.0525;

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


    @


}
