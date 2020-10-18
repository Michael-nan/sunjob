package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class LoginView extends JFrame {


    private JLabel usernameLabel = new JLabel("�û���");
    private JTextField usernameField = new JTextField();

    private JLabel passwordLabel = new JLabel("����");
    // ���������������
    private JTextField passwordField = new JPasswordField();

    private JButton resetButton = new JButton("����");
    private JButton loginButton = new JButton("��¼");


    public LoginView() {
        setTitle("��ӭʹ�����ܸ߼�����ϵͳ");
        setSize(600, 500);//���ô���Ĵ�С
        setLocationRelativeTo(null);//���ý������
        setResizable(false);//���ô�С�Ƿ���Ըı�
        //��Ʋ��ַ�ʽ  ���Բ���   ���ÿؼ��Ĳ���  ����������ȷ���ؼ�λ��
        setLayout(null);

        usernameLabel.setBounds(40, 40, 60, 20);
        usernameField.setBounds(160, 40, 100, 20);

        passwordLabel.setBounds(40, 100, 60, 20);
        passwordField.setBounds(160, 100, 100, 20);

        resetButton.setBounds(40, 160, 60, 20);
        loginButton.setBounds(200, 160, 60, 20);


        this.add(usernameLabel);
        this.add(usernameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(resetButton);
        this.add(loginButton);


        //���ð�ť
        resetButton.addActionListener((e) -> {
            resetButton.setText("");
            loginButton.setText("");
        });

        //����ļ����¼�  �ر�
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //����µ��¼�
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane.showConfirmDialog(null, "���Ҫ�˳���", "�˳�", JOptionPane.YES_NO_OPTION);
                if (n == 0) {
                    System.exit(0);
                }
            }
        });

        //��ɵ�¼�Ĺ���
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //1.ȡ���û���д���û���������
                String username = usernameField.getText();
                String password = passwordField.getText();

                //2.���û���д������ȥ���ݿ��в���  �� �ҵ���û�ҵ�
                //(1) ��������
                //(2)��������
                Connection connection = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");

                    //(3) �õ����Ӷ���
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "admin");
                    //(4) дsql
                    String sql = "select username , password from userinfo where username = ? and password = ? ";

                    //(5)���벢�ҷ���sql
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, username);
                    ps.setObject(2, password);

                    //(6)ִ�в���
                    ResultSet rs = ps.executeQuery();

                    //3.�������ݿ��ѯ���������ͬ����Ӧ
                    if (rs.next()) {
                        //1.�رյ�¼����
                        LoginView.this.dispose();
                        //2.��������
                        new MainView();// new ��������

                    } else {
                        JOptionPane.showMessageDialog(null, "�û������������", "��ʾ", JOptionPane.YES_NO_OPTION);
                        usernameField.setText("");
                        passwordField.setText("");
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //(7) �ر�����
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

