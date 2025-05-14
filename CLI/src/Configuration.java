import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Configuration {
    private  int maxTicketCapacity;
    private int totalNumberOfTickets;
    private int ticReleaseRate;
    private int customerRetrievalRate;

private static final Logger logger = Logger.getLogger(Configuration.class.getName());
static {
    try {
        FileHandler fileHandler = new FileHandler("log.file",true);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

    // Constructor
    public Configuration( int maxTicketCapacity, int totalNumberOfTickets, int ticReleaseRate, int customerRetrievalRate  ) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.totalNumberOfTickets = totalNumberOfTickets;
        this.ticReleaseRate = ticReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;


    }

    // Default Constructor
    public Configuration() {}

    // Getters and Setters
    public int getTotalNumberOfTickets() {
        return totalNumberOfTickets;
    }

    public void setTotalNumberOfTickets(int totalNumberOfTickets) {
        this.totalNumberOfTickets = totalNumberOfTickets;
    }

    public int getTicReleaseRate() {
        return ticReleaseRate;
    }

    public void setTicReleaseRate(int ticReleaseRate) {
        this.ticReleaseRate = ticReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "totalNumberOfTickets=" + totalNumberOfTickets +
                ", ticReleaseRate=" + ticReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }

    // Save the configuration to a file
    public void saveToFile(Configuration config) {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("confi.json")) {
            gson.toJson(config, fileWriter);
            System.out.println("Configuration saved to confi.json");
        } catch (IOException e) {
            throw new RuntimeException("Error saving configuration: " + e.getMessage());
        }
        logger.log(Level.INFO, "Configuration saved to confi.json");
    }

    // Load configuration from a file
    public static Configuration loadFromFile() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("confi.json")) {
            return gson.fromJson(fileReader, Configuration.class);
        } catch (FileNotFoundException e) {
            System.out.println("Configuration file not found.");
        } catch (Exception e) {
            System.out.println("Error loading configuration: " + e.getMessage());
        }
        logger.log(Level.INFO, "Configuration loaded from confi.json");
        return null;
    }

}
