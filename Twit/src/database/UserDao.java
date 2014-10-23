package database;

import helper.SQLUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import database.ConnPool;
import java.sql.*;





import twitter4j.*;
import twitter4j.auth.AccessToken;

import org.apache.log4j.Logger;







import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;



public class UserDao {

    private static Gson         gson                        = new Gson();
    private static final Logger logger                      = Logger.getLogger(UserDao.class);


    private static final String INSERT_PERSONAL_INFORMATION = "INSERT INTO twitter (user_id,friends_count,followers_count) VALUES(?,?,?)";

   
    
    public static void insertinfo(User usersManager) {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
        	logger.info("AAAAAAAAA");
       	ConnPool.getInstance().init();
            con = ConnPool.getInstance().getConnection();
          	logger.info("AAAAAAAAA");
//             Class.forName("oracle.jdbc.driver.OracleDriver");
        	
           
            
             logger.info("AAAAAAAAA");
        	// DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
          
/*             con= DriverManager.getConnection(
        	        "jdbc:oracle:thin:@localhost:1521:xe",
        	        "social",
        	        "social");
        	  logger.info("AAAAAAAAA");
 */       	
             stmt = con.prepareStatement(INSERT_PERSONAL_INFORMATION);

        	logger.info("AAAAAAAAA");
            stmt.setLong(1, usersManager.getId());
            stmt.setLong(2, usersManager.getFollowersCount());
            stmt.setLong(3, usersManager.getFriendsCount());

             

           
            stmt.executeUpdate();
            con.commit();
            
        } catch (Exception e) {
            logger.error("Couldn't insert user info to database : " + e);
        } finally {
            SQLUtil.forceCloseConnection(con, stmt, rs);
        }

    }
}
