package seat.booking.pojo;

import seat.booking.util.DataHolder;

import java.math.BigDecimal;
import java.util.List;

public class User {

    private Long id;
    private String name;
    private BigDecimal paidPrice;
    private BigDecimal discountedPrice;
    private String bookingCode;

    private List<String> complaints;
    private Seat seat;
    private BusinessOwner businessOwner;

    public User(String name, BigDecimal paidPrice, Seat seat, BusinessOwner businessOwner) {
        this.id = Long.valueOf(DataHolder.getUsers().size() + 1);
        this.name = name;
        this.paidPrice = paidPrice;
        this.bookingCode = businessOwner.getName().substring(0, 3).toUpperCase() + businessOwner.getType().name().substring(0, 3) + id;
        this.seat = seat;
        this.businessOwner = businessOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public BusinessOwner getBusinessOwner() {
        return businessOwner;
    }

    public void setBusinessOwner(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    public String getBookingCode() {
        return bookingCode;
    }

    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public List<String> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<String> complaints) {
        this.complaints = complaints;
    }
}
