package seat.booking.service.impl;

import seat.booking.service.AdminService;
import seat.booking.service.BusinessOwnerService;
import seat.booking.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {

    private static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


    public void adminProcess() {
        BusinessOwnerService businessOwnerService = new BusinessOwnerServiceImpl();
        UserService userService = new UserServiceImpl();
        try {
            char exit = 'N';
            do {
                System.out.println("Please select your action");
                System.out.println("1. Perform Business Owner's Actions");
                System.out.println("2. Perform User's Actions");
                System.out.println("3. Check Complaints");

                switch (Integer.valueOf(input.readLine())) {
                    case 1:
                        businessOwnerService.businessOwnerProcess();
                        break;
                    case 2:
                        userService.userProcess();
                        break;
                    case 3:
                        System.out.println("Feature will be available soon.");
                        break;
                    default:
                        break;
                }
                System.out.println("Do you want to continue as Admin?\nPlease press Y  or else N");
                exit = input.readLine().charAt(0);
            } while (exit == 'Y' && exit != 'N');
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
