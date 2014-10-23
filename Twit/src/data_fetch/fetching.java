package data_fetch;


import twitter4j.*;
import twitter4j.auth.AccessToken;

import org.apache.log4j.Logger;

import props.Access_property;
import props.Properties_assign;
import props.Property_validate;

import database.UserDao;



public class fetching {
	
	private static Logger logger = Logger.getLogger(fetching.class);


	public static void main(String[] args) {
		
		logger.debug("Starting");
		
		
		
		TwitterFactory twitterFactory = new TwitterFactory();
		Twitter twitter1 = twitterFactory.getInstance();
	
		
        try {	
		Property_validate.initialize("conf/twitter4j.properties");
        }
        catch (Exception e) {
				logger.error("Couldn't load property file, please check your configuration.");
        	
       }
		
		
		
		
        twitter1.setOAuthConsumer(Access_property
    			.getProperty(Properties_assign.consumerKey1),Access_property
    			.getProperty(Properties_assign.consumerSecret1));
      	  
    	        //setup OAuth Access Token
        twitter1.setOAuthAccessToken(new AccessToken(Access_property.getProperty(Properties_assign.accessToken), Access_property.getProperty(Properties_assign.accessTokenSecret)));
		
		
		
           long id_me;
		try {
			id_me = twitter1.getId();
            User me=twitter1.showUser(id_me);
			twitter1.showUser(id_me);
			logger.debug("hey");
			UserDao.insertinfo(me);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		
		
		
		System.out.println(Access_property.getProperty(Properties_assign.consumerKey1));
	
	    
	}}



