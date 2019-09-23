/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.*;
import java.util.Random;
/**
 *
 * @author zhengyangduan
 */
public class applicationDA {
    boolean bInited = false;
    // load MySQl DB
    public void initJDBC() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        bInited = true;
        System.out.println("Success loading Mysql Driver!");
    }
    
    // Create connection
    public Connection getConnection() throws ClassNotFoundException,SQLException{
        if(!bInited){
            initJDBC();
        }
        Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/grants","root","ITEC4010");
        return conn;
    }
    
    public void addNewApplication(String fmid,String name,String address,String homeunit, String
            proposal, String justification, String budget,String rank){
        // generate a new APPID
        Random rand = new Random();
        int n = rand.nextInt(10000);
        String APPID =fmid + String.valueOf(n);
        //SQL insert new application to database
        String sql = "INSERT INTO Application(APPID,FMID,name,address,homeunit,prop"
                + "osal,justification,budget,apprank) Values('"+ APPID +"','"+fmid+"','"+
                name+"','"+address+"','"+homeunit+"','"+
                proposal+"','"+justification+"','"+budget+"','"+rank+"')";
        Connection conn = null;
        Statement stmt = null;
        try{
            conn = getConnection();
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
            stmt.close();
            
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
       
    
    }
    public String searchApplications(String ID){
        
        String sql = "Select APPID from Application Where FMID =" + ID;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String result = "";
        try{
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);// store all data to result set
            while(rs.next()){
                result += rs.getString("APPID");
            }
            conn.close();
            stmt.close();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
