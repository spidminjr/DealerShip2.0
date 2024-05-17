package services;

import model.Dealership;
import model.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.IOException;

public class DealershipFileManager {
    private static final String FILE_PATH = "C:/PluralSight/Workshop/DealershipApp/Files/inventory.csv";

    public Dealership getDealerShip() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            System.out.println("No inventory file found or file is empty.");
            return null; // Optionally, return an empty dealership or prompt for setup.
        }

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextLine()) {
                System.out.println("The inventory file is empty.");
                return null;
            }
            String[] dealershipDetails = scanner.nextLine().split("\\|");
            Dealership dealership = new Dealership(dealershipDetails[0], dealershipDetails[1], dealershipDetails[2]);

            while (scanner.hasNextLine()) {
                String[] vehicleDetails = scanner.nextLine().split("\\|");
                Vehicle vehicle = new Vehicle(
                        Integer.parseInt(vehicleDetails[0]),
                        Integer.parseInt(vehicleDetails[1]),
                        vehicleDetails[2],
                        vehicleDetails[3],
                        vehicleDetails[4],
                        vehicleDetails[5],
                        Integer.parseInt(vehicleDetails[6]),
                        Double.parseDouble(vehicleDetails[7])
                );
                dealership.addVehicle(vehicle);
            }
            return dealership;
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file was not found. " + e.getMessage());
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(FILE_PATH)) {
            if (dealership == null || dealership.getInventory().isEmpty()) {
                System.out.println("No dealership data to save.");
                return;
            }
            writer.println(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhoneNumber());
            for (Vehicle vehicle : dealership.getInventory()) {
                writer.println(vehicle.getId() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|" + vehicle.getType() + "|" + vehicle.getColor() + "|" + vehicle.getMileage() + "|" + vehicle.getPrice());
            }
        } catch (IOException e) {
            System.out.println("Error saving the dealership data: " + e.getMessage());
        }
    }
}

