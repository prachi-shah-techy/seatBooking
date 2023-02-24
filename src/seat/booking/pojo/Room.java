package seat.booking.pojo;


public class Room {

    protected int numRows;
    protected int numColumns;
    protected Seat[][] seats;

    public Room(int row, int column) {
        this.numRows = row;
        this.numColumns = column;
        this.seats = new Seat[numRows][numColumns];

        initialiseRoomGrid();
    }

    void initialiseRoomGrid() {

        for (int row = 0; row < numRows; row++) {
            for (int column = 0; column < numColumns; column++) {
                seats[row][column] = new Seat(new SeatPosition(row, (char) column));
            }
        }
    }


    public int getLastRow() {
        return numRows;
    }


    public char getLastColumn() {
        return numColumns > 0 && numColumns < 27 ? (char) (numColumns + 64) : null;
    }

    public int getLastColumnInt() {
        return numColumns;
    }


    public Seat getSeat(int row, char column) {
        return seats[row - 1][(column - 65)];
    }

    public Seat queryAvailableSeat(int userSelectedRow, int userSelectedColumn) {
        if (!seats[userSelectedRow - 1][userSelectedColumn - 1].isBooked() || seats[userSelectedRow - 1][userSelectedColumn - 1].isAvailableForResell()) {
            return seats[userSelectedRow - 1][userSelectedColumn - 1];
        }
        return null;
    }


    @Override
    public String toString() {

        String result = "";
        for (int column = 1; column <= numColumns; column++) {
            result += "     " + (char) (column + 64);
        }
        result += "\n";

        for (int row = 0; row < numRows; row++) {
            if (row < 9) {
                result += "  " + (row + 1) + " ";
            } else {
                result += " " + (row + 1) + " ";
            }
            for (int column = 0; column < numColumns; column++) {
                result += (seats[row][column]);
            }
            result += ("\n");
        }

        return result;
    }
}
