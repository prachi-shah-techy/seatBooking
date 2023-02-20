package seat.booking.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessOwner {

    public enum BUSINESS_TYPE {
        RESTAURANT, AIRPORT, CINEMA
    }

    ;

    private String name;
    private Date startTime;
    private Date endTime;


    private BUSINESS_TYPE type;
    public Room room;
    private BigDecimal price;

    public BusinessOwner(String name, Date startTime, Date endTime, BUSINESS_TYPE type, Room room, BigDecimal price) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.room = room;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BUSINESS_TYPE getType() {
        return type;
    }

    public void setType(BUSINESS_TYPE type) {
        this.type = type;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BusinessOwner{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", room=" + room +
                ", price=" + price +
                '}';
    }
}
