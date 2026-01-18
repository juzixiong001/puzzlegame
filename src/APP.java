import com.juzixiong.ui.GameJFrame;
import com.juzixiong.ui.LoginJFrame;
import com.juzixiong.ui.UserService;

import javax.swing.*;

public class APP {
    public static void main(String[] args) {
        //程序启动入口
        //想要打开某个界面，直接创建对象就行
        // 先创建一个 UserService，全局复用
        UserService userService = new UserService();
        new GameJFrame(userService);







    }
}
