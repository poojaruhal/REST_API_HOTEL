/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Models.User;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Pooja 
 */
@Configuration
public class UserController {
       private static List<User> userList;   
       Map<Integer,User> api_userMap;
       private static final  int number_of_user=10;
       private static volatile UserController INSTANCE=null;
        private UserController() {
             userList = new ArrayList<User>();
             userList = setList();
             System.out.println("Created User List is "+userList);
             api_userMap=new HashMap<Integer,User>();
             api_userMap = setApiUserMap((ArrayList) userList); 
            System.out.println("Created Map for api_user Map is "+api_userMap);
         }
        
        //to get only single instance of user controller
        public static UserController getInstance(){
           if(INSTANCE == null){
               synchronized(UserController.class){
               if(INSTANCE==null){
                   INSTANCE = new UserController();
               }
           }
           }
           else
           {
            return INSTANCE;   
           }
           return INSTANCE;
        }
        
        public User getUser(int api_key){
            return api_userMap.get(api_key);
        }
        
        //check if user is allowed to hit or not
        public boolean checkValidity(int api_key) throws ParseException{
            User user= getUser(api_key);
            //if user is hitting api first time then set start date = now
            if(user.getHitCount()==0){
                user.setStartDate(new Date());
             }
            //if user is allowed then increment the hit_count
            if(user.isUserAllowed(new Date())){
                user.setHitCount(user.getHitCount());
                return true;
            }
            else{
                 return false;
            }
          
            }
        
        //suspend user for 5 sec
        public void suspendUser(int api_key){
            User user= getUser(api_key); 
           try {
                    TimeUnit.SECONDS.sleep(5);
                    user.setStartDate(new Date());
                    user.setHitCount(0);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        
        public List<User> setList(){
            List<User> userList=new ArrayList<User>();
            for(int i=0;i<number_of_user;i++){
                userList.add(new User());
            }
            return userList;
        }
        
          public Map<Integer,User> setApiUserMap(ArrayList userList){
           Map<Integer,User> api_userMap=new HashMap<Integer,User>();
            for(int i=1;i<number_of_user;i++){
               api_userMap.put(i, (User) userList.get(i-1));
            }
            return api_userMap;
        }
}
