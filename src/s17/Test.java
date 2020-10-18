package s17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test{
    public static void main(String[] args) {
        Connection connection  = null;
        try {
            //2.加载驱动
            Class.forName("com.mysql.jdbc.Driver");

            //3.得到连接对象
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gongda","root","root");

            //4.写sql语句  crud
            String sql = "insert into dep(depid , depname) values(2,'国防部')";

            //5.创建指令对象
            Statement statement = connection.createStatement();

            //6.执行操作
            int n = statement.executeUpdate(sql);
            System.out.println(n);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //7.关闭连接
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}