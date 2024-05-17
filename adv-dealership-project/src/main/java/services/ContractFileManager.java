package services;

import model.Contract;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
<<<<<<< HEAD
    private static final String FILE_PATH = "files/contracts.csv";
=======
    private static final String FILE_PATH = "Files/contracts.csv";
>>>>>>> 9f7a2501f40cde317954163cd11d9fb101210318

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(contract.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving contract: " + e.getMessage());
        }
    }
}
