package cinema;

import java.util.Objects;
import java.util.UUID;

public class TicketPurchase {
    private UUID token;
    private Ticket ticket;

    public TicketPurchase() {
        this.token = UUID.randomUUID();

    }
    public TicketPurchase( Ticket ticket) {
        this.token = UUID.randomUUID();
        this.ticket = ticket;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketPurchase that = (TicketPurchase) o;
        return Objects.equals(token, that.token) && Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, ticket);
    }
}
