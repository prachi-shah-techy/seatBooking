package seat.booking.pojo;

public class GrandeFloorGrid extends FloorGrid {


    public GrandeFloorGrid(int row, int column) {
        this.numRows = row;
        this.numColumns = column;
        this.seats = new Seat[numRows][numColumns];

        initialiseFloorGrid();
    }


    @Override
    void initialiseFloorGrid() {

        for (int row = 0; row < numRows; row++) {
            // Arrange Seats for Grande Floor Grid
            for (int column = 0; column < numColumns; column++) {
                seats[row][column] = new Seat(new SeatPosition(row, (char) column));
            }
        }
    }
}
