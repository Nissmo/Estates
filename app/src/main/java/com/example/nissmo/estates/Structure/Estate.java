package com.example.nissmo.estates.Structure;

/**
 * Created by nissmo on 21.04.17.
 */

public class Estate {
    private String region;
    private String type;
    private String price;
    private String floors;
    private String rooms;
    private String area;
    private String text;

    public Estate()
    {

    }
    public Estate(String region, String type, String price, String floors, String rooms, String area, String text) {
        this.region = region;
        this.type = type;
        this.price = price;
        this.floors = floors;
        this.rooms = rooms;
        this.area = area;
        this.text = text;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
