/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Resources;

import Controller.HotelController;
import Controller.UserController;
import Models.Hotel;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * REST Web Service
 *
 * @author sony
 */
@Path("cities")
public class CitiesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CitiesResource
     */
    public CitiesResource() {
    }

    /**
     * Retrieves representation of an instance of hotel.CitiesResource
     * @param city_name
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    @Path("{api-key}/cities/{city_name}/{sortList}")
    public String getJson(@PathParam("api-key") int api_key, @PathParam("city_name") String city_name, @PathParam("sortList") boolean sortList) throws ParseException {
        //get user controller instance
        UserController uController = UserController.getInstance();        
        //if user is allowed to get data or not
        if(uController.checkValidity(api_key)){
            HotelController hController = new HotelController();
            List<Hotel> hotelList = hController.searchHotel(city_name, sortList);
            StringBuffer resultList = new StringBuffer();
            for(Hotel h: hotelList){
                resultList.append(h.toString());
                resultList.append(System.getProperty("line.separator"));
            }
            return resultList.toString();
        }
        else{
             uController.suspendUser(api_key); 
             return "You are not allowed to perform HTTP call";
        }
    }

    /**
     * PUT method for updating or creating an instance of CitiesResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
