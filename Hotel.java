/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Pooja
 */
public class Hotel implements Serializable {
    private String cityName;
    private String roomType;
    private int hotelId;
    private int price;
    
    
    public Hotel(String cityName,int hotelId, String roomType,int price) {
        this.cityName=cityName;
        this.hotelId= hotelId;
        this.roomType= roomType;
        this.price= price;
    }

    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * @return the roomType
     */
    public String getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    /**
     * @return the hotelId
     */
    public int getHotelId() {
        return hotelId;
    }

    /**
     * @param hotelId the hotelId to set
     */
    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return "Hotel [Id="+hotelId+", City= "+cityName+", Room_Type="+roomType+", Price= "+price+"]";
    }
    
    //give list in ascending order
    public static Comparator<Hotel> sortAscending = new Comparator<Hotel>(){
        public int compare(Hotel h1,Hotel h2){
            if(h1.getPrice()< h2.getPrice()){
                return -1;
            }
            else if(h1.getPrice() > h2.getPrice()){
                return 1;
            }
            else{
                return 0;
             }
        }
    };
         
    public static Comparator<Hotel> sortDescending = new Comparator<Hotel>(){
        public int compare(Hotel h1,Hotel h2){
            if(h2.getPrice()< h1.getPrice()){
                return -1;
            }
            else if(h2.getPrice() > h1.getPrice()){
                return 1;
            }
            else{
                return 0;
             }
        }
    };
    
}
