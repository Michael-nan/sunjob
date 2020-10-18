package s22;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class LoginView extends JFrame {
    private JLabel usernameLabel = new JLabel("用户名");
    private JTextField usernameField = new JTextField();
    private JLabel passwordLabel = new JLabel("密码");
    // 把密码框内容隐藏
    private JTextField passwordField = new JPasswordField();
    private JButton resetButton = new JButton("重置");
    private JButton loginButton = new JButton("登录");

    public LoginView(){
        setTitle("欢迎使用赛杰高级管理系统");
        setSize(600,420);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        usernameLabel.setBounds(100,60,100,60);
        usernameField.setBounds(300,60,200,60);
        passwordLabel.setBounds(100,180,100,60);
        passwordField.setBounds(300,180,200,60);
        resetButton.setBounds(100,300,100,60);
        loginButton.setBounds(300,300,100,60);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resetButton);
        this.add(loginButton);
        resetButton.addActionListener((e) -> {
            usernameField.setText("");
            passwordField.setText("");
        });
       /* resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });*/
       setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "真的要退出吗？", "退出", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });
         /*
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {

                //弹出对话框   n  能确定用户点击的是哪个按钮
                int n = JOptionPane.showConfirmDialog(null , "真的要退出嘛?" ,"退出" , JOptionPane.YES_NO_OPTION);

                if(n==0){
                    System.exit(0);
                }

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        */
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //1.取出用户填写的用户名和密码
                String username = usernameField.getText();
                String password = passwordField.getText();

                //2.把用户填写的数据去数据库中查找  ？ 找到和没找到
                //(1) 拷贝驱动
                //(2)加载驱动
                Connection connection = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    //(3) 得到链接对象
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                    //(4) 写sql
                    String sql = "select username , password from userinfo where username = ? and password = ? ";

                    //(5)编译并且发送sql
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, username);
                    ps.setObject(2, password);

                    //(6)执行操作
                    ResultSet rs = ps.executeQuery();

                    //3.根据数据库查询结果做出不同的响应
                    if (rs.next()) {
                        //1.关闭登录界面
                        LoginView.this.dispose();
                        //2.打开主界面
                        new MainView();// new 产生对象

                    } else {
                        JOptionPane.showMessageDialog(null, "用户名或密码错误", "提示", JOptionPane.YES_NO_OPTION);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //(7) 关闭链接
                    try {
                        connection.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
