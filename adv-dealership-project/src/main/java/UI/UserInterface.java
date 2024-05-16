package UI;

import model.Dealership;
import model.Vehicle;
import services.DealershipFileManager;

import java.util.List;
import java.util.Scanner;

    public class UserInterface {
        private Dealership dealership;
        private DealershipFileManager fileManager;
        private Scanner scanner = new Scanner(System.in);

        public UserInterface() {
            init();
        }

        private void init() {
            this.fileManager = new DealershipFileManager();
            this.dealership = fileManager.getDealerShip(); // Load the dealership information and inventory
        }

        public void startInterface() {
            display();
        }

        private void display() {
            while (true) {
                displayMenu();
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        processGetByPriceRequest();
                        break;
                    case "2":
                        processGetByMakeModelRequest();
                        break;
                    case "3":
                        processGetByYearRangeRequest();
                        break;
                    case "4":
                        processGetByColorRequest();
                        break;
                    case "5":
                        processGetByMileageRangeRequest();
                        break;
                    case "6":
                        processGetByVehicleTypeRequest();
                        break;
                    case "7":
                        processAllVehiclesRequest();
                        break;
                    case "8":
                        processAddVehicleRequest();
                        break;
                    case "9":
                        processRemoveVehicleRequest();
                        break;
                    case "99":
                        System.out.println("Exiting...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            }
        }

        private void displayMenu() {
            System.out.println();
            System.out.println("[1] - Find vehicles within a price range.");
            System.out.println("[2] - Find vehicles by make/model.");
            System.out.println("[3] - Find vehicles by year range.");
            System.out.println("[4] - Find vehicles by color.");
            System.out.println("[5] - Find vehicles by mileage range.");
            System.out.println("[6] - Find vehicles by type (sedan, truck, SUV, van).");
            System.out.println("[7] - List all vehicles.");
            System.out.println("[8] - Add a vehicle.");
            System.out.println("[9] - Remove a vehicle.");
            System.out.println("[99] - Quit.");
        }


        private void processGetByPriceRequest() {
            System.out.println("Enter minimum price:");
            double min = Double.parseDouble(scanner.nextLine());
            System.out.println("Enter maximum price:");
            double max = Double.parseDouble(scanner.nextLine());
            List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
            displayVehicles(vehicles);
        }

        private void processGetByMakeModelRequest() {
            System.out.println("Enter make:");
            String make = scanner.nextLine();
            System.out.println("Enter model:");
            String model = scanner.nextLine();
            List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
            displayVehicles(vehicles);
        }

        private void processGetByYearRangeRequest() {
            System.out.println("Enter the start year:");
            int startYear = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter the end year:");
            int endYear = Integer.parseInt(scanner.nextLine());

            List<Vehicle> vehicles = dealership.getVehiclesByYearRange(startYear, endYear);
            displayVehicles(vehicles);
        }


        private void processGetByColorRequest() {
            System.out.println("Enter color:");
            String color = scanner.nextLine();
            List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
            displayVehicles(vehicles);
        }

        private void processGetByMileageRangeRequest() {
            System.out.println("Enter minimum mileage:");
            int minMileage = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter maximum mileage:");
            int maxMileage = Integer.parseInt(scanner.nextLine());

            List<Vehicle> vehicles = dealership.getVehiclesByMileageRange(minMileage, maxMileage);
            displayVehicles(vehicles);
        }


        private void processGetByVehicleTypeRequest() {
            System.out.println("Enter vehicle type (sedan, truck, SUV, van):");
            String type = scanner.nextLine().trim();
            List<Vehicle> vehicles = dealership.getVehiclesByType(type);
            displayVehicles(vehicles);
        }


        private void processAllVehiclesRequest() {
            List<Vehicle> vehicles = dealership.getAllVehicles();
            displayVehicles(vehicles);
        }

        private void processAddVehicleRequest() {
            System.out.println("Enter vehicle id:");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter vehicle year:");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter vehicle make:");
            String make = scanner.nextLine();
            System.out.println("Enter vehicle model:");
            String model = scanner.nextLine();
            System.out.println("Enter vehicle type:");
            String type = scanner.nextLine();
            System.out.println("Enter vehicle color:");
            String color = scanner.nextLine();
            System.out.println("Enter vehicle mileage:");
            int mileage = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter vehicle price:");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle vehicle = new Vehicle(id, year, make, model, type, color, mileage, price);
            dealership.addVehicle(vehicle);
            fileManager.saveDealership(dealership);  // Save changes to file
            System.out.println("Vehicle added.");
        }

        private void processRemoveVehicleRequest() {
            System.out.println("Enter vehicle id to remove:");
            int id = Integer.parseInt(scanner.nextLine());
            Vehicle vehicle = dealership.getVehicleById(id);
            if (vehicle != null) {
                dealership.removeVehicle(vehicle);
                fileManager.saveDealership(dealership);  // Save changes to file
                System.out.println("Vehicle removed.");
            } else {
                System.out.println("Vehicle not found.");
            }
        }

        private void displayVehicles(List<Vehicle> vehicles) {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles found.");
                return;
            }

            // Display header
            System.out.printf("%-5s | %-6s | %-10s | %-10s | %-10s | %-10s | %-8s | %-10s%n",
                    "ID", "Year", "Make", "Model", "Type", "Color", "Mileage", "Price");
            System.out.println("--------------------------------------------------------------------------------------");

            // Display each vehicle in a formatted manner
            for (Vehicle vehicle : vehicles) {
                System.out.printf("%-5d | %-6d | %-10s | %-10s | %-10s | %-10s | %-8d | $%-9.2f%n",
                        vehicle.getId(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getType(),
                        vehicle.getColor(),
                        vehicle.getMileage(),
                        vehicle.getPrice());
            }
        }

    }
