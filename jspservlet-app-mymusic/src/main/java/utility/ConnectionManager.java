package utility;
import java.sql.*;
import java.lang.*;


public class ConnectionManager{
    public static void main(String args[]) throws ClassNotFoundException,SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con=null;
        con=DriverManager.getConnection("jdbc:mysql://local:3306/sys","root","examly");
        if(con!=null){
            System.out.println("Connection Established");
        }
        else System.out.println("Connection not established");
    }
}