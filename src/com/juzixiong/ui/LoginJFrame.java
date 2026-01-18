package com.juzixiong.ui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginJFrame extends JFrame implements MouseListener {
    //登录界面

    // 用户服务，负责用户相关业务（登录、注册、读写文件等）
    private final UserService userService;


    //添加登录 和注册按钮
    JButton login = new JButton();
    JButton register = new JButton();

    //验证码提示框
    JLabel rightCode = new JLabel();

    //用户名 密码 验证码输入框
    JTextField userName = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    public LoginJFrame(UserService userService){
        this.userService = userService;

        //初始化界面
        initJFrame();

        //在这个界面添加组件
        initView();

        //让页面显示
        this.setVisible(true);
    }


    //添加组件
    private void initView() {

        //1.添加用户名图片文字
        JLabel userNmaeJLabel = new JLabel(new ImageIcon("image\\login\\用户名.png"));
        userNmaeJLabel.setBounds(136 ,145,47,17);
        this.getContentPane().add(userNmaeJLabel);

        //2.设置用户名输入框
        userName.setBounds(195 ,139,200,30);
        this.getContentPane().add(userName);

        //3.添加密码图片文字
        JLabel passwordJLabel = new JLabel(new ImageIcon("image\\login\\密码.png"));
        passwordJLabel.setBounds(144 ,200,32,18);
        this.getContentPane().add(passwordJLabel);

        //4.设置密码输入框
        password.setBounds(195 ,195,200,30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\验证码.png"));
        codeText.setBounds(133 ,256,50,30);
        this.getContentPane().add(codeText);

        //设置验证码输入框
        code.setBounds(195 ,256,100,30);
        this.getContentPane().add(code);

        String CodeStr = getCode();
        //设置内容
        rightCode.setText(CodeStr);
        //位置和宽高
        rightCode.setBounds(300 ,256,50,30);
        //添加到界面
        this.getContentPane().add(rightCode);
        //添加点击事件
        rightCode.addMouseListener(this);

        //5.设置登录按钮
        login.setBounds(123 ,310,128,47);
        login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
        //去除按钮边框
        login.setBorderPainted(false);
        //去除按钮背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);
        //添加点击事件
        login.addMouseListener(this);

        //6.设置注册按钮
        register.setBounds(256 ,310,128,47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮边框
        register.setBorderPainted(false);
        //去除按钮背景
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);
        //添加点击事件
        register.addMouseListener(this);

        //7.添加背景
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0 ,0,470,390);
        this.getContentPane().add(background);

    }




    //初始化界面
    private void initJFrame() {

        //初始化界面宽高
        this.setSize(488 , 430);
        //设置界面标题
        this.setTitle("拼图游戏v1.0 登录");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面据中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //设置布局为null
        this.setLayout(null);

    }

    public void showJDiaLog(String content){
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //设置大小
        jDialog.setSize(200 , 150);
        //置顶
        jDialog.setAlwaysOnTop(true);
        //居中
        jDialog.setLocationRelativeTo(null);
        //不关闭无法操作下面的界面
        jDialog.setModal(true);
        //创建JLabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0 ,0 , 200 , 150);
        jDialog.getContentPane().add(warning);

        //让弹框显示出来
        jDialog.setVisible(true);
    }


    public static String getCode() {

        //随机生成5位数验证码
        ArrayList<Character> codeList = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            codeList.add((char)('a'+ i));
            codeList.add((char)('A'+ i));
        }
        Random r = new Random();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(codeList.size());
            Character c = codeList.get(index);
            sb.append(c);
        }

        int num = r.nextInt(10);
        sb.append(num);
        //但是数字可以出现在任意位置
        //字符串修改需要把字符串以字符数组的形式来修改
        char[] arr = sb.toString().toCharArray();
        int randomIndex = r.nextInt(arr.length);
        char temp = arr[randomIndex];
        //把最大索引与一个随机索引交换
        arr[randomIndex] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        //交换完毕在将字符数组转换为字符串
        return new String(arr);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("点击验证码-> 更换验证码");
        Object source = e.getSource();
        if(source == rightCode){
            //点击验证码提示
            //更换一个新的验证码
            String newCode = getCode();
            rightCode.setText(newCode);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

        //获取按钮
        Object source = e.getSource();
        if(source == login){ //按下登录按钮不松
            System.out.println("按下登录按钮不松，更换登录按钮图片");
            //切换登录按钮的背景图片
            login.setIcon(new ImageIcon("image\\login\\登录按下.png"));
        } else if (source == register) {
            //按下注册按钮不松
            System.out.println("按下注册按钮不松，更换注册按钮图片");
            //切换注册按钮的背景图片
            register.setIcon(new ImageIcon("image\\login\\注册按下.png"));
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //获取按钮
        Object source = e.getSource();
        if(source == login){

            //松起登录按钮,切换回登录按钮的背景图片
            login.setIcon(new ImageIcon("image\\login\\登录按钮.png"));
            System.out.println("松开登录按钮，校验用户信息");
            //获取用户输入的用户名 、 密码 、 验证码
            String inputUsername = userName.getText();
            String inputPassword = new String(password.getPassword());
            String inputCode = code.getText();

            //先比较验证码
            if(!inputCode.equalsIgnoreCase(rightCode.getText())){
                showJDiaLog("验证码错误！重开吧这都能错");
                return;
            }

            //判断用户名和密码是否为空（有一个空就不行）
            if(inputUsername.isEmpty() || inputPassword.isEmpty()){
                showJDiaLog("用户名或密码不能为空！");
                return;
            }

            //如果该用户已被锁定，直接提示并返回
            if (userService.isUserLocked(inputUsername)) {
                showJDiaLog("该用户已被锁定，请联系管理员！");
                return;
            }

            //判断用户名和密码是否正确（通过 UserService 校验，会自动统计失败次数并在 3 次后锁定）
            boolean loginSuccess = userService.validateLogin(inputUsername, inputPassword);

            if(loginSuccess){
                showJDiaLog("登录成功！");
                //登录成功后关闭当前登录界面
                this.dispose();
                //打开游戏主界面，并传入同一个 UserService
                new GameJFrame(userService);
            }else{
                int failedCount = userService.getFailedCount(inputUsername);
                if (failedCount >= 3) {
                    showJDiaLog("密码连续错误 3 次，账号已被锁定！");
                } else {
                    showJDiaLog("登录失败！用户名或者密码错误，第 " + failedCount + " 次错误");
                }
            }

        } else if(source == register) {
            //松起注册按钮,切换回注册按钮的背景图片
            register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
            System.out.println("松开注册按钮，打开注册界面");
            //当前登录界面关闭
            this.dispose();
            //打开注册界面（把同一个 userService 传过去）
            new RegisterJFrame(userService);

        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}