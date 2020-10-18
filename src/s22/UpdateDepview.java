package s22;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class UpdateDepview extends JDialog {

    //滚动面板
    private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    private JTable table = new JTable() {
        @Override//设置表格是否可以编辑
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };//表格中显示什么数据？


    private JLabel depidLabel = new JLabel("部门编号");
    private JTextField depidField = new JTextField();

    private JLabel depnameLabel = new JLabel("部门名称");
    private JTextField depnameField = new JTextField();

    private JButton updateButton = new JButton("修改");


    private JButton delButton = new JButton("删除");


    public UpdateDepview() {
        setTitle("查找部门");
        setSize(600, 600);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);
        setModal(true);//设置为模式对话框

        depidLabel.setBounds(50 , 450 , 60 , 20);
        depidField.setBounds(120 , 450 , 100 , 20);
        depnameLabel.setBounds(240 , 450 , 60 , 20);
        depnameField.setBounds(320 , 450 , 100 , 20);

        updateButton.setBounds(450 , 450 , 60 , 20);

        add(depidLabel);
        add(depidField);
        depidField.setEditable(false);//设置不可编辑

        add(depnameLabel);
        add(depnameField);

        add(updateButton);


        Vector<String> thVector = new Vector<String>();
        thVector.add("部门编号");
        thVector.add("部门名称");

        //表数据的集合
        Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
        //表数据  从数据库中查出来

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
            String sql = "select depid , depname from dep";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {//循环一次  就是有一条记录   一条记录就对应一个vector
                Vector<String> vector = new Vector<String>();
                vector.add(rs.getString(1));
                vector.add(rs.getString(2));
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

        //表模型
        DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
        table.setModel(defaultTableModel);//把表模型赋值给表格

        scrollPane.getViewport().add(table);

        //表头不能重新排序
        table.getTableHeader().setReorderingAllowed(false);
        //表头不能改变宽度
        table.getTableHeader().setResizingAllowed(false);

        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);//设置居中
        table.setDefaultRenderer(Object.class, defaultTableCellRenderer);

        scrollPane.setBounds(20, 20, 540, 350);
        delButton.setBounds(500 , 400 , 60 , 20);
        add(delButton);
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
                String depid = (String) table.getValueAt(row , 0);//得到部门编号

                Connection connection = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                    String sql = "delete from dep where depid = ? ";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setObject(1 , depid);
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
                        connection.close();
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
                depidField.setText((String) table.getValueAt(row , 0));
                depnameField.setText((String) table.getValueAt(row , 1));
                //   }


            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String depid = depidField.getText();
                String depname = depnameField.getText();

                Connection connection1 = null;
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection1 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                    String sql = "update dep set depname = ? where depid = ? ";
                    PreparedStatement ps = connection1.prepareStatement(sql);
                    ps.setObject(1 , depname);
                    ps.setObject(2 , depid);

                    int n = ps.executeUpdate();

                    if(n==1){

                        //得到表格选中的行
                        table.setValueAt(depname , table.getSelectedRow() , 1);
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
                        connection1.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }



            }
        });



        setVisible(true);
    }


}
