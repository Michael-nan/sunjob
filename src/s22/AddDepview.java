package s22;

import s22.pojo.Dep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class AddDepview extends JDialog {

        private JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        private JTable table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };//表格中显示什么数据？

        private JLabel genderLabel = new JLabel("性别");
        private JRadioButton maleButton = new JRadioButton("男");
        private JRadioButton femaleButton = new JRadioButton("女");

        private JLabel didLabel = new JLabel("所在部门");
        private JComboBox<Dep> depbox = new JComboBox<Dep>();

        public AddDepview() {

            setTitle("查找部门");
            setSize(600, 500);//设置窗体的大小
            setLocationRelativeTo(null);//设置界面居中
            setResizable(false);//设置大小是否可以改变

            //表头
            Vector<String> thVector = new Vector<String>();
            thVector.add("编号");
            thVector.add("姓名");
            thVector.add("性别");
            thVector.add("联系电话");
            thVector.add("身份证号码");
            thVector.add("所在部门");

            //表数据
            Vector<Vector<String>> dataVector = new Vector<Vector<String>>();
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                String sql = "select empyid,empname,empsex,empphone,empid,empbid  from emp ";
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Vector<String> vector = new Vector<String>();
                    vector.add(rs.getString(1));//rs.getObject   Object
                    vector.add(rs.getString(2));
                    vector.add(rs.getString(3).equals("1") ? "男" : "女");
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


            //表模型
            DefaultTableModel defaultTableModel = new DefaultTableModel(dataVector, thVector);
            table.setModel(defaultTableModel);

            //设置列宽
            table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(60);
            table.getTableHeader().getColumnModel().getColumn(3).setPreferredWidth(600);


            scrollPane.setBounds(20, 20, 540, 350);
            this.add(scrollPane);//滚动面板加界面

            scrollPane.getViewport().add(table);

            genderLabel.setBounds(40, 400, 80, 20);
            maleButton.setBounds(160, 400, 60, 20);
            femaleButton.setBounds(240, 400, 60, 20);
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(maleButton);
            buttonGroup.add(femaleButton);
            maleButton.setSelected(true);

            didLabel.setBounds(320, 400, 80, 20);
            depbox.setBounds(420, 400, 100, 20);

            add(genderLabel);
            add(maleButton);
            add(femaleButton);

            add(didLabel);
            add(depbox);

            //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
            setLayout(null);
            setModal(true);//设置为模式对话框


            Connection connection2 = null;
            try {
                //初始化部门列表
                Class.forName("com.mysql.jdbc.Driver");

                //(3) 得到链接对象
                connection2 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/s85", "root", "root");
                //(4) 写sql
                String sql = "select depid , depname from dep ";

                PreparedStatement ps = connection2.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {//这个循环执行一次就表示数据库中有一条记录       一条记录就是一个部门
                    Dep dep = new Dep();
                    dep.setDepid(rs.getInt(1));
                    dep.setDepname(rs.getString(2));
                    depbox.addItem(dep);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    int row = table.getSelectedRow();
                    String gender = (String) table.getValueAt(row, 2);//选中行的性别

                    if (gender.equals("男")) {
                        maleButton.setSelected(true);
                    } else {
                        femaleButton.setSelected(true);
                    }

                    //绑定部门
                    String depname = (String) table.getValueAt(row, 5);

                    int i = depbox.getItemCount();//有几个选项
                    System.out.println(i);
                    for (int j = 0; j < i; j++) {
                        Dep dep = depbox.getItemAt(j);//逐个取出下拉列表的选项
                        if(depname.equals(dep.getDepname())){
                            depbox.setSelectedItem(dep);//就把dep对象设置为选中
                            return ;
                        }

                    }


                }
            });


            setVisible(true);

        }


    }

