import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static boolean StartThread = false; // Shared flag to control threads
    private static final List<Thread> vendorThreads = new ArrayList<>();
    private static final List<Thread> customerThreads = new ArrayList<>();

    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class.getName());
        try {
                FileHandler fileHandler = new FileHandler("log.file",true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);
                logger.setLevel(Level.INFO);
                logger.setUseParentHandlers(false);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        Scanner scanner = new Scanner(System.in);

        // Attempt to load the configuration from file
        Configuration loadedConfig = Configuration.loadFromFile();
        Configuration currentConfig = null;

        if (loadedConfig != null) {
            System.out.println("Loaded configuration:");
            System.out.println(loadedConfig);

            System.out.println("Do you want to use the loaded configuration? (yes/no)");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                currentConfig = loadedConfig;
            }
        }

        // If no configuration loaded, prompt user for input
           if (currentConfig == null) {
               int getMaxTicketCapacity ;

               System.out.println("Enter Max Ticket Capacity:");
               getMaxTicketCapacity = positive();


               int getTotalNumberOfTickets;
            while(true) {
                System.out.println("Enter Total number of tickets:");
                getTotalNumberOfTickets = positive();
                if (getMaxTicketCapacity < getTotalNumberOfTickets) {
                    System.out.println("Enter Total TicketCapacity less than maxTicket Capacity:");

                } else {
                    break;
                }
            }


               System.out.println("Enter Release rate of Ticket:");
            int getTicReleaseRate = positive();

            System.out.println("Enter Customer Retrieval Rate:");
            int getCustomerRetrievalRate = positive();

            currentConfig = new Configuration(getMaxTicketCapacity, getTotalNumberOfTickets, getTicReleaseRate, getCustomerRetrievalRate);
        }

        // Create TicketPool instance
        TicketPool ticketPool = new TicketPool(currentConfig);

        // Menu for interacting with the system
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1 = Save Configuration");
            System.out.println("2 = Add Vendor");
            System.out.println("3 = Add Customer");
            System.out.println("4 = Run System");
            System.out.println("5 = Stop System");
            System.out.println("6 = Exit");

            int choice = positive();

            switch (choice) {
                case 1:
                    // Save configuration
                    currentConfig.saveToFile(currentConfig);
                    System.out.println("New configuration saved.");
                    break;

                case 2:
                    System.out.println("How many vendors do you want to add?");
                    int numVendors = positive();
                    for (int i = 0; i < numVendors; i++) {
                        int vendorID = vendorThreads.size() + 1; // Auto-generate ID
                        Vendor vendor = new Vendor(vendorID, currentConfig.getTicReleaseRate(), currentConfig.getTotalNumberOfTickets(), ticketPool);
                        Thread vendorThread = new Thread(vendor, "Vendor-" + vendorID);
                        vendorThreads.add(vendorThread);
                        System.out.println("Vendor " + vendorID + " added.");
                    }
                    break;

                case 3:
                    System.out.println("How many customers do you want to add?");
                    int numCustomers = positive();
                      for (int i = 0; i < numCustomers; i++) {
                          System.out.println("How many tickets can this customer buy (quantity)?");
                          int quantity = positive();

                    int customerID = customerThreads.size() + 1; // Auto-generate ID
                    Customer customer = new Customer(customerID, currentConfig.getCustomerRetrievalRate(), quantity, ticketPool);
                    Thread customerThread = new Thread(customer, "Customer-" + customerID);
                    customerThreads.add(customerThread);
                    System.out.println("Customer " + customerID + " added.");
            }
                      logger.log(Level.INFO, "Customer " + currentConfig.getCustomerRetrievalRate() + " added.");
                    break;

                case 4:
                    if (vendorThreads.isEmpty()) { // Ensure at least one vendor exists
                        System.out.println("Please add at least one vendor before running the system.");
                    } else if (customerThreads.isEmpty()) { // Ensure at least one customer exists
                        System.out.println("Please add at least one customer before running the system.");
                    } else if (!StartThread) {
                        System.out.println("Starting all threads...");
                        StartThread = true; // Signal threads to start
                        // Start all vendor threads
                        for (Thread vendorThread : vendorThreads) {
                            if (!vendorThread.isAlive()) {
                                vendorThread.start();
                            }
                        }
                        // Start all customer threads
                        for (Thread customerThread : customerThreads) {
                            if (!customerThread.isAlive()) {
                                customerThread.start();
                            }
                        }
                    } else {
                        System.out.println("Threads are already running!");
                    }
                    logger.log(Level.INFO, "Threads are now running.");
                    break;

                case 5:
                    if (StartThread) {
                        System.out.println("Stopping all threads...");
                        StartThread = false; // Signal threads to stop
                        for (Thread vendorThread : vendorThreads) {
                            if (vendorThread.isAlive()) {
                                vendorThread.stop();
                            }
                        }
                        // Start all customer threads
                        for (Thread customerThread : customerThreads) {
                            if (customerThread.isAlive()) {
                                customerThread.stop();
                            }
                        }
                    } else {
                        System.out.println("Threads are already stopped!");
                    }
                    logger.log(Level.INFO, "Threads are now stopped.");
                    break;

                case 6:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option. Please select again.");

            }
        }
    }

    public static int positive() {
        Scanner scanner = new Scanner(System.in);
        int num;
        while (true) {
            try {
                num = scanner.nextInt();
                if (num < 0) {
                    System.out.println("Invalid input. Please enter a positive number:");
                } else {
                    return num;
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer:");
                scanner.next(); // Clear invalid input
            }
        }
    }
}

