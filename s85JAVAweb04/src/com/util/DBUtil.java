package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class DBUtil {
    public static Vector<Connection> connectionPool=new Vector<Connection>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < 10; i++) {
                Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sunjob", "root", "root");
                connectionPool.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    public static
}
