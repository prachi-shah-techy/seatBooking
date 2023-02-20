package seat.booking.service.impl;

import seat.booking.pojo.BusinessOwner;
import seat.booking.pojo.GrandeFloorGrid;
import seat.booking.service.BusinessOwnerService;
import seat.booking.util.DataHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class BusinessOwnerServiceImpl implements BusinessOwnerService {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static BufferedReader INPUT = new BufferedReader(new InputStreamReader(System.in));
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    public void businessOwnerProcess() {
        char exit;
        try {
            do {
                System.out.println("Please enter business name");
                String name = INPUT.readLine();

                System.out.println("Welcome :" + name);

                System.out.println("Please provide basic details");
                BusinessOwner.BUSINESS_TYPE businessType = businessTypeSelection();
                System.out.println("2. Enter Start Time DD/MM/YYY Format");
                Date startTime = DATE_FORMATTER.parse(INPUT.readLine());
                System.out.println("3. Enter End Time DD/MM/YYY Format");
                Date endTime = DATE_FORMATTER.parse(INPUT.readLine());
                System.out.println("4. row");
                int row = SCANNER.nextInt();
                System.out.println("5. Column");
                int column = SCANNER.nextInt();
                System.out.println("6. Price");
                BigDecimal price = new BigDecimal(INPUT.readLine());

                BusinessOwner businessOwner = new BusinessOwner(name, startTime, endTime, businessType, new GrandeFloorGrid(row, column), price);
                System.out.println(businessOwner.getGrandeFloorGrid());

                DataHolder.addBusinessOwner(businessOwner);

                System.out.println("Do you want to add more room? \n to continue please press Y  or else N");
                exit = INPUT.readLine().charAt(0);

            } while (exit == 'Y' && exit != 'N');
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            System.out.println("Entered date format is not valid");
            throw new RuntimeException(e);
        }
    }

    private static BusinessOwner.BUSINESS_TYPE businessTypeSelection() {
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
}
