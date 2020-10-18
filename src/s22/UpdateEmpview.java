package s22;

import s22.pojo.Dep;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class UpdateEmpview extends JDialog {

    //滚动面板
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTable table = new JTable() {
        @Override//设置表格是否可以编辑
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };//表格中显示什么数据？
    private JLabel empyidLabel = new JLabel("员工编号");
    private JTextField empyidField = new JTextField();

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
    private JComboBox<Dep> depBox = new JComboBox<Dep>();//下拉
    private JButton updateButton = new JButton("修改");
    private JButton delButton = new JButton("删除");

    public UpdateEmpview() {
        setTitle("修改员工");
        setSize(600, 750);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        empyidLabel.setBounds(20,560,40,20);
        empyidField.setBounds(20,580,125,20);
        empnameLabel.setBounds(165, 560, 40, 20);
        empnameField.setBounds(165, 580, 125, 20);

        empgenderLabel.setBounds(310, 560, 40, 20);
        maleButton.setBounds(310, 580, 50, 20);
        femaleButton.setBounds(385, 580, 50, 20);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(maleButton);
        buttonGroup.add(femaleButton);

        maleButton.setSelected(true);


        emptelLabel.setBounds(20, 610, 40, 20);
        emptelField.setBounds(20, 630, 125, 20);
        checktelLabel.setBounds(145, 630, 10, 20);

        empidendityLabel.setBounds(165, 610, 40, 20);
        empidendityField.setBounds(165, 630, 125, 20);

        empdepLabel.setBounds(310, 610, 40, 20);
        depBox.setBounds(310, 630, 125, 20);

        updateButton.setBounds(455, 630, 60, 20);

        add(empyidLabel);
        add(empyidField);

        add(empnameLabel);
        add(empnameField);

        add(empgenderLabel);
        add(maleButton);
        add(femaleButton);

        add(emptelLabel);
        add(emptelField);
        add(checktelLabel);

        add(empidendityLabel);
        add(empidendityField);

        add(empdepLabel);
        add(depBox);


        Vector<String> thVector = new Vector<String>();
        thVector.add("员工编号");
        thVector.add("姓名");
        thVector.add("性别");
        thVector.add("联系电话");
        thVector.add("身份证号码");
        thVector.add("所在部门");
        //表数据的集合
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        //表数据  从数据库中查出来

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
            String sql = "select empyid,empname,empsex,empphone,empid,empbid  from emp";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//循环一次  就是有一条记录   一条记录就对应一个vector
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
                vector.add(rs.getString(3));
                vector.add(rs.getString(4));
                vector.add(rs.getString(5));
                vector.add(rs.getString(6));
                dataVector.add(vector);
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
        Connection connection3 = null;
        try {
            //初始化部门列表
            Class.forName("com.mysql.jdbc.Driver");

            //(3) 得到链接对象
            connection3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
            //(4) 写sql
            String sql = "select depid , depname from dep ";

            PreparedStatement ps = connection3.prepareStatement(sql);

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
                connection3.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        //表模型
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);//把表模型赋值给表格

        scrollPane.getViewport().add(table);

        //表头不能重新排序
        table.getTableHeader().setReorderingAllowed(false);
        //表头不能改变宽度
        table.getTableHeader().setResizingAllowed(false);


       /* table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(600);
       */
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);//设置居中
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

        scrollPane.setBounds(20, 20, 560, 500);
        delButton.setBounds(455,580,60,20);
        add(delButton);
        add(updateButton);
        add(scrollPane);

        delButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//得到选中的行
                if(row==-1){
                    JOptionPane.showMessageDialog(null , "请选中先");
                    return ;//后面删除的代码不要执行了
                }

                //删除只能根据部门编号删除
                String empyid = (String) table.getValueAt(row , 0);//得到部门编号

                Connection connection1 = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                    String sql = "delete from emp where empyid = ? ";
                    PreparedStatement ps = connection1.prepareStatement(sql);
                    ps.setObject(1 ,empyid);
                    int n = ps.executeUpdate();

                    if(n>0){
                        JOptionPane.showMessageDialog(null , "删除成功");
                        //数据删了    才删除界面
                        ((DefaultTableModel)table.getModel()).removeRow(row);

                    }else{
                        JOptionPane.showMessageDialog(null , "删除失败");
                    }
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        connection1.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                //if(e.getClickCount()==2){

                int row = table.getSelectedRow();
                empyidField.setText((String) table.getValueAt(row , 0));
                empnameField.setText((String) table.getValueAt(row , 1));
                String gender = (String) table.getValueAt(row, 2);//选中行的性别

                if (gender.equals("男")) {
                    maleButton.setSelected(true);
                } else {
                    femaleButton.setSelected(true);
                }
                emptelField.setText((String) table.getValueAt(row , 3));
                empidendityField.setText((String) table.getValueAt(row , 4));
               String depname= (String) table.getValueAt(row,5);
               int i=depBox.getItemCount();
               for (int j=0;j<i;j++){
                   Dep dep=depBox.getItemAt(j);
                   if (depname.equals(dep.getDepname())){
                       depBox.setSelectedItem(dep);
                       return ;
                   }
                }
                //   }


            }
        });


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String empyid = empyidField.getText();
                String empname = empnameField.getText();
                ButtonModel empsex=buttonGroup.getSelection();
                String empphone= emptelField.getText();
                String empid = empidendityField.getText();
                String empbid = (String) depBox.getSelectedItem();

                Connection connection2 = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                    String sql = "update emp set empname = ?,empsex = ?,empphone = ?,empid = ?,empbid = ?   where empyid = ? ";
                    PreparedStatement ps = connection2.prepareStatement(sql);
                    ps.setObject(1 , empname);
                    ps.setObject(2 ,empsex);
                    ps.setObject(3 ,empphone);
                    ps.setObject(4 ,empid);
                    ps.setObject(5 ,empbid);
                    ps.setObject(6 ,empyid);

                    int n = ps.executeUpdate();

                    if(n==1){

                        //得到表格选中的行
                        table.setValueAt(empname , table.getSelectedRow() , 1);
                        table.setValueAt(empsex , table.getSelectedRow() , 2);
                        table.setValueAt(empphone , table.getSelectedRow() , 3);
                        table.setValueAt(empid , table.getSelectedRow() , 4);
                        table.setValueAt(empbid , table.getSelectedRow() , 5);


                        JOptionPane.showMessageDialog(null , "修改成功");
                    }else{
                        JOptionPane.showMessageDialog(null , "修改失败");
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        connection2.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }



            }
        });



        setVisible(true);
    }


}

