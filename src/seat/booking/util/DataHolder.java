package seat.booking.util;

import seat.booking.pojo.BusinessOwner;
import seat.booking.pojo.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataHolder {

    private static Map<String, BusinessOwner> BUSINESS_OWNER_DETAILS = new HashMap<>();
    private static Map<String, User> USER_DETAILS = new HashMap<>();

    public static List<BusinessOwner> addBusinessOwner(BusinessOwner businessOwner) {
        BUSINESS_OWNER_DETAILS.put(businessOwner.getName(), businessOwner);
        System.out.println("Successfully added business owner");
        return BUSINESS_OWNER_DETAILS.values().stream().collect(Collectors.toList());
    }

    public static List<BusinessOwner> getBusinessOwnerList() {
        return BUSINESS_OWNER_DETAILS.values().stream().collect(Collectors.toList());
    }

    public static BusinessOwner getBusinessOwnerByName(String name) {
        return BUSINESS_OWNER_DETAILS.get(name);
    }

    public static List<BusinessOwner> findByBusinessType(BusinessOwner.BUSINESS_TYPE businessType) {
        return BUSINESS_OWNER_DETAILS.values().stream().filter(e -> businessType == e.getType()).collect(Collectors.toList());
    }

    public static User getUserByBookingCode(String bookingCode) {
        return USER_DETAILS.get(bookingCode);
    }

    public static void addUser(User user) {
        USER_DETAILS.put(user.getBookingCode(), user);
    }

    public static List<User> getUsers() {
        return USER_DETAILS.values().stream().collect(Collectors.toList());
    }

    public static void removeUser(String bookingCode){
        USER_DETAILS.remove(bookingCode);
    }
}
