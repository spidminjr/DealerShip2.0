package services;

import model.Contract;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private static final String FILE_PATH = "C:/PluralSight/Workshop/DealershipApp/Files/contracts.csv";

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(contract.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
