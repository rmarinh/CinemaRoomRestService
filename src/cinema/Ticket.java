package cinema;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {
    private int row;
    private int column;
    private int price;

    public Ticket() {
        this.row = -1;
        this.column = -1;
        this.price = -1;
    }
    public Ticket(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
    }

    public Ticket(int row, int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ?10:8;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getPrice() {
        return price;
    }
//he ticket price is determined by a row number.
// If the row number is less or equal to 4, set the price at 10.
// All other rows cost 8 per seat.
    public void setPrice(int price) {
    this.price = price;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return row == ticket.row && column == ticket.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
