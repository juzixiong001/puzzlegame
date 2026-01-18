package com.juzixiong.test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setSize(603 , 680);
        jFrame.setTitle("演示事件");
        jFrame.setLayout(null);
        jFrame.setAlwaysOnTop(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        //创建一个按钮对象
        JButton jbt = new JButton("点我啊");
        jbt.setBounds(0 , 0 ,100 , 55);
        //给按钮添加动作监听
        //addActionListener ： 鼠标左键点击 / 空格

        //使用匿名内部类
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("不要点我~~~好害羞哦~");
            }
        });


        //把按钮添加进界面
        jFrame.getContentPane().add(jbt);


        jFrame.setVisible(true);



    }
}
