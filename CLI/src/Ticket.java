import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Ticket {
 private int ticketID;
 private BigDecimal ticketPrice;
 private String EventName;

 public Ticket(int ticketID, BigDecimal ticketPrice, String EventName) {
     this.ticketID = ticketID;
     this.ticketPrice = ticketPrice;
     this.EventName = EventName;
 }

    private static final Logger logger = Logger.getLogger(Ticket.class.getName());
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


    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID=" + ticketID +
                ", ticketPrice=" + ticketPrice +
                ", EventName='" + EventName + '\'' +
                '}';
    }

}
