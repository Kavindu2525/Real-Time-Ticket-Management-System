import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TicketPool {
    private final Queue<Ticket> tickets = new LinkedList<Ticket>();
    private final int maxCapacity;
   /* private final int totalNumberOfTicket;*/
   /* private final int ticReleaseRate;*/

    public TicketPool(Configuration config) {
        this.maxCapacity = config.getMaxTicketCapacity();
       /* this.totalNumberOfTicket = config.getTotalNumberOfTickets();*/
       /* this.ticReleaseRate = config.getTicReleaseRate();*/
     /*   addInitialTickets(config.getTotalNumberOfTickets());*/
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


   /* // Add initial tickets based on the total number of tickets specified by the user
    private void addInitialTickets(int totalTickets) {
        for (int i = 0; i < totalTickets; i++) {
            tickets.add("Ticket" + (i + 1));
        }
        System.out.println("Initialized Ticket Pool with " + totalTickets + " tickets.");
    }*/

    // Add tickets based on the release rate
    public synchronized void addTicket(Ticket ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                wait();
                System.out.println("Tikect pool is full " + tickets.size());
            }catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        this.tickets.add(ticket);
        System.out.println(Thread.currentThread().getName() + ": Ticket added: " + ticket + "Current Size is " + tickets.size());
        notifyAll();
        logger.log(Level.INFO, "Ticket added: " + ticket + "Current Size is " + tickets.size());
    }



       /* if (tickets.size() < maxCapacity) {
            String newTicket = "Ticket" + (tickets.size() + 1);
            tickets.add(newTicket);
            notifyAll(); // Notify waiting customers
            System.out.println(Thread.currentThread().getName() + ": Ticket added: " + newTicket + "Current pool size: " + tickets.size());
        } else {
            System.out.println("Ticket pool is full." + tickets.size());

        }
    }*/


           /* int ticketsToRelease = Math.min(ticReleaseRate, maxCapacity - tickets.size());
            for (int i = 0; i < ticketsToRelease; i++) {
                this.tickets.add("Ticket" + (tickets.size() + 1));
            }
            System.out.println(Thread.currentThread().getName() + "Released " + ticketsToRelease + " ticket(s). Current pool size: " + tickets.size());

        }*/



    // Remove tickets based on the retrieval rate
    public synchronized Ticket removeTicket() {
        while (tickets.isEmpty()) {
            try {
                System.out.println("No tickets available. Customer is waiting...");
                wait();
            } catch (InterruptedException e) {
               throw new RuntimeException(e.getMessage());
            }
        }

        Ticket ticket = tickets.poll();
        notifyAll();
        System.out.println(Thread.currentThread().getName() + "Retrieved " + ticket +  "Current pool size: " + tickets.size() );
        logger.log(Level.INFO, "Ticket removed: " + ticket + "Current pool size: " + tickets.size());
        return ticket;

    }


   /* public synchronized void displayTickets() {
        System.out.println("Total tickets in the pool: " + tickets.size());
    }*/
}


