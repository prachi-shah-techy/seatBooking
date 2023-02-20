import seat.booking.pojo.BusinessOwner;
import seat.booking.pojo.Room;
import seat.booking.service.AdminService;
import seat.booking.service.BusinessOwnerService;
import seat.booking.service.UserService;
import seat.booking.service.impl.AdminServiceImpl;
import seat.booking.service.impl.BusinessOwnerServiceImpl;
import seat.booking.service.impl.UserServiceImpl;
import seat.booking.util.DataHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static seat.booking.pojo.BusinessOwner.BUSINESS_TYPE;


public class SeatBookingMain {

    private final static Scanner SCANNER = new Scanner(System.in);
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
    static BufferedReader INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {

        addSampleData();
        init();
        //new Server().start();
        mainProcess();
    }

    private static void mainProcess() {
        AdminService adminService = new AdminServiceImpl();
        BusinessOwnerService businessOwnerService = new BusinessOwnerServiceImpl();
        UserService userService = new UserServiceImpl();

        try {
            char exit = 'N';
            do {
                System.out.println("Please select your role");
                System.out.println("1. Admin");
                System.out.println("2. Business Owner");
                System.out.println("3. User");
                System.out.println("4. Exit");

                int selection = SCANNER.nextInt();
                System.out.println(selection);

                switch (selection) {
                    case 1:
                        adminService.adminProcess();
                        break;
                    case 2:
                        businessOwnerService.businessOwnerProcess();
                        break;
                    case 3:
                        userService.userProcess();
                        break;
                    default:
                        System.out.println("Wrong Selection !!!");
                        break;
                }
                System.out.println("Do you want to Quit?\n please press Y(YES) or else N(NO)");
                exit = INPUT.readLine().charAt(0);
            }
            while ((exit == 'Y' || exit == 'y') && (exit != 'N' || exit != 'n'));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void addSampleData() {
        try {
            BusinessOwner businessOwner = new BusinessOwner("Sample 1", DATE_FORMATTER.parse("10/10/2022"), DATE_FORMATTER.parse("11/10/2022"), BUSINESS_TYPE.RESTAURANT, new Room(5, 5), new BigDecimal(100));
            BusinessOwner businessOwner2 = new BusinessOwner("Sample 2", DATE_FORMATTER.parse("01/03/2023"), DATE_FORMATTER.parse("01/03/2023"), BUSINESS_TYPE.AIRPORT, new Room(5, 5), new BigDecimal(200));
            BusinessOwner businessOwner3 = new BusinessOwner("Sample 3", DATE_FORMATTER.parse("05/03/2023"), DATE_FORMATTER.parse("05/03/2023"), BUSINESS_TYPE.CINEMA, new Room(5, 5), new BigDecimal(300));

            DataHolder.addBusinessOwner(businessOwner);
            DataHolder.addBusinessOwner(businessOwner2);
            DataHolder.addBusinessOwner(businessOwner3);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {
        System.out.println("-----------------------------------------------");
        System.out.println("    WELCOME TO THE SEAT BOOKING APPLICATION ");
        System.out.println("-----------------------------------------------\n");
    }

}