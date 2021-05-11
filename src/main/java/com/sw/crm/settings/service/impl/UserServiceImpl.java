package com.sw.crm.settings.service.impl;

import com.sw.crm.exception.LoginException;
import com.sw.crm.settings.dao.UserDao;
import com.sw.crm.settings.domain.User;
import com.sw.crm.settings.service.UserService;
import com.sw.crm.utils.DateTimeUtil;
import com.sw.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @MASTER 统治世界的霸主 常狗狗
 * @date 2021/4/29
 */
public class UserServiceImpl implements UserService {
    private UserDao ud = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);
    @Override
    public User login(String act, String pwd, String ip) throws LoginException {
        System.out.println("进入业务处理层");
        Map<String,String> map = new HashMap<>();
        map.put("act",act);
        map.put("pwd",pwd);
        User user = ud.login(map);
        if (user == null){
            throw new LoginException("账号或者密码错误");
        }
        if (user.getExpireTime().compareTo(DateTimeUtil.getSysTime()) < 0){
            throw new LoginException("您的账号已失效");
        }
        if ("0".equals(user.getLockState())){
            throw new LoginException("您的账号别锁定");
        }
        if (!user.getAllowIps().contains(ip)){
            throw new LoginException("无效的ip");
        }
        return user;
    }

    public List<User> getUserList(){
        System.out.println("进入获取User的业务处理层");
        return ud.getUserList();
    }
}
