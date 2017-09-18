/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.Hotel;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static javax.ws.rs.HttpMethod.GET;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author sony
 */

@Controller
public class HotelController {
    //file name of csv file to read hotel data
      public static String filename="C:\\Users\\sony\\Documents\\NetBeansProjects\\WebApplication1/Hotel-DB.csv";
      private static List<Hotel> hotels;   
      Map<Integer,Integer> id_priceMap;
        public HotelController() {
            id_priceMap=new HashMap<Integer,Integer>();
             hotels = new ArrayList<>();
             hotels = readeCSVFile(filename);
         }
    
       public  List<Hotel> readeCSVFile(String fileName){
            Path filePath=Paths.get(fileName);
            Map<Integer,Integer> id_priceMap=new HashMap<Integer,Integer>();
            try(BufferedReader br =Files.newBufferedReader(filePath,StandardCharsets.US_ASCII)){
                String line=br.readLine();
                while(line !=null){
                    String[] columns = line.split(",");
                    Hotel hotel=createHotel(columns);
                    id_priceMap.put(hotel.getHotelId(), hotel.getPrice());
                    hotels.add(hotel);
                    line = br.readLine();
                }
            }catch(IOException ioe){
                ioe.printStackTrace();
            }
                return hotels;
        }
                                 
        private static Hotel createHotel(String[] hotelData){
    //  System.out.println("hotel Data "+hotelData.toString());
            String cityName=hotelData[0];
            int hotelId= Integer.parseInt(hotelData[1]);
            String roomType= hotelData[2];
            int price = Integer.parseInt(hotelData[3]);
            return new Hotel(cityName,hotelId,roomType,price);
    }
        

    public Hotel getHotel(int hotelId){
           for(Hotel h : hotels){
                if(h.getHotelId()==hotelId){
                    return h;
                }
            }
        return null;
    }
    
    /**
     *@desc search hotels by city name
     * @param city_name
     * @param sortFlag
     * @return
     */
   
    public List<Hotel> searchHotel( String city_name, boolean sortList){
        List<Hotel> hotelList=new ArrayList<Hotel>();   
        for(Hotel h : hotels){
            if(h.getCityName().equalsIgnoreCase(city_name)){
                hotelList.add(h);
            }   
         }
        if(sortList==true){
                Collections.sort(hotelList, Hotel.sortAscending);  
        }
        else
        {
                  Collections.sort(hotelList, Hotel.sortDescending);
        }

        return hotelList;
    }  
}
