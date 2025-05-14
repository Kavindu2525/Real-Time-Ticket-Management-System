import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Vendor implements Runnable {
    private final int vendorID;
    private final TicketPool ticketPool;
    private final int totalNumberOfTickets;
    private final int releaseRate; // Maximum tickets released in one cycle
   /* private static final Random random = new Random();*/

    public Vendor(int vendorID, int releaseRate, int totalNumberOfTickets, TicketPool ticketPool) {
        this.vendorID = vendorID;
        this.releaseRate = releaseRate;
        this.ticketPool = ticketPool;
        this.totalNumberOfTickets = totalNumberOfTickets;

    }

    private static final Logger logger = Logger.getLogger(Vendor.class.getName());
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
            for (int t = 1; t < totalNumberOfTickets; t++) {
                Ticket ticket = new Ticket(t,new BigDecimal(1000), "pandama");
                ticketPool.addTicket(ticket);
               /* System.out.println("Vendor " + vendorID + " has been added to the pool" + t + " tickets");*/
                try {
                    Thread.sleep(releaseRate * 1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                   /* throw new RuntimeException(e);*/
                }

            }
        }else {
            System.out.println("Ticket pool is full" + totalNumberOfTickets);
        }
        logger.log(Level.INFO, "Ticket pool is full" + totalNumberOfTickets);
    }
       /* try {
            while (true) {
                if (Main.StartThread) {
                    int ticketsToRelease = random.nextInt(releaseRate) + 1; // Random tickets between 1 and releaseRate
                    for (int i = 0; i < ticketsToRelease; i++) {
                        ticketPool.addTicket();
                    }
                    System.out.println("Vendor " + vendorID + " released " + ticketsToRelease + " ticket.");
                }
                Thread.sleep(random.nextInt(10000) ); // Random delay between 500ms and 1500ms
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor " + vendorID + " thread stopped.");
        }
    }*/
}
