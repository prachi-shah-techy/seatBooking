package seat.booking.service.impl;

import seat.booking.pojo.BusinessOwner;
import seat.booking.pojo.Seat;
import seat.booking.pojo.User;
import seat.booking.service.UserService;
import seat.booking.util.DataHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserServiceImpl implements UserService {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static BufferedReader INPUT = new BufferedReader(new InputStreamReader(System.in));

    public void userProcess() {
        String name = "User";
        try {
            System.out.println("Please enter your name");
            name = INPUT.readLine();
            System.out.println("Welcome :" + name);
            char exit = 'N';
            do {
                System.out.println("Please provide basic details");
                BusinessOwner.BUSINESS_TYPE businessType = businessTypeSelection();

                System.out.println("============Available " + businessType + " in the System============");
                List<BusinessOwner> businessOwners = DataHolder.findByBusinessType(businessType);
                for (int i = 0; i < businessOwners.size(); i++) {
                    System.out.println((i + 1) + ": " + businessOwners.get(i).getName());
                    System.out.println(businessOwners.get(i).getStartTime() + " = TO =" + businessOwners.get(i).getEndTime());
                    System.out.println(businessOwners.get(i).getGrandeFloorGrid());
                }
                System.out.println("Please enter your preferred choice");
                int userSelection = SCANNER.nextInt();

                if (userSelection > businessOwners.size() && userSelection < 0) {
                    System.out.println("Sorry entered number is not valid");
                    continue;
                }
                System.out.println("Please select your action");
                System.out.println("1. Reserve seat");
                System.out.println("2. Resell Seat");
                System.out.println("3. Cancel Seat");
                System.out.println("4. Communicate with other user's");
                System.out.println("5. Register complaint");

                switch (SCANNER.nextInt()) {
                    case 1:
                        printSeatMatrix(businessOwners.get(userSelection - 1), name);
                        seatReservation(businessOwners.get(userSelection - 1), name);
                        break;
                    case 2:
                        resellSeat(businessOwners.get(userSelection - 1), name);
                        break;
                    case 3:
                        cancelSeat(businessOwners.get(userSelection - 1), name);
                        break;
                    case 4:
                        System.out.println("Feature will be available soon");
                        break;
                    case 5:
                        registerComplaint(businessOwners.get(userSelection - 1), name);
                        break;
                    default:
                        break;
                }
                System.out.println("Do you want to book more seats? \n to continue please press Y  or else N");
                exit = INPUT.readLine().charAt(0);
            } while (exit == 'Y' && exit != 'N');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nIt was pleasure to connect with you " + name + ".\n Have a nice day.");
    }

    private void registerComplaint(BusinessOwner businessOwner, String name) {
        try {
            System.out.println("Please enter your message to admin");
            String complaint = INPUT.readLine();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void cancelSeat(BusinessOwner businessOwner, String name) {
        try {
            System.out.println("Please enter your booking code: ");
            String bookingCode = INPUT.readLine();

            User reservedSeatUser = DataHolder.getUserByBookingCode(bookingCode);
            if (reservedSeatUser == null) {
                System.out.println("Sorry No Entry found with the entered booking code");
            } else {
                System.out.println("Are you sure you want to cancel this seat? \n If seat is cancelled you will lost your paid amount.");
                char confirm = INPUT.readLine().charAt(0);
                if (confirm == 'Y' || confirm == 'y') {
                    reservedSeatUser.getSeat().setBooked(false);
                    System.out.println("Seat has been cancelled !!");
                    DataHolder.removeUser(reservedSeatUser.getBookingCode());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void resellSeat(BusinessOwner businessOwner, String name) {
        try {
            System.out.println("Please enter your booking code: ");
            String bookingCode = INPUT.readLine();

            User reservedSeatUser = DataHolder.getUserByBookingCode(bookingCode);
            if (reservedSeatUser == null) {
                System.out.println("Sorry No Entry found with the entered booking code");
            } else if (reservedSeatUser.getSeat().isAvailableForResell()) {
                System.out.println("Seat is already available for resell !!");
            } else {
                System.out.println("Enter discounted price");
                BigDecimal discountedPrice = new BigDecimal(INPUT.readLine());
                reservedSeatUser.setDiscountedPrice(discountedPrice);
                reservedSeatUser.getSeat().setAvailableForResell(true);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BusinessOwner.BUSINESS_TYPE businessTypeSelection() {
        int userSeatTypeSelection = -1;

        do {
            System.out.println("Which Business type?");
            for (int i = 0; i < BusinessOwner.BUSINESS_TYPE.values().length; i++) {
                System.out.println((i + 1) + ": " + BusinessOwner.BUSINESS_TYPE.values()[i]);
            }
            userSeatTypeSelection = SCANNER.nextInt();
        } while (userSeatTypeSelection <= 0 || userSeatTypeSelection > BusinessOwner.BUSINESS_TYPE.values().length);

        return BusinessOwner.BUSINESS_TYPE.values()[userSeatTypeSelection - 1];
    }

    private void printSeatMatrix(BusinessOwner businessOwner, String name) {
        System.out.println("Seat availability");
        System.out.println("X -> for Availability || R -> available for Resell");
        System.out.println(businessOwner.getGrandeFloorGrid());
    }

    private void seatReservation(BusinessOwner businessOwner, String name) {
        try {
            System.out.println("Enter row");
            int selectedRow = SCANNER.nextInt();
            System.out.println("Enter column");
            int selectedColumn = SCANNER.nextInt();

            Seat availableSeat = businessOwner.getGrandeFloorGrid().queryAvailableSeat(selectedRow, selectedColumn);
            if (availableSeat == null) {
                System.out.println("Sorry, we could not book the requested seat.");
            } else {
                BigDecimal price = availableSeat.isAvailableForResell() ? getResellPrice(businessOwner, availableSeat) : calculatePrice(businessOwner, availableSeat);
                System.out.println("Price for this seat will be : " + price);
                System.out.println("Please confirm you have paid the amount. Y/N");
                char confirm = INPUT.readLine().charAt(0);
                if (confirm == 'Y' || confirm == 'y') {
                    availableSeat.setBooked(true);
                    availableSeat.setAvailableForResell(false);
                    User user = new User(name, price, availableSeat, businessOwner);
                    DataHolder.addUser(user);
                    System.out.println("=======================================");
                    System.out.println("  Congratulations !! Seat is booked.");
                    System.out.println("  Your booking code is : " + user.getBookingCode());
                    System.out.println("=======================================");
                    System.out.println(businessOwner.getGrandeFloorGrid());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BigDecimal getResellPrice(BusinessOwner businessOwner, Seat availableSeat) {

        Optional<User> user = DataHolder.getUsers().stream().filter(e -> e.getBusinessOwner().getName().equalsIgnoreCase(businessOwner.getName()))
                .filter(e -> e.getSeat().getSeatPosition().getColumnInt() == availableSeat.getSeatPosition().getColumnInt())
                .filter(e -> e.getSeat().getSeatPosition().getRow() == availableSeat.getSeatPosition().getRow())
                .findFirst();
        if (user.isPresent()) {
            return user.get().getDiscountedPrice();
        }
        return businessOwner.getPrice();

    }

    private BigDecimal calculatePrice(BusinessOwner businessOwner, Seat availableSeat) {

        BigDecimal regularPrice = businessOwner.getPrice();
        int lastRow = businessOwner.getGrandeFloorGrid().getLastRow();
        int lastColumn = businessOwner.getGrandeFloorGrid().getLastColumnInt();
        BigDecimal twentyFivePercentValue = regularPrice.multiply(new BigDecimal(25)).divide(new BigDecimal(100));

        if (availableSeat.getSeatPosition().getRow() == 0) {
            regularPrice = regularPrice.multiply(new BigDecimal(2));
        } else if (availableSeat.getSeatPosition().getRow() == lastRow) {
            regularPrice = regularPrice.subtract(twentyFivePercentValue);
        } else if (isMiddleColumn(availableSeat, lastColumn)) {
            regularPrice = regularPrice.add(twentyFivePercentValue);
        }
        return regularPrice;
    }

    private boolean isMiddleColumn(Seat availableSeat, int lastColumn) {

        if (availableSeat.getSeatPosition().getColumn() == lastColumn / 2) {
            return true;
        } else if (availableSeat.getSeatPosition().getColumn() == ((lastColumn / 2) + 1)) {
            return true;
        } else if (availableSeat.getSeatPosition().getColumn() == ((lastColumn / 2) - 1)) {
            return true;
        } else {
            return false;
        }
    }
}
