package s20;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test1 extends JFrame {
    private JLabel usernameLabel=new JLabel("用户名");
    private JTextField usernameField=new JTextField();
    private JLabel passwordLabel=new JLabel("密码");
    private JTextField passwordField=new JPasswordField();
    private JButton resetButton = new JButton("重置");
    private JButton loginBUtton = new JButton("登录");
    public void init(){
        usernameLabel.setBounds(100,60,100,60);
        usernameField.setBounds(300,60,200,60);
        passwordLabel.setBounds(100,180,100,60);
        passwordField.setBounds(300,180,200,60);
        resetButton.setBounds(100,300,100,60);
        loginBUtton.setBounds(300,300,100,60);
        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resetButton);
        this.add(loginBUtton);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameField.setText("");
                passwordField.setText("");
            }
        });
    }

    public static void main(String[] args) {
        Test1 test1=new Test1();
        test1.setSize(600,420);
        test1.setLocationRelativeTo(null);
        test1.setResizable(false);
        test1. setLayout(null);
        test1.setVisible(true);
        test1.init();

    }
}
