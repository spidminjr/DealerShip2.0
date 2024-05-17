package services;
import model.Contract;
import java.io.IOException;
import java.io.FileWriter;
public class ContractFileManager {
    private static final String FilePath = "C:\\PluralSight\\DealerShip2.0\\adv-dealership-project\\files\\contracts.csv";
    public void saveContact(Contract contract) {
        try (FileWriter writer = new FileWriter(FilePath, true)) {
            writer.write(contract.toString() + System.lineSeparator());
        } catch (IOException e) { System.out.println ("Error saving the contract" + e.getMessage());

    }
    }
}
