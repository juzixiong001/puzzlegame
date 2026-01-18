package com.juzixiong.test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyJFrame extends JFrame implements MouseListener {

    JButton jbt = new JButton("点我啊");

    public MyJFrame() {
        //创建一个JFrame对象
        JFrame jFrame = new JFrame();
        //初始化界面宽高
        jFrame.setSize(603, 680);
        //设置界面标题
        jFrame.setTitle("鼠标监听");
        //设置关闭模式
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置界面据中
        jFrame.setLocationRelativeTo(null);
        //设置布局为空
        jFrame.setLayout(null);
        //设置界面置顶
        jFrame.setAlwaysOnTop(true);


        //创建一个按钮对象

        jbt.setBounds(0, 0, 100, 100);
        jbt.addMouseListener(this);


        jFrame.getContentPane().add(jbt);


        jFrame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("鼠标点击了");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("鼠标按下了");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("鼠标释放了");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("鼠标划入了");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("鼠标划出了");
    }

}
