/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zhengyangduan
 */
package controller;
import java.sql.*;
import java.util.*;
        
public class userDA {
    boolean bInited = false;
    
    // load jdbc
    public void initJDBC() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver"); 
        bInited = true;
    }
    
    // method create connnection to MySQl DB
    public Connection getConnection() throws ClassNotFoundException,SQLException{
        if(!bInited){
            initJDBC();
        }
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/grants","root","ITEC4010");
        return conn;
    }
    //method that checks if the credential match data in database
    public boolean loginSucceed(String userName, String pwd){
        boolean returnValue = false;
        String sql = "SELECT * FROM Applicant"; // get all applicant data
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);// store all data to result set
            while(rs.next()){
                // compare credential to all records.
                // return ture when find match.
                String userNameInDB = rs.getString("FMID");
                String passwordInDB = rs.getString("PWD");
                if(userNameInDB.equals(userName) && passwordInDB.equals(pwd)){
                    returnValue = true;
                    break;
                }
            }
            conn.close();
            stmt.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    
    
}
