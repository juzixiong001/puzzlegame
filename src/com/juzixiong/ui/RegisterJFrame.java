package com.juzixiong.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class RegisterJFrame extends JFrame implements MouseListener {
    //注册界面

    // 通过 UserService 管理用户数据
    private final UserService userService;

    //输入框组件
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JPasswordField confirmPasswordInput = new JPasswordField();

    //按钮组件
    JButton registerButton = new JButton();
    JButton resetButton = new JButton();
    JButton backButton = new JButton();


    public RegisterJFrame(UserService userService) {
        this.userService = userService;
        //初始化界面
        initFrame();

        //添加组件
        initView();

        //让页面显示
        this.setVisible(true);
    }


    //添加组件
    private void initView() {

        //1.添加用户名图片文字
        JLabel usernameLabel = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameLabel.setBounds(86, 127, 128, 47);
        this.getContentPane().add(usernameLabel);

        //2.添加用户名输入框
        usernameInput.setBounds(195, 137, 200, 30);
        this.getContentPane().add(usernameInput);

        //3.添加密码图片文字
        JLabel passwordLabel = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordLabel.setBounds(86, 184, 128, 47);
        this.getContentPane().add(passwordLabel);

        //4.添加密码输入框
        passwordInput.setBounds(195, 193, 200, 30);
        this.getContentPane().add(passwordInput);

        //5.添加确认密码图片文字
        JLabel confirmPasswordLabel = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        confirmPasswordLabel.setBounds(93, 253, 96, 17);
        this.getContentPane().add(confirmPasswordLabel);

        //6.添加确认密码输入框
        confirmPasswordInput.setBounds(195, 248, 200, 30);
        this.getContentPane().add(confirmPasswordInput);

        //7.添加注册按钮
        registerButton.setBounds(100, 320, 128, 47);
        registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        this.getContentPane().add(registerButton);
        registerButton.addMouseListener(this);

        //8.添加重置按钮
        resetButton.setBounds(256, 320, 128, 47);
        resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        this.getContentPane().add(resetButton);
        resetButton.addMouseListener(this);

        //9.添加返回登录按钮
        backButton.setBounds(180, 390, 128, 47);
        backButton.setText("返回登录");
        this.getContentPane().add(backButton);
        backButton.addMouseListener(this);

        //10.添加背景
        JLabel background = new JLabel(new ImageIcon("image\\register\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }


    //初始化窗口
    private void initFrame() {

        //设置界面宽高
        this.setSize(488, 467);
        //设置界面标题
        this.setTitle("拼图游戏v1.0 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(2); // DISPOSE_ON_CLOSE，只关闭当前窗口
        //设置布局为null
        this.setLayout(null);

    }

    //显示对话框
    public void showDialog(String content) {
        JDialog dialog = new JDialog();
        dialog.setSize(200, 150);
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        dialog.getContentPane().add(warning);
        dialog.setVisible(true);
    }

    //重置输入
    private void resetInputs() {
        usernameInput.setText("");
        passwordInput.setText("");
        confirmPasswordInput.setText("");
    }

    //注册用户
    private void registerUser() throws IOException {
        String username = usernameInput.getText().trim();
        String password = new String(passwordInput.getPassword());
        String confirmPassword = new String(confirmPasswordInput.getPassword());

        //1. 非空验证
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showDialog("用户名或密码不能为空！");
            return;
        }

        //2. 密码一致性验证
        if (!password.equals(confirmPassword)) {
            showDialog("两次输入的密码不一致！");
            return;
        }

        //3. 用户名唯一性验证（通过 userService 判断）
        if (userService.isUsernameExists(username)) {
            showDialog("该用户名已存在！");
            return;
        }

        //4. 注册成功，通过 userService 写入内存和文件
        userService.registerUser(username, password);
        showDialog("注册成功！");

        //5. 关闭注册窗口，返回登录界面（继续复用同一个 userService）
        this.dispose();
        new LoginJFrame(userService);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == resetButton) {
            //重置按钮点击
            resetInputs();
        } else if (e.getSource() == backButton) {
            //返回登录按钮点击
            this.dispose();
            new LoginJFrame(userService);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == registerButton) {
            //按下注册按钮，切换图片
            registerButton.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        } else if (e.getSource() == resetButton) {
            //按下重置按钮，切换图片
            resetButton.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == registerButton) {
            //松开注册按钮，恢复图片
            registerButton.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
            //执行注册操作
            try {
                registerUser();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource() == resetButton) {
            //松开重置按钮，恢复图片
            resetButton.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}