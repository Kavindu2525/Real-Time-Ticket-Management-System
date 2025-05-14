import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Customer implements Runnable {
    private final int customerID;
    private final TicketPool ticketPool;
    private final int retrievalRate; // Maximum tickets retrieved in one cycle
    private final int quantity;
    private static final Random random = new Random();

    public Customer(int customerID, int retrievalRate, int quantity, TicketPool ticketPool) {
        this.customerID = customerID;
        this.retrievalRate = retrievalRate;
        this.quantity = quantity;
        this.ticketPool = ticketPool;

    }
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());
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

    @Override
    public void run() {

            if (Main.StartThread) {
                for (int i = 0; i < quantity; i++) {
                    Ticket ticket = ticketPool.removeTicket();
                    System.out.println("Ticket bought by " + Thread.currentThread().getName() + "   " +  "Ticket is" + ticket);
                    logger.log(Level.INFO, "Ticket bought by " + Thread.currentThread().getName() + "   " +  "Ticket is" + ticket);
                }
            } else {
                System.out.println("Waiting for " + Thread.currentThread().getName() + "...");
                /*break; // Stop if no tickets are available*/
            }
            logger.log(Level.INFO, "Customer " + customerID + " is waiting for " + Thread.currentThread().getName() + "...");

            /*  System.out.println("Customer " + customerID + " retrieved " + retrieved + " ticket.");*/

        try {
            Thread.sleep(retrievalRate * 1000);
            // Random delay between 500ms and 1500ms

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer " + customerID + " thread stopped.");
        }
        logger.log(Level.INFO, "Customer " + customerID + " thread finished.");
    }
}/*
int ticketsToRetrieve = random.nextInt(retrievalRate) + 1; // Random tickets between 1 and retrievalRate
int retrieved = 0; // Count of successfully retrieved tickets
                    for (int i = 0; i < ticketsToRetrieve; i++) {
String ticket = ticketPool.removeTicket();
                        if (ticket != null) {
retrieved++;*/










/*
 while (true) {
         if (Main.StartThread) {
        for (int i = 0; i < quantity; i++) {
String ticket = ticketPool.removeTicket();
                    System.out.println("Ticket bought by " + Thread.currentThread().getName() + "Ticket is" + ticket);
        }
        } else {
        break; // Stop if no tickets are available
        }

        */
/*  System.out.println("Customer " + customerID + " retrieved " + retrieved + " ticket.");*//*

        }
        try {
        Thread.sleep(retrievalRate * 1000);
// Random delay between 500ms and 1500ms

        } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
            System.out.println("Customer " + customerID + " thread stopped.");
        }*/
