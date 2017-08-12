/**
 * Service Class
 * - Return the rate for a hike trip
 *
 * @author Brandon Tarney
 * @since 08/11/2017
 */
package com.brandontarney.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Tarney
 */
@Path("service")
public class Service {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Service
     */
    public Service() {
    }

    /**
     * Retrieves representation of an instance of
     * com.brandontarney.service.Service
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/getHikeRate/")
    @Produces(MediaType.TEXT_HTML)
    public String getHikeRate(
            @QueryParam("hike") String hikeStr,
            @QueryParam("datepicker") String date,
            @QueryParam("duration") int duration) {

        try {
            /*
            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/Tarney/tmp/LOG"));
            writer.write("Hike: " + hikeStr + "\nDate: " + date + "\nduration: " + duration);
            writer.close();
            */
            
            Rates.HIKE hike = HikeQueryParser.getHike(hikeStr);

            int[] monthDayYear = HikeQueryParser.getDate(date);
            int month = monthDayYear[0];
            int day = monthDayYear[1];
            int year = monthDayYear[2];

            Rates rate = this.getRate(hike, duration, year, month, day);

            if (rate.isValidDates()) {
                //FORMAT RATES as HTML & RETURN
                System.out.println("rate IS valid");
                return formatHtmlResponse(rate, "VALID HIKE RATE");
            } else {
                //FORMAT ERROR/RATES as HTML & RETURN
                System.out.println("rate is INvalid");
                return formatHtmlResponse(rate, "INVALID HIKE RATE");
            }
            /*
             */
        } catch (BadQueryStringException exception) {
            //FORMAT ERROR/RATES as HTML & RETURN
            return "<h1>Exception</h1><p> Details: " + exception.getMessage() + "</p>";
        
        } /* catch (IOException e) {
            return "<h1>Exception</h1><p> Details: " + e.getMessage() + "</p>";
        }
            */

    }
    
    /**
     * Create HTML String Response for rate
     * @param   rate    rate, including details about the rate
     * @param   type    string, will be the H1 tag
     * @return          String HTML rate details
    */
    public static String formatHtmlResponse(Rates rate, String type) {
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("<h1>" + type + "</h1>");
        returnStr.append("<p>Rate: " + rate.getCost() + "</p>");
        returnStr.append("<p>Details: " + rate.getDetails() + "</p>");
        returnStr.append("<a href=\"http://localhost:8084/tarneyHW_m12_605481/index.html\">Try Again?</a>");
        return returnStr.toString();      
    }

    /**
     * Compute the rate associated with a hike
     *
     * @param rate preliminary rate information of hike
     * @param duration duration of hike
     * @param year time of hike
     * @param month time of hike
     * @param day time of hike
     *
     * @return Final rate information for hike
     */
    public static Rates getRate(Rates.HIKE hike, int duration, int year, int month, int day) {
        BookingDay startDate = new BookingDay(year, month, day);
        Rates rate = new Rates(hike);
        rate.setBeginDate(startDate);
        rate.setDuration(duration);
        return rate;
    }

    @GET
    @Path("/sanityTest/")
    @Produces(MediaType.TEXT_HTML)
    public String sanityTest() {
        return "<h1>SHIT WORKS</h1>";
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
    }

    @GET
    @Path("/querytest/")
    @Produces(MediaType.TEXT_HTML)
    public String getHtml(@QueryParam("id") String methodId, @QueryParam("id2") String methodId2) {
        return "<h1>TEST</h1><p>TEST</p><br/><p>" + methodId + methodId2 + "</p>";
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Service
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
}
