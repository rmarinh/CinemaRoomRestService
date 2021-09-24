package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Theater implements Serializable {
       private int total_rows;
       private int total_columns;
       private List<Ticket> availabe_tickets;
       private List<TicketPurchase> purchased_tickets;

    public Theater(int total_rows, int total_columns) {
        this.total_rows = total_rows;
        this.total_columns = total_columns;
        availabe_tickets = new ArrayList<>();
        purchased_tickets = new LinkedList<>();

        fillSeats();
    }

    private void fillSeats(){
        for (int i = 0; i < total_rows; i++) {
            for (int j = 0; j < total_columns; j++) {
                if (i+1<=4){
                    availabe_tickets.add(new Ticket(i+1,j+1,10));
                }else
                    availabe_tickets.add(new Ticket(i+1,j+1,8));
            }

        }
    }
    public int getTotal_rows() {
        return total_rows;
    }
    private int calculateIncome(){
        int totalIncome = 0;
        for (TicketPurchase purchase : purchased_tickets
             ) {
            totalIncome += purchase.getTicket().getPrice();
        }
        return totalIncome;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }
    @JsonProperty("available_seats")
    public List<Ticket> getAvailableSeats() {
        return this.availabe_tickets;
    }

    public void setAvailableSeats(List<Ticket> tickets) {
        this.availabe_tickets = tickets;
    }
/*
    public boolean purchaseSeat(int row, int column){
        if (row>0 && row <=total_rows && column >0 && column <= total_columns){
            for (Ticket s: availabe_tickets) {
                if(s.getRow() == row && s.getColumn() == column){
                    availabe_tickets.remove(s);
                    purchased_tickets.add(s);
                    return true;
                }
            }
        }
        return false;
    }*/

    public TicketPurchase purchaseSeat(Ticket ticketToBeBuyed) throws Exception {
        if (!isSeatValid(ticketToBeBuyed)){
            throw new SeatOutOfBoundsException("seat out of bounds");
        }
        if (isSeatBought(ticketToBeBuyed)){
            throw new SeatAlreadyPurchasedException("Seat already Bought");
        }
        int index = availabe_tickets.indexOf(ticketToBeBuyed);
        if (index >-1){
            Ticket temp = availabe_tickets.get(index);
            availabe_tickets.remove(temp);
            TicketPurchase ticketPurchase = new TicketPurchase(temp);
            purchased_tickets.add(ticketPurchase);
            return ticketPurchase;
        }
            throw new Exception("seatNotFound");
    }


    public boolean isSeatBought(Ticket ticket){
        for (TicketPurchase purchase : purchased_tickets)
              {
            if (purchase.getTicket().equals(ticket)){
                return true;
            }
        }
        return false;
    }
    public boolean isSeatValid(Ticket ticket) {
        if (ticket != null) {
            if (ticket.getColumn() < 0 ||
                    ticket.getColumn() > this.getTotal_columns() ||
                    ticket.getRow() < 0 ||
                    ticket.getRow() > this.getTotal_rows()) {
                return false;
            }else
                return true;
        }
        return  false;
    }



    public boolean isTicketPurchaseTokenValid(UUID token){
        for (TicketPurchase purchase
                : purchased_tickets) {
            if(purchase.getToken().equals(token))
                return true;
        }
        return false;
    }
    private Ticket getPurchasedTicket (UUID token){
        if (isTicketPurchaseTokenValid(token)){
            for (TicketPurchase purchase
                    : purchased_tickets) {
                if(purchase.getToken().equals(token))
                    return purchase.getTicket();
            }
        }
        return null;
    }
    private Ticket removePurchasedTicket (UUID token){
        if (isTicketPurchaseTokenValid(token)) {
            for (TicketPurchase purchase
                    : purchased_tickets) {
                if (purchase.getToken().equals(token)) {
                    Ticket ticket = new Ticket(purchase.getTicket().getRow(), purchase.getTicket().getColumn(), purchase.getTicket().getPrice());
                    availabe_tickets.add(ticket);
                    purchased_tickets.remove(purchase);
                    return purchase.getTicket();
                }
            }
        }
        return null;
    }
    public Ticket returnTicket(UUID token) throws WrongTokenException{
       if(!isTicketPurchaseTokenValid(token))
           throw new WrongTokenException("Wrong Token!");
        else{

           return removePurchasedTicket(token);

       }
    }
@JsonIgnore
    public Statistics getTheaterStatistics(){
        Statistics statistics = new Statistics( calculateIncome(),
        getAvailableSeats().size()
        , purchased_tickets.size());

        return statistics;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return total_rows == theater.total_rows && total_columns == theater.total_columns && Objects.equals(availabe_tickets, theater.availabe_tickets) && Objects.equals(purchased_tickets, theater.purchased_tickets);
    }


    @Override
    public int hashCode() {
        return Objects.hash(total_rows, total_columns, availabe_tickets, purchased_tickets);
    }
}
