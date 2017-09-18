/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Pooja
 */
public class User {
    private int hitCount; //to keep track of number of access
    private Date startDate = null;
    private Date endDate = null;
    private static final int TIME_LIMIT = 10;
    private static final int HIT_LIMIT = 5;
    
    public void User( ){
        this.setHitCount(0);   
    }
    
    public boolean isUserAllowed(Date endDate) throws ParseException{
        long sec = getDiff(endDate) ;
        //if user has not croassed the hit_count and accessing within time_frame then allow
        if(this.hitCount <= HIT_LIMIT && sec <= TIME_LIMIT  ) {
            return true;
        }
        return false;
    }
    
    public void setUserSucces()
    {
        this.hitCount++;
    }
    
    //get time frame by taking differece in seconds 
    public long getDiff(Date endDate) throws ParseException{
        Date d1;
        d1 = this.getStartDate();
        this.setEndDate(endDate);
        Date d2=this.getEndDate();
        long diff = d2.getTime()-d1.getTime();
        long diffSeconds = diff/(60 * 1000);
        return diffSeconds;
        
    }
    /**
     * @return the hitCount
     */
    public int getHitCount() {
        return hitCount;
    }

    /**
     * @param hitCount the hitCount to set
     */
    public void setHitCount(int hitCount) {
        this.hitCount = hitCount+1;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

   public String toString(){
        return "User [Hit_count_till_now="+hitCount+", Start_date= "+startDate+", End_date="+endDate+"]";
    }
}
