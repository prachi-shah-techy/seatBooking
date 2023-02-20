package seat.booking.pojo;

public class Seat {

    private boolean isBooked;
    private boolean isAvailableForResell;
    private SeatPosition seatPosition;

    public Seat(SeatPosition seatPosition) {
        this.seatPosition = seatPosition;
    }

    @Override
    public String toString() {
        return ("[" + (isBooked ? isAvailableForResell ? " R " : " X " : " _ ") + "]");
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public SeatPosition getSeatPosition() {
        return seatPosition;
    }

    public void setSeatPosition(SeatPosition seatPosition) {
        this.seatPosition = seatPosition;
    }

    public boolean isAvailableForResell() {
        return isAvailableForResell;
    }

    public void setAvailableForResell(boolean availableForResell) {
        isAvailableForResell = availableForResell;
    }
}
