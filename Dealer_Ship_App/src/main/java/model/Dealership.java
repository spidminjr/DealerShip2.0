package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public List<Vehicle> getInventory() {
        return new ArrayList<>(inventory);
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(inventory);
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getPrice() >= min && vehicle.getPrice() <= max)
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getMake().equals(make) && vehicle.getModel().equals(model))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByYearRange(int startYear, int endYear) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getYear() >= startYear && vehicle.getYear() <= endYear)
                .collect(Collectors.toList());
    }



    public List<Vehicle> getVehiclesByColor(String color) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getColor().equals(color))
                .collect(Collectors.toList());
    }

    public List<Vehicle> getVehiclesByMileageRange(int minMileage, int maxMileage) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getMileage() >= minMileage && vehicle.getMileage() <= maxMileage)
                .collect(Collectors.toList());
    }


    public List<Vehicle> getVehiclesByType(String type) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }


    public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
    }

    public Vehicle getVehicleById(int id) {
        return inventory.stream()
                .filter(vehicle -> vehicle.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
