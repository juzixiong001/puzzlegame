package com.juzixiong.ui;

import cn.hutool.core.io.FileUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关的业务逻辑：读取/保存用户信息、登录校验、注册校验等
 */
public class UserService {

    private final List<User> userList = new ArrayList<>();
    // 记录每个用户连续登录失败次数（也会同步到文件中的 count 字段）
    private final Map<String, Integer> failedLoginCount = new HashMap<>();

    // 用户信息文件路径（与原来 LoginJFrame 中保持一致）
    private static final String USER_INFO_FILE = "D:\\java_code\\JavaSE_code\\puzzlegame\\userinfo.txt";

    public UserService() {
        loadUsersFromFile();
    }

    /**
     * 从本地文件加载用户信息到内存
     */
    private void loadUsersFromFile() {
        if (!FileUtil.isFile(USER_INFO_FILE)) {
            return;
        }
        List<String> userInfoStrList = FileUtil.readUtf8Lines(USER_INFO_FILE);
        for (String s : userInfoStrList) {
            // 兼容两种格式：
            // 旧：username=xxx&password=yyy
            // 新：username=xxx&password=yyy&count=0
            String[] arr = s.split("&");
            if (arr.length < 2) {
                continue;
            }
            String userName = arr[0].split("=")[1];
            String passWord = arr[1].split("=")[1];
            userList.add(new User(userName, passWord));

            int count = 0;
            if (arr.length >= 3 && arr[2].startsWith("count=")) {
                try {
                    count = Integer.parseInt(arr[2].split("=")[1]);
                } catch (NumberFormatException ignored) {
                    count = 0;
                }
            }
            failedLoginCount.put(userName, count);
        }
    }

    /**
     * 将当前内存中的用户信息（包括 count）全部写回文件
     */
    private void persistAllUsersToFile() {
        List<String> lines = new ArrayList<>();
        for (User user : userList) {
            int count = failedLoginCount.getOrDefault(user.getUsername(), 0);
            String line = "username=" + user.getUsername()
                    + "&password=" + user.getPassword()
                    + "&count=" + count;
            lines.add(line);
        }
        FileUtil.writeUtf8Lines(lines, USER_INFO_FILE);
    }

    /**
     * 校验登录
     */
    public boolean validateLogin(String username, String password) {
        // 如果用户已被锁定（count >= 3），直接返回 false
        if (isUserLocked(username)) {
            return false;
        }
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                // 登录成功清空失败次数，并持久化
                resetFailedCount(username);
                persistAllUsersToFile();
                return true;
            }
        }
        // 登录失败，增加一次失败次数，并持久化
        increaseFailedCount(username);
        persistAllUsersToFile();
        return false;
    }

    /**
     * 用户是否已被锁定
     */
    public boolean isUserLocked(String username) {
        return getFailedCount(username) >= 3;
    }

    /**
     * 获取当前失败次数
     */
    public int getFailedCount(String username) {
        return failedLoginCount.getOrDefault(username, 0);
    }

    /**
     * 重置失败次数（在登录成功或必要时可调用）
     */
    public void resetFailedCount(String username) {
        failedLoginCount.remove(username);
    }

    /**
     * 增加一次失败次数，并在达到 3 次时锁定账号
     */
    private void increaseFailedCount(String username) {
        int count = failedLoginCount.getOrDefault(username, 0) + 1;
        failedLoginCount.put(username, count);
    }

    /**
     * 判断用户名是否已存在
     */
    public boolean isUsernameExists(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 注册用户（内存 + 文件）
     */
    public void registerUser(String username, String password) {
        // 先加到内存
        userList.add(new User(username, password));
        // 初始化失败次数为 0
        failedLoginCount.put(username, 0);
        // 再持久化到文件（统一写回，带 count 字段）
        persistAllUsersToFile();
    }

    /**
     * 提供只读列表给外部如有需要
     */
    public List<User> getUserList() {
        return userList;
    }
}


