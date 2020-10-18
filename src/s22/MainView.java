package s22;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainView extends JFrame {

    //设置菜单栏
    JMenuBar menuBar = new JMenuBar();

    JMenu depMenu = new JMenu("部门管理");
    JMenuItem addDepItem = new JMenuItem("新增部门");
    JMenuItem delDepItem = new JMenuItem("删除部门");
    JMenuItem updateDepItem = new JMenuItem("修改部门");
    JMenuItem queryDepItem = new JMenuItem("查询部门");


    JMenu empMenu = new JMenu("员工管理");
    JMenuItem addEmpItem = new JMenuItem("新增员工");
    JMenuItem delEmpItem = new JMenuItem("删除员工");
    JMenuItem updateEmpItem = new JMenuItem("修改员工");
    JMenuItem queryEmpItem = new JMenuItem("查询员工");



    public MainView(){

        setTitle("欢迎使用赛杰高级管理系统");
        setSize(600, 420);//设置窗体的大小
        setLocationRelativeTo(null);//设置界面居中
        setResizable(false);//设置大小是否可以改变
        //设计布局方式  绝对布局   设置控件的参数  根据坐标来确定控件位置
        setLayout(null);

        menuBar.setBounds(0,0,600 ,20);
        menuBar.add(depMenu);
        depMenu.add(addDepItem);
        depMenu.add(delDepItem);
        depMenu.add(updateDepItem);
        depMenu.add(queryDepItem);

        menuBar.add(empMenu);
        empMenu.add(addEmpItem);
        empMenu.add(delEmpItem);
        empMenu.add(updateEmpItem);
        empMenu.add(queryEmpItem);

        addDepItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddDepview();
            }
        });
        addEmpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEmpview();
            }
        });

        updateDepItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateDepview();
            }
        });
        updateEmpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateEmpview();
            }
        });
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

        add(menuBar);


        setVisible(true);
    }

    public static void main(String[] args) {
        new MainView();
    }

}

