package com.lee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class JdbcPool {
    static int length = 10;
    static Map<Connection,Boolean> conMap = new HashMap<>(length);

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc-config");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        String len = null;
        try {
            len = bundle.getString("length");
        } catch (Exception e) {
            len = "";
        }
        if (!"".equals(len)){
            length = Integer.parseInt(len);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (int i=0;i<length;i++){
            try {
                Connection con= DriverManager.getConnection(url,user,password);
                conMap.put(con,true);
            } catch (SQLException a) {
                a.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        for(Connection con : conMap.keySet()){
            if (conMap.get(con)){
                return con;
            }
        }
        return null;
    }

    public static void closeConnection(Connection con){
        conMap.put(con,false);
    }

    public static void closePool(){
        for (Connection connection: conMap.keySet()) {
            try {
                connection.close();
            } catch (SQLException b) {
                b.printStackTrace();
            }
        }
    }
}