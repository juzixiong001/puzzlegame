package com.juzixiong.ui;

import cn.hutool.core.io.IoUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Properties;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener , ActionListener {

//游戏主界面
    
    //创建一个二维数组
    //目的：存储图片的编号，打乱图片顺序
    int[][] data = new int[4][4];

    //记录空白位置的坐标
    int x = 0 ;
    int y = 0 ;

    //记录当前图片路径
    String path = "image\\girl\\girl7\\";

    //定义一个二维数组，存储正确的数据
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };

    //定义计步器
    int step = 0;

    //创建选项下面的条目对象(重新游戏   重新登录   关闭游戏)
    JMenuItem ReGameJMenuItem = new JMenuItem("重新游戏");
    JMenuItem ReLoginJMenuItem = new JMenuItem("重新登录");
    JMenuItem CloseJMenuItem = new JMenuItem("关闭游戏");
    JMenuItem Girl = new JMenuItem("美女");
    JMenuItem Animal = new JMenuItem("动物");
    JMenuItem Sport = new JMenuItem("运动");

    //添加存档和读档功能
    JMenu SaveJMenu = new JMenu("存档");
    JMenu LoadJMenu = new JMenu("读档");

    JMenuItem saveItem01 = new JMenuItem("存档1(空)");
    JMenuItem saveItem02 = new JMenuItem("存档2(空)");
    JMenuItem saveItem03 = new JMenuItem("存档3(空)");
    JMenuItem saveItem04 = new JMenuItem("存档4(空)");
    JMenuItem saveItem05 = new JMenuItem("存档5(空)");

    JMenuItem loadItem01 = new JMenuItem("读档1(空)");
    JMenuItem loadItem02 = new JMenuItem("读档2(空)");
    JMenuItem loadItem03 = new JMenuItem("读档3(空)");
    JMenuItem loadItem04 = new JMenuItem("读档4(空)");
    JMenuItem loadItem05 = new JMenuItem("读档5(空)");

    JMenuItem AccountJMenuItem = new JMenuItem("公众号");

    // 共享的用户服务，用于重新登录时打开登录界面
    private final UserService userService;
    //随机数用于随机选择图片
    Random r = new Random();

    public GameJFrame(UserService userService){
        this.userService = userService;

        //初始化界面
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据（打乱）
        initData();

        //初始化图片
        initImage();

        //让页面显示
        this.setVisible(true);
    }


    private void initData() {

        //定义一个一维数组
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        //打乱数组中数据顺序
        //遍历数组，得到每一个元素，用每一个元素和随机索引上的数进行交换
        Random r = new Random();

        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            //交换
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }


        //将一维数组中的数据赋值给二维数组
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            //避免bug不能加else
            data[i / 4][i % 4] = tempArr[i];

        }
    }

    //添加图片的时候就需要根据二维数组中的数据来添加
    private void initImage() {

        //清空界面
        this.getContentPane().removeAll();

        //判断是否胜利
        if(isWin()){
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);

        }

        //添加计步器
        JLabel stepJLabel = new JLabel("你已经走了" + step + "步");
        stepJLabel.setBounds(50,30,100,20);
        this.getContentPane().add(stepJLabel);

        for (int i = 0; i < 4; i++) {

            //创建一行4个图片
            for (int j = 0; j < 4; j++) {

                //获取当前要加载的图片序号
                int number = data[i][j];

                //创建ImageIcon对象 和 JLabel对象容器
                JLabel jLabel = new JLabel(new ImageIcon( path + number + ".jpg"));
                //jLabel设置位置
                jLabel.setBounds(105 * j + 83,105 * i + 134,105,105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
                //把jLabel添加到界面
                this.getContentPane().add(jLabel);


            }

        }

        //添加背景图片
        JLabel jLabel1 = new JLabel(new ImageIcon("image\\background.png"));
        //设置位置
        jLabel1.setBounds(40,40,508,560);
        //添加到界面
        this.getContentPane().add(jLabel1);

        //刷新界面
        this.getContentPane().repaint();

    }


    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单上的两个选项(功能    关于我们)
        JMenu FunctionJMenu = new JMenu("功能");
        JMenu AboutJMenu = new JMenu("关于我们");
        JMenu ChangeImage = new JMenu("切换图片");


        //将每一选项下面的条目添加到对应的选项
        FunctionJMenu.add(ChangeImage);
        FunctionJMenu.add(ReGameJMenuItem);
        FunctionJMenu.add(ReLoginJMenuItem);
        FunctionJMenu.add(SaveJMenu);
        FunctionJMenu.add(LoadJMenu);
        FunctionJMenu.add(CloseJMenuItem);
        AboutJMenu.add(AccountJMenuItem);
        ChangeImage.add(Girl);
        ChangeImage.add(Animal);
        ChangeImage.add(Sport);
        SaveJMenu.add(saveItem01);
        SaveJMenu.add(saveItem02);
        SaveJMenu.add(saveItem03);
        SaveJMenu.add(saveItem04);
        SaveJMenu.add(saveItem05);
        LoadJMenu.add(loadItem01);
        LoadJMenu.add(loadItem02);
        LoadJMenu.add(loadItem03);
        LoadJMenu.add(loadItem04);
        LoadJMenu.add(loadItem05);


        //给条目添加事件监听
        ReGameJMenuItem.addActionListener(this);
        ReLoginJMenuItem.addActionListener(this);
        CloseJMenuItem.addActionListener(this);
        AccountJMenuItem.addActionListener(this);
        Girl.addActionListener(this);
        Animal.addActionListener(this);
        Sport.addActionListener(this);

        saveItem01.addActionListener(this);
        saveItem02.addActionListener(this);
        saveItem03.addActionListener(this);
        saveItem04.addActionListener(this);
        saveItem05.addActionListener(this);
        loadItem01.addActionListener(this);
        loadItem02.addActionListener(this);
        loadItem03.addActionListener(this);
        loadItem04.addActionListener(this);
        loadItem05.addActionListener(this);


        //将选项添加到菜单
        jMenuBar.add(FunctionJMenu);
        jMenuBar.add(AboutJMenu);

        //读取存档信息
        getGameInfo();

        //将菜单添加到界面
        this.setJMenuBar(jMenuBar);
    }

    public void getGameInfo() {
        //创建一个file对象 表示所有存档所在文件夹
        File file = new File("save");
        //进入文件夹获取到所有存档文件
        File[] files = file.listFiles();
        //获取里面步数 -> 修改文件
        for (File saveFile : files) {
            GameInfo gameInfo = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
                gameInfo = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            //获取步数
            int step = gameInfo.getStep();
            //获取存档序号
            int index = saveFile.getName().charAt(4) - '0';  // 1 2 3 4 5

            //修改菜单上文字信息
            SaveJMenu.getItem(index - 1).setText("存档" + index + "(" + step + "步)");
            LoadJMenu.getItem(index - 1).setText("读档" + index + "(" + step + "步)");

        }
    }



    private void initJFrame() {

        //初始化界面宽高
        this.setSize(603 , 680);
        //设置界面标题
        this.setTitle("拼图游戏v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面据中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消界面的默认居中布局,这样才会按照xy坐标设置组件位置
        this.setLayout(null);
        //添加键盘监听
        this.addKeyListener(this);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘按下不松
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if(code  == 65){

            //把界面的所有图片都删除
            this.getContentPane().removeAll();
            //加载整张图片（作弊）
            JLabel All = new JLabel(new ImageIcon( path + "all.jpg"));
            //设置位置和边框
            All.setBounds(83,134,420,420);
            //添加到界面
            this.getContentPane().add(All);

            //添加背景图片
            JLabel bg = new JLabel(new ImageIcon("image\\background.png"));
            //设置位置
            bg.setBounds(40,40,508,560);
            //添加到界面
            this.getContentPane().add(bg);
            //刷新界面
            this.getContentPane().repaint();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        //判断是否胜利 如果胜利就不能再执行代码了
        if(isWin()){
            return;
        }

        int code = e.getKeyCode();

        if(code == 37){
            if(y == 3){
                return;
            }
            System.out.println("向左移动");
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            //步数增加
            step++;
            //调用方法按照新的数组数据刷新界面
            initImage();

        }else if(code == 38){
            if(x == 3){
                return;
            }
            System.out.println("向上移动");
            data[x][y]  = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            //步数增加
            step++;
            //调用方法按照新的数组数据刷新界面
            initImage();

        }else if(code == 39){
            if(y == 0){
                return;
            }
            System.out.println("向右移动");
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            //步数增加
            step++;
            //调用方法按照新的数组数据刷新界面
            initImage();

        }else if(code == 40){
            if(x == 0){
                return;
            }
            System.out.println("向下移动");
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            //步数增加
            step++;
            //调用方法按照新的数组数据刷新界面
            initImage();

        } else if(code == 65){
            initImage();
        }

        //一键通关
        else if (code == 87) {

             data = new int[][]{
                     {1,2,3,4},
                     {5,6,7,8},
                     {9,10,11,12},
                     {13,14,15,0}
             };

             initImage();
            System.out.println(" 你已经作弊成功！");
        }
    }

    //判断是否胜利
    //判断data和win是否完全一致
    public boolean isWin(){

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    //有一个不一样，就返回false
                    return false;
                }
            }
        }

        //如果全部遍历完，都没有返回false，说明一致,返回true
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //获取点击的是哪个选项
        Object source = e.getSource();
        if(source == ReGameJMenuItem){
            System.out.println("重新游戏");

            //步数重置  一定要先清零step
            step = 0;
            //打乱数据
            initData();
            //重载图片
            initImage();

        } else if(source == ReLoginJMenuItem){

            System.out.println("重新登录");
            //关闭游戏界面
            this.setVisible(false);
            //打开登录界面，继续复用同一个 UserService
            new LoginJFrame(userService);

        } else if(source == CloseJMenuItem){

            System.out.println("关闭游戏");
            //关闭游戏
            System.exit(0);

        } else if(source == AccountJMenuItem){

            System.out.println("公众号");
            JDialog jDialog = new JDialog();
            //设置弹窗的标题
            jDialog.setTitle("关于我们");
            //设置弹窗的大小
            jDialog.setSize(644,644);
            //设置弹窗的位置
            jDialog.setLocationRelativeTo(null);
            //设置弹窗为置顶
            jDialog.setAlwaysOnTop(true);
            //设置弹窗为模态 (弹窗不关闭则无法操作下面的界面)
            jDialog.setModal(true);

            //根据配置文件更换弹窗图片
            Properties prop = new Properties();
            try {
                FileInputStream fis = new FileInputStream("game.properties");
                prop.load(fis);
                fis.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String path = (String) prop.get("account");

            //添加标签
            JLabel jLabel = new JLabel(new ImageIcon(path));
            //设置标签的位置
            jLabel.setBounds(0,0,450,453);
            //添加到弹窗
            jDialog.getContentPane().add(jLabel);
            //显示弹窗
            jDialog.setVisible(true);

        }

        else if(source == Girl){
            System.out.println("切换美女");

            //随机选择图片
            int i = r.nextInt(13) + 1;
            //修改path变量记录的值
            path = "image\\girl\\girl" + i + "\\";
            //重新开始
            step = 0;
            initData();
            initImage();


        }
        else if(source == Animal){
            System.out.println("切换动物");

            //随机选择图片
            int i = r.nextInt(8) + 1;
            //修改path变量记录的值
            path = "image\\animal\\animal" + i + "\\";
            //重新开始
            step = 0;
            initData();
            initImage();

        }
        else if(source == Sport){
            System.out.println("切换运动");

            //随机选择图片
            int i = r.nextInt(10) + 1;
            //修改path变量记录的值
            path = "image\\sport\\sport" + i + "\\";
            //重新开始
            step = 0;
            initData();
            initImage();

        } else if (source == saveItem01 || source == saveItem02 || source == saveItem03 || source == saveItem04 || source == saveItem05) {
            //获取存档序号
            int index = ((JMenuItem) source).getText().charAt(2) - '0';
            try {
                //直接把游戏数据写到本地（通过序列化流进行操作：直接将对象写入，且不可以修改）
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save\\save" + index + ".data"));
                IoUtil.writeObj(oos , true , new GameInfo(data,x,y,path,step));   //hutool 实现序列化流写入
                /*oos.writeObject(new GameInfo(data,x,y,path,step));
                oos.close();      序列化流*/
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            //修改存档、读档item上文字信息   存档（空） ->  存档（xx步）
            ((JMenuItem) source).setText("存档" + index + "（" + step + "步）");
            LoadJMenu.getItem(index-1).setText("读档" + index + "（" + step + "步）");


        } else if (source == loadItem01 || source == loadItem02 || source == loadItem03 || source == loadItem04 || source == loadItem05) {
            //获取读档序号
            JMenuItem item = (JMenuItem)  source;
            char cha = item.getText().charAt(2);
            int index = cha - '0';

            //从本地读取游戏数据
            GameInfo gameInfo = null;
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save\\save" + index + ".data"));
                gameInfo = (GameInfo) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

            data = gameInfo.getData();
            x = gameInfo.getX();
            y = gameInfo.getY();
            path = gameInfo.getPath();
            step = gameInfo.getStep();
            //重载图片
            initImage();

        }

    }

}