/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Resources;

import Controller.HotelController;
import Controller.UserController;
import Models.Hotel;
import Models.User;
import java.text.ParseException;
import java.util.Date;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * REST Web Service
 *
 * @author sony
 */
@Path("/hotels")
public class HotelWorld {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HotelWorld
     */
    public HotelWorld() {
    }

    /**
     * Retrieves representation of an instance of hotel.HotelWorld
     * @return an instance of java.lang.String
     */
    @GET  
    @Produces("application/json")
    @Path("{api-key}/hotels/{hotel_id}")
    public String getJson(@PathParam("api-key") int api_key, @PathParam("hotel_id") int hotelId) throws ParseException {
        //get instance of user controller
      UserController uController = UserController.getInstance(); 
      //check for validitity of user
      if(uController.checkValidity(api_key)){
            HotelController hController = new HotelController();
            Hotel hotel = hController.getHotel(hotelId);
            return hotel.toString();  
      }
      else
      {
          //suspend user for 5 sec or if user has crossed hit count limit
          uController.suspendUser(api_key);
          return "You are not allowed to perform HTTP call";
      }
      // return "success";
    }

    /**
     * PUT method for updating or creating an instance of HotelWorld
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
