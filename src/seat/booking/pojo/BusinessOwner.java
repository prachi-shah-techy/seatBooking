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
    public GrandeFloorGrid grandeFloorGrid;
    private BigDecimal price;

    public BusinessOwner(String name, Date startTime, Date endTime, BUSINESS_TYPE type, GrandeFloorGrid grandeFloorGrid, BigDecimal price) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.grandeFloorGrid = grandeFloorGrid;
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

    public GrandeFloorGrid getGrandeFloorGrid() {
        return grandeFloorGrid;
    }

    public void setGrandeFloorGrid(GrandeFloorGrid grandeFloorGrid) {
        this.grandeFloorGrid = grandeFloorGrid;
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
                ", grandeFloorGrid=" + grandeFloorGrid +
                ", price=" + price +
                '}';
    }
}
