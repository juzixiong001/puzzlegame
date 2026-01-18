package com.juzixiong.test;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyJFrame2 extends JFrame implements KeyListener {


    public MyJFrame2(){
        //初始化界面宽高
        this.setSize(603, 680);
        //设置界面标题
        this.setTitle("键盘监听");
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //设置界面据中
        this.setLocationRelativeTo(null);
        //设置布局为空
        this.setLayout(null);
        //设置界面置顶
        this.setAlwaysOnTop(true);

        //给整个窗体添加键盘监听
        this.addKeyListener(this);


        this.setVisible(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("按下不松");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("键盘释放");
         int keyCode = e.getKeyCode();
        if (keyCode ==65){
            System.out.println("你按下了A键");
        } else if (keyCode == 68) {
            System.out.println("你按下了D键");
        } else if (keyCode == 87) {
            System.out.println("你按下了W键");
        } else if (keyCode == 83) {
            System.out.println("你按下了S键");
        }
    }
}
