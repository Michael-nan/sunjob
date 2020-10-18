package s22;

import s22.pojo.Dep;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddEmpview extends JDialog {

    private JLabel empnameLabel = new JLabel("姓名");
    private JTextField empnameField = new JTextField();

    private JLabel empgenderLabel = new JLabel("性别");
    private JRadioButton maleButton = new JRadioButton("男");
    private JRadioButton femaleButton = new JRadioButton("女");

    private JLabel emptelLabel = new JLabel("联系电话");
    private JTextField emptelField = new JTextField();
    private JLabel checktelLabel = new JLabel("");

    private JLabel empidendityLabel = new JLabel("身份证号码");
    private JTextField empidendityField = new JTextField();

    private JLabel empdepLabel = new JLabel("所在部门");
    // private JTextField empdepField = new JTextField();
    private JComboBox<Dep> depBox = new JComboBox<Dep>();//下拉

    private JButton addButton = new JButton("添加");


    public AddEmpview() {

        setTitle("添加员工");
        setSize(600, 500);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        empnameLabel.setBounds(60, 20, 80, 20);
        empnameField.setBounds(160, 20, 100, 20);

        empgenderLabel.setBounds(60, 60, 80, 20);
        maleButton.setBounds(160, 60, 60, 20);
        femaleButton.setBounds(220, 60, 60, 20);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        maleButton.setSelected(true);


        emptelLabel.setBounds(60, 100, 80, 20);
        emptelField.setBounds(160, 100, 100, 20);
        checktelLabel.setBounds(270, 100, 60, 20);

        empidendityLabel.setBounds(60, 140, 80, 20);
        empidendityField.setBounds(160, 140, 100, 20);

        empdepLabel.setBounds(60, 180, 80, 20);
        depBox.setBounds(160, 180, 100, 20);

        addButton.setBounds(200, 220, 60, 20);


        add(empnameLabel);
        add(empnameField);

        add(empgenderLabel);
        add(maleButton);
        add(femaleButton);

        add(emptelLabel);
        add(emptelField);
        add(checktelLabel);

        emptelField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = emptelField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    checktelLabel.setText("√");
                } else {
                    checktelLabel.setText("×");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String regex = "^1[3456789]\\d{9}$";
                Pattern pattern = Pattern.compile(regex);
                String target = emptelField.getText();
                //用户填写的电话
                Matcher matcher = pattern.matcher(target);
                if (matcher.find()) {
                    checktelLabel.setText("√");
                } else {
                    checktelLabel.setText("×");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                System.out.println("3");
            }
        });


        add(empidendityLabel);
        add(empidendityField);

        add(empdepLabel);
        add(depBox);
        Connection connection = null;
        try {
            //初始化部门列表
            Class.forName("com.mysql.jdbc.Driver");

            //(3) 得到链接对象
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "admin");
            //(4) 写sql
            String sql = "select depid , depname from dep ";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//这个循环执行一次就表示数据库中有一条记录       一条记录就是一个部门
                Dep dep = new Dep();
                dep.setDepid(rs.getInt(1));
                dep.setDepname(rs.getString(2));
                depBox.addItem(dep);//有一个部门  就往下拉列表中添加一个对象
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        add(addButton);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String empname = empnameField.getText();
                //取性别?
                int empgender = 1;

                if (femaleButton.isSelected()) {
                    empgender = 2;
                }

                String empidendity = empidendityField.getText();
                String emptel = emptelField.getText();

                // 如何取出部门编号

                // String did = depBox.getSelectedItem().toString();//转换为字符串
                int did = ((Dep)depBox.getSelectedItem()).getDepid();

                Connection connection = null;

                try {
                    //写JDBC
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "admin");
                    String sql = "insert into emp(empname , empidendity , empgender , emptel , did ) values(?,?,?,?,?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1, empname);
                    ps.setObject(2, empidendity);
                    ps.setObject(3, empgender);
                    ps.setObject(4, emptel);
                    ps.setObject(5, did);

                    //执行操作
                    int n = ps.executeUpdate();
                    // n标是添加的条数    n=0  表示添加了0条
                    if (n == 0) {
                        JOptionPane.showMessageDialog(null, "添加失败", "提示", JOptionPane.YES_NO_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.YES_NO_OPTION);
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    //关闭连接
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


}

